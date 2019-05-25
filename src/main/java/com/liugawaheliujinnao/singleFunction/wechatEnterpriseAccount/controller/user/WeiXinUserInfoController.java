package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.user;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.UTF8MediaType;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinUserInfoService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
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
@Path("/userInfo")
public class WeiXinUserInfoController {

    @Autowired
    private WeiXinUserInfoService userService;

    @GET
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult get(@Context UriInfo ui) {
        try {
            return WeiXinResult.success(userService.fetchUserInfo(AppUtils.prepareParameters(ui.getQueryParameters())));
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Path("/getByUnionId")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult getByUnionId(@Context UriInfo ui) {
        try {
            return WeiXinResult.success(userService.fetchUserInfoByUnionId(AppUtils.prepareParameters(ui.getQueryParameters())));
        } catch (Exception e) {
            return null;
        }
    }

    @GET
    @Path("/list")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult list(@Context UriInfo ui) {
        try {
            System.out.println("weixin log fetch user list now");
            return WeiXinResult.success(userService.fetchUserList(AppUtils.prepareParameters(ui.getQueryParameters())));
        } catch (Exception e) {
            return null;
        }
    }
}
