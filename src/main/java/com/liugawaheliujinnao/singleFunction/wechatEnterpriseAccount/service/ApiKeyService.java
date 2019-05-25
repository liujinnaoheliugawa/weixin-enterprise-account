package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.apiKey.ApiKeys;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApiKeyService extends TemplateService<ApiKeys> {

    List<ApiKeys> getAll();
}
