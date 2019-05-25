package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.ws.rs.FormParam;
import java.io.Serializable;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class WeiXinTicket implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "ticket")
    @FormParam("ticket")
    private String ticket;

    @Column(name = "url")
    @FormParam("url")
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WeiXinTicket () {

    }

    public WeiXinTicket (WeiXinEternalTicket ticket) {
        this.ticket = ticket.getTicket();
        this.url = ticket.getUrl();
    }
}
