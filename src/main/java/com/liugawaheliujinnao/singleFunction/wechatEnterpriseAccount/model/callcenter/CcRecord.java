package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.callcenter;

import java.io.Serializable;

public class CcRecord implements Serializable {

    /**
     * 会话状态:
     *  1000    创建未接入会话
        1001    接入会话
        1002    主动发起会话
        1003    转接会话
        1004    关闭会话
        1005    抢接会话
        2001    公众号收到消息
        2002    客服发送消息
        2003    客服收到消息
     */
    private static final long serialVersionUID = -8544970138031947354L;

    private String openid = "";// 粉丝的openid
    private String opercode = "";// 会话状态
    private String text = "";// 聊天记录
    private String time = "";// 操作时间，UNIX时间戳
    private String worker = "";// 客服账号
    
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getOpercode() {
        return opercode;
    }
    public void setOpercode(String opercode) {
        this.opercode = opercode;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getWorker() {
        return worker;
    }
    public void setWorker(String worker) {
        this.worker = worker;
    }

}
