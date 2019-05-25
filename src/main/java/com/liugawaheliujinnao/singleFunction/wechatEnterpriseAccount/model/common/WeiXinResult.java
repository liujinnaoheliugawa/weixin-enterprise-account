package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class WeiXinResult<T> {

    private Integer code = 0;
    private T msg;

    public WeiXinResult() {
        super();
        // TODO Auto-generated constructor stub
    }

    public WeiXinResult(Integer code, T msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public static <T> WeiXinResult<T> success(){
        return new WeiXinResult(0, "ok");
    }

    public static <T> WeiXinResult<T> success(T msg){
        return new WeiXinResult(0, msg);
    }

    public static <T> WeiXinResult<T> failed(Integer code, T msg){
        return new WeiXinResult(code, msg);
    }

    public static <T> WeiXinResult<T> failed(WeiXinErrorMessage message){
        return new WeiXinResult(message.getCode(), message.getMsg());
    }

    public static  WeiXinResult failed(Integer code){
        return new WeiXinResult(code, "");
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public T getMsg() {
        return msg;
    }
    public void setMsg(T msg) {
        this.msg = msg;
    }
}
