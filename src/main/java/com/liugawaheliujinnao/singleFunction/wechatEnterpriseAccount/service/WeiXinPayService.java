package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay.WeiXinOrderQueryResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay.WeiXinRefundQueryResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay.WeiXinRefundResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay.WeiXinTradeResult;

import java.util.Map;

public interface WeiXinPayService {

    /**
     * 调用统一下单接口，生成预付款id
     * @param map 订单参数
     * @param appId 应用id
     * @param mchId 商户id
     * @param appPaySecret api支付密钥
     * @return prepay_id
     * @throws Exception
     */
    String getPrepayId(Map<String, String> map, String appId, String mchId, String appPaySecret) throws Exception;

    /**
     * 签名
     * @param map
     * @param appPaySecret
     * @return 签名结果
     */
    String getRaw(Map<String, String> map,String appPaySecret);

    /**
     * 保存微信支付订单
     * @param map
     * @return
     * @throws Exception
     */
    boolean updateOrder(Map<String, String> map) throws Exception;

    /**
     * 查询支付状态
     * @param map 存储 out_trade_no
     * @param appId 应用id
     * @param mchId 商户id
     * @param appPaySecret api支付密钥
     * @return 最新状态
     */
    WeiXinTradeResult getTradeState(Map<String, String> map, String appId, String mchId, String appPaySecret);

    /**
     * 取消支付
     * @param map 存放 appid,mch_id,out_trade_no,nonce_str
     * @param appPaySecret api支付密钥
     * @return
     */
    WeiXinOrderQueryResult closeOrder(Map<String, String> map, String appPaySecret);

    /**
     *
     * @param path 整数地址
     * @param map 申请退款的订单参数
     * @param appSecret 应用签名
     * @param appPaySecret API支付32位密钥
     * @param mchId 商户号
     * @return
     */
    WeiXinRefundResult refund(String path, Map<String, String> map, String appSecret, String appPaySecret, String mchId);

    /**
     * 退款进度查询
     * @param map
     * @param appPaySecret
     * @return
     */
    WeiXinRefundQueryResult refundQuery(Map<String, String> map, String appPaySecret);
}
