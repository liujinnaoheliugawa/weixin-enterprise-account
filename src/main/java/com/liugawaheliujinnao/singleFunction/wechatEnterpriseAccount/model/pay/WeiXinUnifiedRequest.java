package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay;

import java.io.Serializable;

/**
 * Created by wufei on 16/4/7.
 */
public class WeiXinUnifiedRequest implements Serializable {

    private String appid;

    private String mch_id;

    private String device_info;

    private String nonce_str;

    private String sign;

    private String body;

    private String attach;

    private String out_trade_no;

    private Integer total_fee;

    private String spbill_create_ip;

    private String time_start;

    private String time_expire;

    private String goods_tag;

    private String notify_url;

    private String trade_type;

    private String openid;

    private String product_id;

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

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public WeiXinUnifiedRequest () {

    }

    public WeiXinUnifiedRequest (String appid, String mch_id, String device_info, String nonce_str, String sign, String body, String attach, String out_trade_no, Integer total_fee, String spbill_create_ip,
                                 String time_start, String time_expire, String goods_tag, String notify_url, String trade_type, String openid, String product_id) {
        this.appid = appid;
        this.mch_id = mch_id;
        this.device_info = device_info;
        this.nonce_str = nonce_str;
        this.sign = sign;
        this.body = body;
        this.attach = attach;
        this.out_trade_no = out_trade_no;
        this.total_fee = total_fee;
        this.spbill_create_ip = spbill_create_ip;
        this.time_start = time_start;
        this.time_expire = time_expire;
        this.goods_tag = goods_tag;
        this.notify_url = notify_url;
        this.trade_type = trade_type;
        this.openid = openid;
        this.product_id = product_id;
    }
}
