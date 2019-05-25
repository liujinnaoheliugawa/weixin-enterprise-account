package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.token.AccessToken;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.SnsUser;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.User;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.mq.producer.ShareClickSender;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.redis.RedisCachePlugin;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.UTF8MediaType;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.WeiXinHttpClient;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.SnsUserService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.UserService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinTokenService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.AppUtils;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Component
@Path("/user")
public class UserController {

    @Autowired
    private UserService zenlifeUserService;
    @Autowired
    private SnsUserService zenlifeSnsUserService;

    @Resource
    private ShareClickSender shareClickSender;

    @Autowired
    RedisCachePlugin redisCachePlugin;

    @Autowired
    private WeiXinTokenService tokenService;

    @POST
    @Path("/saveFlow")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult saveFlow(@Context HttpServletRequest request, String params) {

        try {
            System.out.println("记录用户点击行为");
            JSONObject flow = JSONObject.parseObject(params);
            // 检查分享者的uid是否存在
            if (flow.containsKey("fromUid")) {
                String uid = flow.getString("fromUid");
                User user = zenlifeUserService.getByUid(uid);
                if (null == user) {
                    System.err.println("未发现相关用户,uid=" + uid);
                    return WeiXinResult.failed(-3, "未发现相关用户");
                }

                flow.put("clickIp", request.getRemoteAddr());

                String asyncMsg = flow.toString();
                System.out.println("发送jms消息:" + asyncMsg);
                shareClickSender.send("share.click", asyncMsg);
                return WeiXinResult.success();
            } else
            {
                return WeiXinResult.failed(-3, "未发现相关用户");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-2, "保存失败");
        }
    }

    @GET
    @Path("/getUid")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult getUid(@Context UriInfo ui) {
        try {
            String openId = AppUtils.prepareParameters(ui.getQueryParameters()).get("openId");
            SnsUser user = zenlifeSnsUserService.getUserByOpenid(openId);
            if (user != null && user.getUser() != null) {
                JSONObject resp = new JSONObject();
                resp.put("uId", user.getUser().getId());
                return WeiXinResult.success(resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-3, "查找失败");
        }
        return WeiXinResult.failed(-4, "未关注用户");
    }

    @POST
    @Path("/rewardIntegal")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult rewardIntegal(String params) {
        try {
            JSONObject req = JSONObject.parseObject(params);
            String uid = req.getJSONArray("records").getJSONObject(0).getString("uid");
            String url = Utils.initUserBehaviorUrl("wx_public", uid);

            String result = WeiXinHttpClient.postJSonStr(url, params);
            System.out.println("rewardIntegal behavior result =" + result);
            return WeiXinResult.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WeiXinResult.failed(-4, "加积分失败");
    }

    @POST
    @Path("/accessToken")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult accessToken(String params) {
        try {
            JSONObject req = JSONObject.parseObject(params);
            String uid = req.getJSONArray("records").getJSONObject(0).getString("uid");
            if( null != uid && !uid.isEmpty()){
                User user = zenlifeUserService.getByUid(uid);
                if( null != user ){
                    AccessToken at = tokenService.fetch();
                    return WeiXinResult.success(at.getAccess_token());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WeiXinResult.failed(-1, "无效的用户id");
    }
}
