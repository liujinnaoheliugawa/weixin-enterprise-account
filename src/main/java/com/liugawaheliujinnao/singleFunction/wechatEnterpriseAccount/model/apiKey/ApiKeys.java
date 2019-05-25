package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.apiKey;

import java.io.Serializable;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class ApiKeys implements Serializable {

    private Integer id;

    private String name;

    private String des;

    private String origin;

    private String apiKey;

    private String privateKey;

    private String pubKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public ApiKeys() {

    }

    public ApiKeys(String apiKey, String des, String name, String origin, String privateKey, String pubKey) {
        this.apiKey = apiKey;
        this.des = des;
        this.name = name;
        this.origin = origin;
        this.privateKey = privateKey;
        this.pubKey = pubKey;
    }
}
