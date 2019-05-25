package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import org.springframework.stereotype.Service;

@Service
public interface ShareService {

    /**
     * 处理用户点击分享的链接jms消息,注意只处理文本消息
     * @param messageText 消息内容
     * @param topic 消息主题
     */
    void delMsg(String messageText, String topic);
}
