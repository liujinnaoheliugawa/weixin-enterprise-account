package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.token;

import com.fasterxml.jackson.core.type.TypeReference;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.JsAPITicketEntity;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.token.AccessToken;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.token.WeiXinToken;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.WeiXinHttpsClient;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinTokenService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.LocalCacheUtil;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.WeiXinTokenStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Component
public class AccessTokenUpdateUtil {

    @Autowired
    WeiXinTokenService tokenService;

    public void updateToken() {
        try {
            String tokenUrl = "token_url";
            Map<String, String> fetchMap = tokenService.getFetchMap();
            String fetchUrl = WeiXinTokenStringUtil.convertUrl(tokenUrl, fetchMap);
            TypeReference reference = new TypeReference<WeiXinToken>() {
            };
            System.out.println("weixin log fetchUrl is " + fetchUrl);
            WeiXinToken weixinToken = WeiXinHttpsClient.get(fetchUrl, reference);
            AccessToken accessToken = tokenService.fetch();
            if (accessToken != null) {
                accessToken.setOld_token(accessToken.getAccess_token());
                accessToken.setAccess_token(weixinToken.getAccess_token());
                tokenService.update(accessToken);
            } else {
                AccessToken newToken = new AccessToken();
                newToken.setAccess_token(weixinToken.getAccess_token());
                tokenService.save(newToken);
            }
            LocalCacheUtil.ASSCESS_TOKEN = weixinToken.getAccess_token();
            cacheJsApiTicket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cacheJsApiTicket() {
        try {

            String jsApiTicket = getJsApiTicket();
            LocalCacheUtil.JS_API_TICKET = jsApiTicket;
            System.out.println("jsapi="+jsApiTicket);

        } catch (Exception e) {
            System.err.println("获取ticket失败:"+e);
            System.err.println("刷新JSAPI_TICKET_KEY失败2分钟后将再次刷新!");

        }
    }


    /**
     * 获取jsapi_ticket
     *
     * @return jsapi_ticket
     */
    private String getJsApiTicket() {
        Map<String, String> paramMap = new LinkedHashMap<String, String>();
        paramMap.put("access_token", LocalCacheUtil.ASSCESS_TOKEN);
        TypeReference reference = new TypeReference<JsAPITicketEntity>() {
        };
        String url = WeiXinTokenStringUtil.convertUrl("js_api_ticket_get_url", paramMap);
        System.out.println("jsticket url = " + url);
        JsAPITicketEntity ticketEntity = WeiXinHttpsClient.get(url, reference);
        return ticketEntity.getTicket();
    }
}
