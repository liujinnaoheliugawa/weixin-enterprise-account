package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.req;

import java.io.Serializable;

/**
 * Created by wufei on 16/1/25.
 */
public class TextMessage extends BaseMessage implements Serializable {

    /**消息内容**/
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
