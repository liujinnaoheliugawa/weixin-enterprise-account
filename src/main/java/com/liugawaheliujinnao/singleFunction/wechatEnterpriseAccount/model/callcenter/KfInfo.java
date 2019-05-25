package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.callcenter;

import java.io.Serializable;

public class KfInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2896130297015143718L;
    private String kf_account = "";// 完整客服账号，格式为：账号前缀@公众号微信号
    private String kf_headimgurl = "";// 客服头像
    private String kf_id = "";// 客服工号
    private String kf_nick = "";// 客服昵称

    private int status = 0;// 客服在线状态 1：pc在线，2：手机在线。若pc和手机同时在线则为 1+2=3
    private int auto_accept = 0;// 客服设置的最大自动接入数
    private int accepted_case = 0;// 客服当前正在接待的会话数

    public String getKf_account() {
        return kf_account;
    }

    public void setKf_account(String kf_account) {
        this.kf_account = kf_account;
    }

    public String getKf_headimgurl() {
        return kf_headimgurl;
    }

    public void setKf_headimgurl(String kf_headimgurl) {
        this.kf_headimgurl = kf_headimgurl;
    }

    public String getKf_id() {
        return kf_id;
    }

    public void setKf_id(String kf_id) {
        this.kf_id = kf_id;
    }

    public String getKf_nick() {
        return kf_nick;
    }

    public void setKf_nick(String kf_nick) {
        this.kf_nick = kf_nick;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAuto_accept() {
        return auto_accept;
    }

    public void setAuto_accept(int auto_accept) {
        this.auto_accept = auto_accept;
    }

    public int getAccepted_case() {
        return accepted_case;
    }

    public void setAccepted_case(int accepted_case) {
        this.accepted_case = accepted_case;
    }

}
