package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.token.AccessToken;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

@Service
public interface WeiXinTokenService {

    AccessToken fetch();

    Serializable save(AccessToken token) throws Exception;

    Object update(AccessToken token) throws Exception;

    Map getFetchMap() throws Exception;
}
