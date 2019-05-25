package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.req;

/**
 * Created by wufei on 16/1/25.
 */

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.Serializable;

/**
 * 消息基类(普通用户->公众账号)
 */
public class BaseMessage implements Serializable {

    /**开发者微信账号**/
    private String ToUserName;

    /**发送方帐号(openId)**/
    private String FromUserName;

    /**消息创建时间**/
    private long CreateTime;

    /**消息类型**/
    private String MsgType;

    /**消息id**/
    @XStreamOmitField
    private long MsgId;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}
