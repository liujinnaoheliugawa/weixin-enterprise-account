package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay;

import java.io.Serializable;

/**
 * Created by wufei on 16/4/12.
 */
public class WeiXinOrderQueryResult implements Serializable {

    private String result_code;

    private String err_code;

    private String err_code_des;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public WeiXinOrderQueryResult () {

    }

    public WeiXinOrderQueryResult (String result_code, String err_code, String err_code_des) {
        this.result_code = result_code;
        this.err_code = err_code;
        this.err_code_des = err_code_des;
    }
}
