package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.User;

import java.io.Serializable;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class WeixinUserTicket implements Serializable {

    private Long id;

    private User user;

    private String ticket;

    private Long expire_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Long getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(Long expire_time) {
        this.expire_time = expire_time;
    }
}
