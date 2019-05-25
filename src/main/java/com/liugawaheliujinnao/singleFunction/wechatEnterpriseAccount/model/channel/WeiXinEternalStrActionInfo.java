package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel;

import java.io.Serializable;

/**
 * Created by wufei on 16/3/23.
 */
public class WeiXinEternalStrActionInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private WeiXinEternalStrScene strScene;

    public WeiXinEternalStrScene getStrScene() {
        return strScene;
    }

    public void setStrScene(WeiXinEternalStrScene strScene) {
        this.strScene = strScene;
    }
}
