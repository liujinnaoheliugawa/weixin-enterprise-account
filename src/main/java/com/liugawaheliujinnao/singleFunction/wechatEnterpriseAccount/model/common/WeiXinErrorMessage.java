package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class WeiXinErrorMessage {

    public static final WeiXinErrorMessage WX_DELETE_CONSTRAINT = new WeiXinErrorMessage("该数据有关联数据，不能删除", -001);
    public static final WeiXinErrorMessage WX_DELETE_NO_DATA = new WeiXinErrorMessage("该数据不存在", -002);
    public static final WeiXinErrorMessage WX_NO_PARAMETER_ERROR = new WeiXinErrorMessage("缺少参数", -107);

    //未知异常，msg从exception中获取  -201错误码预留
    public static final int WX_UNKONWN_ERROR_CODE = -201;
    //JSAPI检查openid
    public static final WeiXinErrorMessage WX_NO_OPENID_ERROR = new WeiXinErrorMessage("获取不到用户的openid，请检查支付方式是否正确", -202);
    public static final WeiXinErrorMessage WX_UNVALID_PAYTYPE_ERROR = new WeiXinErrorMessage("无效的支付方式", -203);

    /**token获取失败**/
    public static final WeiXinErrorMessage WX_FETCH_TOKEN_FAILED = new WeiXinErrorMessage("Token获取失败",-004);

    public static final WeiXinErrorMessage WX_CREATE_PRODUCT_FAILED = new WeiXinErrorMessage("商品创建失败", -005);

    /**sign认证*/
    public static final WeiXinErrorMessage WX_SIGN_ERROR = new WeiXinErrorMessage("签名不正确！", -11000);

    public static final WeiXinErrorMessage WX_AUTH_ERROR = new WeiXinErrorMessage("微信秘钥验证失败!", -20002);

    private String msg;
    private int code;
    public WeiXinErrorMessage(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
