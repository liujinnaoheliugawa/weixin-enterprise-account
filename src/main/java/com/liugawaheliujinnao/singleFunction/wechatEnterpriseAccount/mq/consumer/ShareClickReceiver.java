package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.mq.consumer;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.ShareService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Description: 消息队列监听器
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Component
public class ShareClickReceiver implements MessageListener {

    private static final Log Console = LogFactory.getLog(PresentClickReceiver.class);
    @Autowired
    private ShareService shareService;

    @Override
    public void onMessage(Message message) {
        try {
            String paramsStr = ((TextMessage)message).getText();
            shareService.delMsg(paramsStr, "share.click");

        } catch (Exception ex ){
            Console.error("处理点击消息异常:" + ex);
        }
    }
}
