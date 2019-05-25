package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinTokenType;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.job.WeiXinIp;

import java.io.Serializable;
import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public interface WeiXinIpService {

    Serializable save(WeiXinIp ip) throws Exception;

    Object update(WeiXinIp ip) throws Exception;

    Map getTokenMap(WeiXinTokenType type) throws Exception;

    boolean truncate() throws Exception;

    String getIpUrl(Map<String, String> map) throws Exception;
}
