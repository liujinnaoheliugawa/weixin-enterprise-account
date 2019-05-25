package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.channel;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel.*;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.UTF8MediaType;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.*;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Component
@Path("/qr")
public class WeiXinQrController {

    @Autowired
    private WeiXinQrService qrService;

    @Autowired
    private WeiXinChannelService channelService;

    @Autowired
    private WeiXinTicketService ticketService;

    @Autowired
    private WeiXinUserTicketService userTicketService;

    @Autowired
    private SnsUserService snsUserService;

    @GET
    @Produces(UTF8MediaType.JSON)
    @Path("/getEternalQr")
    public Object getEternalQr(@Context UriInfo ui) {
        try {
            Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
            String code = map.get("code");
            String description = map.get("description");
            String name = map.get("name");
            String qr = map.get("qr");
            if (!channelService.checkExists(qr)) {
                WeiXinEternalStrActionInfo actionInfo = qrService.getEternalStrActionInfo(qr);
                WeiXinEternalTicket eternalTicket = qrService.fetchEternalTicket(actionInfo);
                WeiXinTicket ticket = new WeiXinTicket(eternalTicket);
                ticketService.save(ticket);
                WeiXinChannel channel = new WeiXinChannel();
                channel.setCode(code);
                channel.setDescription(description);
                channel.setName(name);
                channel.setQr(qr);
                channel.setTicket(ticket);
                channelService.save(channel);
                return qrService.downloadPic(ticket);
            } else {
                WeiXinChannel channel = channelService.getChannel(qr);
                WeiXinTicket ticket = channel.getTicket();
                return qrService.downloadPic(ticket);
            }
            /*WeiXinEternalStrActionInfo actionInfo = qrService.getEternalStrActionInfo(qr);
            WeiXinEternalTicket eternalTicket = qrService.fetchEternalTicket(actionInfo);
            WeiXinTicket ticket = new WeiXinTicket(eternalTicket);
            return qrService.downloadPic(ticket);*/
        } catch (Exception e) {
            System.out.println("weixin log download img error " + e.toString());
            return null;
        }
    }

    @GET
    @Produces(UTF8MediaType.JSON)
    @Path("/checkEternalQr")
    public Object checkEternalQr(@Context UriInfo ui) {
        try {
            Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
            String qr = map.get("qr");
            WeiXinChannel channel = channelService.getChannel(qr);
            return channel != null ? qrService.downloadPic(channel.getTicket()) : null;
        } catch (Exception e) {
            return null;
        }
    }

    /*@GET
    @Produces(UTF8MediaType.JSON)
    @Path("/function")
    public WeiXinResult function(@Context UriInfo ui) {
        Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
        Integer effect = channelService.effect(map.get("uid"));
        return WeiXinResult.success(effect);
    }*/

    @GET
    @Produces(UTF8MediaType.JSON)
    @Path("/getTemplateQr")
    public Object getTemplateQr(@Context UriInfo ui) {
        try {
            String str = "test";
            WeiXinTemplateActionInfo actionInfo = qrService.getTemplateActionInfo(str);
            WeiXinTemplateTicket ticket = qrService.fetchTemplateTicket(actionInfo);
            return qrService.downloadPic(ticket);
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Produces(UTF8MediaType.JSON)
    @Path("/getPersonalQr")
    public Object getPersonalQr(@Context UriInfo ui) {
        Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
        String openId = map.get("openId");
        String uid = map.get("uid");
        try {
            System.out.println("上级流程 openId=" + openId + ";uid=" + uid);
            WeiXinTemplateTicket ticket = qrService.getUserTicket(openId,uid);
            return qrService.downloadPic(ticket);
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Produces(UTF8MediaType.JSON)
    @Path("/getShareQrUrl")
    public Object getShareQrUrl(@Context UriInfo ui) {
        Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
        String uid = map.get("uid");
        try {
            System.out.println("上级流程 uid is " + uid);
            WeiXinTemplateTicket ticket = qrService.getUserTicket(null,uid);
            String picUrl = qrService.getPicUrl(ticket);

            return WeiXinResult.success(picUrl);
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Produces(UTF8MediaType.JSON)
    @Path("/getPic")
    public WeiXinResult getPic(@Context UriInfo ui) {
        try {
            String str = "test";
            WeiXinTemplateActionInfo actionInfo = qrService.getTemplateActionInfo(str);
            WeiXinTemplateTicket ticket = qrService.fetchTemplateTicket(actionInfo);
            File file = qrService.getPicFile(ticket);
        } catch (Exception e) {
            return WeiXinResult.success("weixin log exception happend");
        }
        return WeiXinResult.success();
    }

    @GET
    @Produces(UTF8MediaType.JSON)
    @Path("/getPersonalQrUrl")
    public WeiXinResult getPersonalQrUrl(@Context UriInfo ui) {
        Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
        String openId = map.get("openId");
        String uid = map.get("uid");
        try {
            WeiXinTemplateTicket ticket = qrService.getUserTicket(openId,uid);
            String url = "";
            if (ticket != null){
                url = ticket.getUrl();
            }
            return WeiXinResult.success(url);
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Path("/getShareQr")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult getShareQr(@Context UriInfo ui) {
        Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
        String uid = map.get("uid");
        try {
            System.out.println("上级流程 uid is " + uid);
            System.out.println("这是新方法,用于获取分享列表分享者二维码");
            //modify by zhangzhen:使用uid作为查询参数
            WeiXinTemplateTicket ticket = qrService.getUserTicket(null, uid);
            String picUrl = qrService.getPicUrl(ticket);
            System.out.println("最后的picUrl 是" + picUrl);
            return WeiXinResult.success(picUrl);
        } catch (Exception e) {
            return null;
        }
    }
}
