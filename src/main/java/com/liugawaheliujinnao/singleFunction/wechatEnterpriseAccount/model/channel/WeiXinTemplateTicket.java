package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel;

import java.io.Serializable;

/**
 * Created by wufei on 16/2/26.
 */
public class WeiXinTemplateTicket extends WeiXinTicket implements Serializable {

    private Long expire_seconds;

    public Long getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(Long expire_seconds) {
        this.expire_seconds = expire_seconds;
    }
}
