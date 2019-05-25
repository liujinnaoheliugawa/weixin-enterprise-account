package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay;

import java.io.Serializable;

/**
 * Created by wufei on 16/4/8.
 */
public class WeiXinOrderNotify implements Serializable {

    private Integer id;

    private String outTradeNo;

    private Boolean success;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
