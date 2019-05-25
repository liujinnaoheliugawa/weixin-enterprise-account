package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wufei on 16/4/5.
 */

public class WeiXinOrder implements Serializable {

    private Integer id;

    private String tradeNo;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
