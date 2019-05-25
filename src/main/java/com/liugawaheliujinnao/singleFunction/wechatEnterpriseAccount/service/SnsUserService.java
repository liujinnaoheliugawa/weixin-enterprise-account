package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.SnsUser;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface SnsUserService {

    Serializable save(SnsUser model);

    Object update(SnsUser zenlifeUser);

    SnsUser getUser(String relId, String authType);

    SnsUser getUserByUid(String uid);

    SnsUser getUserByOpenid(String openId);

    /**
     * 根据union id查询用户信息，注意：手机注册的省事儿用户在本表中没有记录
     * @param unionId
     * @return
     */
    SnsUser getUserByUnionId(String unionId);
}
