package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.token;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.UTF8MediaType;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Component
@Path("/fetch")
public class AccessTokenController {

    @Autowired
    WeiXinTokenService tokenService;

    @GET
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult get() {
        return WeiXinResult.success(tokenService.fetch());
    }
}
