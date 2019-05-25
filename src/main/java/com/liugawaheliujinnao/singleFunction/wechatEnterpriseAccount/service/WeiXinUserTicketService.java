package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel.WeixinUserTicket;

import java.io.Serializable;

public interface WeiXinUserTicketService {

    Serializable save(WeixinUserTicket model) throws Exception;
    Object update(WeixinUserTicket model) throws Exception;
    WeixinUserTicket getByUid(String uid) throws Exception;
}
