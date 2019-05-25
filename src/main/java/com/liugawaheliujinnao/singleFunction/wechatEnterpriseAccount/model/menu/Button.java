package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.menu;

import java.io.Serializable;

/**
 * Created by Roy2_Wu on 2016/2/13.
 *
 * 菜单项基类
 */
public class Button implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
