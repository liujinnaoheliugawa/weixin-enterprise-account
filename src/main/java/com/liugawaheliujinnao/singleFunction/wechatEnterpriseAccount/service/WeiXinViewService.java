package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.behavior.WeiXinView;
import org.springframework.stereotype.Service;

@Service
public interface WeiXinViewService {

    WeiXinView getByName(String name);
}
