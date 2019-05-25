package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wufei on 16/4/5.
 */
public class WeiXinProduct implements Serializable {

    private String id;

    private String body;

    private Double price;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public WeiXinProduct() {

    }

    public WeiXinProduct(String id, String body, Double price, Date createTime) {
        this.id = id;
        this.body = body;
        this.price = price;
        this.createTime = createTime;
    }
}
