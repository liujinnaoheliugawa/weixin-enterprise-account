package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.behavior.WeiXinUserView;

import java.io.Serializable;

public interface WeiXinUserViewService {

    Serializable save(WeiXinUserView model) throws Exception;
}
