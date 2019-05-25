package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel;

import java.io.Serializable;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class WeiXinChannel implements Serializable {

    private Integer id;

    private String name;

    private String code;

    private String description;

    private String qr;

    //微信分组
    private String group;

    private WeiXinTicket ticket;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public WeiXinTicket getTicket() {
        return ticket;
    }

    public void setTicket(WeiXinTicket ticket) {
        this.ticket = ticket;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
