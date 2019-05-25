package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.user;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.token.AccessToken;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Component
public class UserInfoUtil {

    @Autowired
    private WeiXinTokenService tokenService;

    public void fetchUserInfo(String openId) {
        String userInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
        AccessToken token = tokenService.fetch();
        userInfoUrl = userInfoUrl.replace("ACCESS_TOKEN", token.getAccess_token());
        userInfoUrl = userInfoUrl.replace("OPENID", openId);
    }
}
