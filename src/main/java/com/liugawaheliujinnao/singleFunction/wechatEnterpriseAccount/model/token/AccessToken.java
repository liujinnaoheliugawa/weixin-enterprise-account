package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.token;

import java.io.Serializable;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class AccessToken implements Serializable {


    private Integer id;

    private String old_token;

    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOld_token() {
        return old_token;
    }

    public void setOld_token(String old_token) {
        this.old_token = old_token;
    }
}
