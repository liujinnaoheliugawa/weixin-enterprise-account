package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class JsAPITicketEntity {

    private int errcode;

    private String errmsg;

    private String ticket;

    private Double expires_in;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Double getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Double expires_in) {
        this.expires_in = expires_in;
    }
}
