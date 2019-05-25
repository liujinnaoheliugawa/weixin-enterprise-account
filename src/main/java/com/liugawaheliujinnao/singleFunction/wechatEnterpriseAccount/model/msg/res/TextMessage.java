package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.res;

import java.io.Serializable;

/**
 * Created by wufei on 16/1/25.
 */
public class TextMessage extends BaseMessage implements Serializable {

    /**回复的消息内容**/
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public TextMessage() {

    }

    public TextMessage(String fromUserName, String toUserName, Integer funcFlag, String msgType) {
        super(fromUserName, toUserName, funcFlag, msgType);
    }
}
