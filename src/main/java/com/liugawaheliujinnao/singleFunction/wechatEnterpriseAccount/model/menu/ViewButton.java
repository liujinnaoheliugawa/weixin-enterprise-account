package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.menu;

import java.io.Serializable;

/**
 * Created by wufei on 16/2/14.
 */
public class ViewButton extends Button implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7566717467289251133L;
    //按钮类型
    private String type;
    //跳转链接
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() { 
        return "name="+ this.getName() + ";"+"type="+ type + ";"+"url="+ url;
    }
    
}
