package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel;

import java.io.Serializable;

/**
 * Created by wufei on 16/2/26.
 */
public class WeiXinTemplateActionInfo implements Serializable {

    private WeiXinTemplateScene scene;

    public WeiXinTemplateScene getScene() {
        return scene;
    }

    public void setScene(WeiXinTemplateScene scene) {
        this.scene = scene;
    }
}
