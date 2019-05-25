package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.callcenter;

import java.io.Serializable;
import java.util.List;

public class KfListInfo implements Serializable {

 
    /**
     * 
     */
    private static final long serialVersionUID = 4551376018612981590L;
    private List<KfInfo> kf_online_list =  null;
    public List<KfInfo> getKf_online_list() {
        return kf_online_list;
    }
    public void setKf_online_list(List<KfInfo> kf_online_list) {
        this.kf_online_list = kf_online_list;
    }

    
}
