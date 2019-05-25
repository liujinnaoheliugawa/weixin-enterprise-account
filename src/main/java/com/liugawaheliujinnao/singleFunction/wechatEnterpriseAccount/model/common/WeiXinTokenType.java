package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public enum WeiXinTokenType {

    NEW(1),
    OLD(2);

    private Integer type;

    WeiXinTokenType(Integer type) {
        this.type = type;
    }
}
