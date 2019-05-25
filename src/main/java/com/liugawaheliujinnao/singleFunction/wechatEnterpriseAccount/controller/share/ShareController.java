package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.share;

import com.alibaba.fastjson.JSONObject;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.ShareClickFlow;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.mq.producer.ShareClickSender;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.redis.RedisCachePlugin;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.UTF8MediaType;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.WeiXinHttpsClient;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.ShareClickFlowService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.SnsUserService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.UserService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Component
@Path("/share")
public class ShareController {

    @Autowired
    private SnsUserService SnsUserService;

    @Autowired
    RedisCachePlugin redisCachePlugin;

    @Resource
    private ShareClickSender shareClickSender;

    @Autowired
    private ShareClickFlowService flowService;

    @Autowired
    private UserService userService;

    @GET
    @Path("/getInfoDetail")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult getInfoDetail(@Context UriInfo ui) {
        try {
            Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
            String id = map.get("id");

            String url = "service_url" + "/zenlife_origin/shareInfo/get?id="+id;
            String result = WeiXinHttpsClient.get(url);
            JSONObject response = JSONObject.parseObject(result);
            if (response.getInteger("code").equals(0)){
                return WeiXinResult.success(response.getString("msg"));
            }
        } catch (Exception e){
        }
        return WeiXinResult.failed(-4, "获取资讯失败");
    }

    @GET
    @Path("/getClickCount")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult getClickCount(@Context UriInfo ui) {
        try{
            Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());

            String field = new StringBuffer(map.get("fromId"))
                    .append("#")
                    .append(map.get("url")).toString();

            return WeiXinResult.success(redisCachePlugin.getHashLongValue("user#share#count", field));
        }catch (Exception e){
        }
        return WeiXinResult.success(0);
    }

    @POST
    @Path("/saveFlow")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult saveFlow(@Context HttpServletRequest request, String params){
         System.out.println("PresentClickReceiver saveflow in");
         System.out.println("PresentClickReceiver params is " + params);
        try{
            JSONObject flow = JSONObject.parseObject(params);

            if (!flow.containsKey("clickOpenId") || flow.get("clickOpenId") == null){
                if (flow.containsKey("clickIp") && flow.get("clickIp") != null){
                    flow.put("clickIp", flow.getString("clickIp"));
                } else {
                    flow.put("clickIp", getRemoteAddress(request));
                }

            } else {
                flow.remove("clickIp");
            }
            shareClickSender.send("share.present.click", flow.toString());

            return WeiXinResult.success();
        } catch (Exception e ){
            return WeiXinResult.failed(-2, "保存失败");
        }
    }



    public static String getRemoteAddress(HttpServletRequest request) {
        try {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
                ip = request.getRemoteAddr();
            }
            return ip;
        } catch (Exception e) {

        }
        return "";
    }

    @GET
    @Path("/countShare")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult countShare(@Context UriInfo ui) {
        try{
            Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
            String uid = map.get("uid");
            return WeiXinResult.success(flowService.shareCount(uid));
        }catch (Exception e){
        }
        return WeiXinResult.success(0);
    }

    @POST
    @Path("/countShares")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult countShares(Map<String, String> map) {
        String uid = map.get("uid");
        String type = map.get("type");
        String relId = map.get("relId");
        return WeiXinResult.success(flowService.countShares(uid, type == null ? "all" : type, relId == null ? "0" : relId));
    }


    @POST
    @Path("/saveGoodFlow")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult saveGoodFlow(@Context HttpServletRequest request, Map<String, String> map) {
        String uid = map.get("fromUid");
        //ZenlifeSnsUser snsUser = zenlifeSnsUserService.getUserByUid(uid);
        String type = map.get("type");
        String relId = map.get("relId");
        String openId = map.get("openId");
        String url;
        if (type.equals("share")) { /** 商品分享 **/
            url = "loogear_url" + "/goodml/goToGoodsDetails.do?goodsId=" + relId;
        } else if (type.equals("shop")) { /** 店铺分享 **/
            url = "loogear_url" + "/goodml/shop.do?shopNo=" + relId;
        } else {//分享咨询
            url = "i_" + relId;
        }
        ShareClickFlow shareClickFlow = new ShareClickFlow();
        shareClickFlow.setUrl(url);
        shareClickFlow.setFromUid(uid);
        //shareClickFlow.setClickOpenId(snsUser.getOpenId());
        shareClickFlow.setClickOpenId(openId);
        shareClickFlow.setClickDate(new Date());
        shareClickFlow.setClickIp(getRemoteAddress(request));
        shareClickFlow.setRelId(Integer.parseInt(relId));
        shareClickFlow.setType(type);
        flowService.save(shareClickFlow);
        //更新用户的影响力
        userService.effect(uid);
        return WeiXinResult.success(0);
    }
}
