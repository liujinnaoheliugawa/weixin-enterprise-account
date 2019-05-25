package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MyMsgTemplateService {

    String queryMsgTemplateByMsgType(Map<String, Object> params, String msgType);
}
