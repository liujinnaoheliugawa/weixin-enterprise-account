package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.behavior;

import java.io.Serializable;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class WeiXinClick implements Serializable {

    private Integer id;

    private String name;

    private String keyStr;

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

    public String getKeyStr() {
        return keyStr;
    }

    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
    }
}
