package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel.WeiXinChannel;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface WeiXinChannelService {

    public Serializable save(WeiXinChannel model) throws Exception;

    public WeiXinChannel getByCode(String code);

    public Boolean checkExists(String qr);

    public WeiXinChannel getChannel(String qr);

    public WeiXinChannel getChannelByTicket(String ticket);
}
