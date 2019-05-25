package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel.WeiXinTicket;

import java.io.Serializable;

public interface WeiXinTicketService {

    Serializable save(WeiXinTicket model) throws Exception;
}
