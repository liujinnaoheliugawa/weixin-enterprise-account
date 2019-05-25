package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.behavior.WeiXinClick;
import org.springframework.stereotype.Service;

@Service
public interface WeiXinClickService {

    WeiXinClick getByKey(String key);
}
