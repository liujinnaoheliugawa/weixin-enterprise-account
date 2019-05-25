package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import javax.servlet.http.HttpServletRequest;

public interface CoreService {

    /**处理微信发送来的请求**/
    String processRequest(HttpServletRequest req);
}
