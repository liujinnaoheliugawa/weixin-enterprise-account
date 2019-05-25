package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import org.springframework.stereotype.Service;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.logger.MyMessage;

import java.io.Serializable;

@Service
public interface MyMessageService {

    Serializable save(MyMessage model) throws Exception;
}
