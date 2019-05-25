package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.oauth;

import java.io.Serializable;

/**
 * Created by wufei on 16/4/28.
 */
public class WeiXinEssentialParam implements Serializable {

    private String appId;

    private String token;

    private String jsapi_ticket;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJsapi_ticket() {
        return jsapi_ticket;
    }

    public void setJsapi_ticket(String jsapi_ticket) {
        this.jsapi_ticket = jsapi_ticket;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer("token: ")
                .append(token)
                .append("; jsapi_ticket: ")
                .append(jsapi_ticket);
        return sb.toString();
    }
}
