package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel;

import java.io.Serializable;

/**
 * Created by wufei on 16/2/24.
 */
public class WeiXinEternalActionInfo implements Serializable {

    private WeiXinEternalScene scene;

    public WeiXinEternalScene getScene() {
        return scene;
    }

    public void setScene(WeiXinEternalScene scene) {
        this.scene = scene;
    }
}
