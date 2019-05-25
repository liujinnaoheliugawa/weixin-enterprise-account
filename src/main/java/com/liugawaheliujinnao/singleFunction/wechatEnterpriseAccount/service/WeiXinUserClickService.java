package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.behavior.WeiXinUserClick;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
@Service
public interface WeiXinUserClickService {

    Serializable save(WeiXinUserClick model) throws Exception;
}
