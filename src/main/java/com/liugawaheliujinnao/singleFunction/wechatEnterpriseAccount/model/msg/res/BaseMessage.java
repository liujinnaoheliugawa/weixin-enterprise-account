package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.res;

/**
 * Created by wufei on 16/1/25.
 */

import java.io.Serializable;
import java.util.Date;

/**
 * 消息基类(公众账号->普通用户)
 */
public class BaseMessage implements Serializable {

    /**接收方帐号(openId)**/
    private String ToUserName;

    /**开发者微信账号**/
    private String FromUserName;

    /**消息创建时间**/
    private long CreateTime;

    /**消息类型**/
    private String MsgType;

    /**星标刚收到的消息**/
    private int FuncFlag;

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

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }

    public BaseMessage() {

    }

    public BaseMessage(String fromUserName, String toUserName, String msgType) {
        this.FromUserName = fromUserName;
        this.ToUserName = toUserName;
        this.CreateTime = new Date().getTime();
        this.MsgType = msgType;
    }

    public BaseMessage(String fromUserName, String toUserName, Integer funcFlag, String msgType) {
        this.FromUserName = fromUserName;
        this.ToUserName = toUserName;
        this.FuncFlag = funcFlag;
        this.CreateTime = new Date().getTime();
        this.MsgType = msgType;
    }
}
