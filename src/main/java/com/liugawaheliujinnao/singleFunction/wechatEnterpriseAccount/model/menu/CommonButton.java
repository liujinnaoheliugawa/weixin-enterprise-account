package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.menu;

import java.io.Serializable;

/**
 * Created by Roy2_Wu on 2016/2/13.
 *
 * 子菜单项
 */
public class CommonButton extends Button implements Serializable {

    private String type;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
