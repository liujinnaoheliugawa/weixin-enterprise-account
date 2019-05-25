package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.WeiXinUserInfo;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.WeiXinUserList;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface WeiXinUserInfoService {

    WeiXinUserInfo fetchUserInfo(Map<String, String> map) throws Exception;

    WeiXinUserInfo fetchUserInfoByUnionId(Map<String, String> map) throws Exception;

    /**
     * 根据openId查询数据库中的用户信息
     * @param openId
     * @return
     * @throws Exception
     */
    WeiXinUserInfo fetchUserInfoByOpenid(String openId) throws Exception;

    WeiXinUserInfo fetchUserInfo(String openId) throws Exception;

    void save(WeiXinUserInfo model) throws Exception;

    WeiXinUserList fetchUserList(Map<String, String> map) throws Exception;

    int getEffectRank(Integer effect);
}
