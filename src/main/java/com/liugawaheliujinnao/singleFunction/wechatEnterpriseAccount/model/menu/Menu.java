package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.menu;

import java.io.Serializable;

/**
 * Created by Roy2_Wu on 2016/2/13.
 *
 * 菜单对象
 */
public class Menu implements Serializable {

    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}
