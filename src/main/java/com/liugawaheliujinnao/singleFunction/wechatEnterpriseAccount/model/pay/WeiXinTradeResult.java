package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay;

import java.io.Serializable;

/**
 * Created by wufei on 16/6/30.
 */
public class WeiXinTradeResult implements Serializable {

    private String trade_state;

    private String transaction_id;

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public WeiXinTradeResult() {

    }

    public WeiXinTradeResult(String trade_state, String transaction_id) {
        this.trade_state = trade_state;
        this.transaction_id = transaction_id;
    }
}
