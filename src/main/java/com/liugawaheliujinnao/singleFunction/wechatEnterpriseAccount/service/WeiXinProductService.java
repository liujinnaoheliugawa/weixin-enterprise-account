package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay.WeiXinProduct;

import java.io.Serializable;

public interface WeiXinProductService {

    Serializable save(WeiXinProduct model) throws Exception;
}
