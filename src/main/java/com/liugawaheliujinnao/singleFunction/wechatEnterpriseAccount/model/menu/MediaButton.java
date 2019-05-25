package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.menu;

import java.io.Serializable;

/**
 * Created by wufei on 16/2/14.
 */
public class MediaButton extends Button implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6581838989925144079L;
    //按钮类型
    private String type;
    //多媒体资源id
    private String media_id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
    
    @Override
    public String toString() { 
        return "name="+ this.getName() + ";"+"type="+ type + ";"+"media_id="+ media_id + ";";
    }
    
}
