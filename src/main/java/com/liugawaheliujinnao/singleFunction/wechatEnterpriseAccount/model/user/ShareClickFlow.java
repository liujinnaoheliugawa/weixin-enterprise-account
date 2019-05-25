package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class ShareClickFlow implements Serializable {

    private Long id;

    private String url;

    private String fromUid;

    private String clickOpenId;

    private String clickIp;

    private Date clickDate;

    private String type;

    private Integer relId;

    private int action;

    private String name;//邀请函专用

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClickOpenId() {
        return clickOpenId;
    }

    public void setClickOpenId(String clickOpenId) {
        this.clickOpenId = clickOpenId;
    }

    public String getClickIp() {
        return clickIp;
    }

    public void setClickIp(String clickIp) {
        this.clickIp = clickIp;
    }

    public Date getClickDate() {
        return clickDate;
    }

    public void setClickDate(Date clickDate) {
        this.clickDate = clickDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRelId() {
        return relId;
    }

    public void setRelId(Integer relId) {
        this.relId = relId;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
