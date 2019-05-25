package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay;

import java.io.Serializable;

/**
 * Created by wufei on 16/4/12.
 */
public class WeiXinCloseOrderRequest implements Serializable {

    private String appid;

    private String mch_id;

    private String out_trade_no;

    private String nonce_str;

    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public WeiXinCloseOrderRequest () {

    }

    public WeiXinCloseOrderRequest (String appid, String mch_id, String out_trade_no, String nonce_str, String sign) {
        this.appid = appid;
        this.mch_id = mch_id;
        this.out_trade_no = out_trade_no;
        this.nonce_str = nonce_str;
        this.sign = sign;
    }
}
