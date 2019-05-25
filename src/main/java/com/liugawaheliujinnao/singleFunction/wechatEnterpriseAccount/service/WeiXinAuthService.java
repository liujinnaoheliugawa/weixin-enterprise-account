package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.SnsUser;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Service
public interface WeiXinAuthService {

    String openBrowser(Map<String, String> map);

    String openLoggerBrowser(Map<String, String> map);

    SnsUser getAuthUser(Map<String, String> map);
}
