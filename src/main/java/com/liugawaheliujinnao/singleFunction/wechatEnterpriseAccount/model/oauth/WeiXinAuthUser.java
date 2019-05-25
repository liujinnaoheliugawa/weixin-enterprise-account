package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.oauth;

import java.io.Serializable;

/**
 * Created by wufei on 16/3/25.
 */
public class WeiXinAuthUser implements Serializable {

    private String access_token; /**网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同**/

    private Long expires_in;

    private String refresh_token; /**用户刷新access_token**/

    private String openid;

    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
