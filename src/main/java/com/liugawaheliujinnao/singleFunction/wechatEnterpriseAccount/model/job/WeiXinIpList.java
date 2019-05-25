package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.job;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wufei on 16/2/3.
 */
public class WeiXinIpList implements Serializable {

    private List ip_list;

    public List getIp_list() {
        return ip_list;
    }

    public void setIp_list(List ip_list) {
        this.ip_list = ip_list;
    }
}
