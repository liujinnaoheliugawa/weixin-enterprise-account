package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.auth;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.oauth.WeiXinEssentialParam;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.UTF8MediaType;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.WeiXinHttpsClient;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinAuthService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinTokenService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.AppUtils;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.WeiXinApiKeysUtil;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.WeiXinTokenStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Component
@Path("/auth")
public class WeiXinAuthController {

    @Autowired
    private WeiXinAuthService authService;

    @Autowired
    private WeiXinTokenService tokenService;

    @Autowired
    WeiXinApiKeysUtil apiKeysUtil;

    @GET
    @Produces(UTF8MediaType.JSON)
    public Object get(@Context UriInfo ui) {
        try {
            String browserUrl = authService.openBrowser(AppUtils.prepareParameters(ui.getQueryParameters()));
            return Response.temporaryRedirect(new URI(browserUrl)).build();
        }catch (URISyntaxException e) {
            return null;
        }
    }

    @GET
    @Path("/logger")
    @Produces(UTF8MediaType.JSON)
    public Object logger(@Context HttpServletResponse res) {
        try {
            res.setContentType("");
            PrintWriter out = res.getWriter();
            out.println("<p>this is a test</p>");
            return null;
        }catch (IOException e) {
            return null;
        }
    }

    @GET
    @Path("/print")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(UTF8MediaType.JSON)
    public Object print(@Context UriInfo ui) {
        Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
        String content = map.get("content");
        System.out.println("weixin log auth print content is " + content);
        return null;
    }

    /**
     * 用户授权页面跳转
     * @param scope 作用域
     * @param redirectUrl 重定向页面URL
     * @param response
     */
    @Path("/mpOAuth")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public void userAuthorize(@QueryParam(value = "scope")String scope, @QueryParam(value = "redirectUrl")String redirectUrl,
                              @QueryParam(value = "showUrl")String showUrl, @Context HttpServletResponse response){
        try {
            System.err.println("userAuthorize================redirect_uri===================>" + redirectUrl);
             System.err.println("userAuthorize================scope===================>" + scope);
            if (StringUtils.isNotBlank(showUrl)){
                Map<String, String> redirectUrlMap = new HashMap<String, String>();
                 System.err.println("userAuthorize================showurl===================>" + showUrl);
                redirectUrlMap.put("showUrl", showUrl);
                redirectUrl = WeiXinTokenStringUtil.convertUrl(redirectUrl, redirectUrlMap);
                 System.err.println("userAuthorize================redirect_uri===================>" + redirectUrl);
            }
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("appid", "");
            paramMap.put("redirect_uri", redirectUrl);
            paramMap.put("response_type", "code");
            paramMap.put("scope", scope == null || "".equals(scope) ? "snsapi_base" : "snsapi_userinfo");
            paramMap.put("state", scope == null || "".equals(scope) ? "snsapi_base#wechat_redirect"
                    : "snsapi_userinfo#wechat_redirect");
             System.err.println("oauth param = " + paramMap.toString());
            String str = WeiXinTokenStringUtil.convertUrl("https://open.weixin.qq.com/connect/oauth2/authorize", paramMap);
             System.err.println("========跳转url===========" + str);
            response.sendRedirect(str);
        } catch (UnsupportedEncodingException e) {
             System.err.println("========URLDecoder解码异常==========>" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
             System.err.println("========重定向页面请求异常==========>" + e.getMessage());
        }
    }

    /**
     * 根据code换取access_token
     * @param code
     * @return
     */
    @Path("/mpCode2AccessToken")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public String getAccessTokenByCode(@QueryParam(value = "code")String code){
        System.out.println("**********getAccessTokenByCode start***********");
        String resData = null;
        if (code != null && !"".equals(code)) {
            // 根据code获取access_token
            Map<String, String> oAuthParamMap = new LinkedHashMap<String, String>();
            oAuthParamMap.put("appid", "");
            oAuthParamMap.put("secret", "");
            oAuthParamMap.put("code", code);
            oAuthParamMap.put("grant_type", "authorization_code");

            try {
                String url = WeiXinTokenStringUtil.convertUrl("https://api.weixin.qq.com/sns/oauth2/access_token", oAuthParamMap);
                System.out.println("codeToAccessToken url === " + url);
                resData = WeiXinHttpsClient.get(url);
                System.out.println("codeToAccessToken===============>" + resData);
            } catch (Exception e) {
                 System.err.println(e);
                resData = "{\"errmsg\":\"connection fail\"}";
            }
        }
        return resData;
    }

    /**
     * 根据accessToken和openId获取微信个人用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    @Path("/mpUserInfo")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public String getUserInfo(@QueryParam(value = "accessToken") String accessToken, @QueryParam(value = "openId") String openId){
        String resData = null;
        System.out.println("mpUserInfo start");
        System.out.println("accessToken = "+accessToken +";openId = " + openId);
        if (accessToken != null && !"".equals(accessToken) && openId != null && !"".equals(openId)) {
            Map<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("access_token", accessToken);
            paramMap.put("openid", openId);
            paramMap.put("lang", "zh_CN");
            resData = WeiXinHttpsClient.get(WeiXinTokenStringUtil.convertUrl("https://api.weixin.qq.com/sns/userinfo", paramMap));
            System.out.println("获取微信个人基本信息==============>" + resData);
        }
        return resData;
    }

    @POST
    @Path("/params")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult getParams(@Context HttpServletRequest request) {
        apiKeysUtil.load();
//        String appId = WeiXinConstants.WEIXIN_APP_ID;
//        String token = LocalCacheUtil.ASSCESS_TOKEN;
//        String jsapi_ticket = LocalCacheUtil.JS_API_TICKET;
        WeiXinEssentialParam param = new WeiXinEssentialParam();
        param.setAppId("");
        param.setToken("");
        param.setJsapi_ticket("");
        System.out.println("获取微信参数:" + param.toString());
        return WeiXinResult.success(param);
    }
}
