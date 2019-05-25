package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public interface TemplateService<T> {

    Serializable save(T model) throws Exception;

    Object update(T model) throws Exception;

    Object delete(T model) throws Exception;

    List<T> getAll(Map<String, String> con) throws Exception;
}
