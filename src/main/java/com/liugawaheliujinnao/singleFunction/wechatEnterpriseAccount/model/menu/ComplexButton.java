package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.menu;

import java.io.Serializable;

/**
 * Created by Roy2_Wu on 2016/2/13.
 *
 * 父菜单项
 */
public class ComplexButton extends Button implements Serializable {

    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
