package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.job;

import java.io.Serializable;

/**
 * Created by wufei on 16/2/3.
 */
public class WeiXinIp implements Serializable {

    private Integer id;

    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
