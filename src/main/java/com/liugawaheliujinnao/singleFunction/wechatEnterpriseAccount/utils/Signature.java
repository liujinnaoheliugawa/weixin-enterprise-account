package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay.WeiXinUnifiedParam;

import java.util.*;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class Signature {

    /**
     * 生成签名
     * @param map 支付参数
     * @param appPaySecret 支付密钥
     * @return
     */
    public static String getSign(Map<String,Object> map, String appPaySecret){
        ArrayList<String> list = new ArrayList<>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(null != entry.getValue() && !"".equals(entry.getValue())){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        System.out.println("paramStr = " + result);
        if (appPaySecret != null) {
            result += "key=" + appPaySecret;
        } else {
            result = result.substring(0, result.length() - 1);
        }
        System.out.println("Sign Before MD5:" + result);
        result = MD5.MD5Encode(result).toUpperCase();
        System.out.println("Sign Result:" + result);
        return result;
    }

    public static String getSign(Map<String,String> map, String appPaySecret, boolean flag){
        ArrayList<String> list = new ArrayList<>();
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(null != entry.getValue() && !"".equals(entry.getValue())){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        //按照参数名排序
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        System.out.println("paramStr = " + result);
        if (appPaySecret != null) {
            result += "key=" + appPaySecret;
        } else {
            result = result.substring(0, result.length() - 1);
        }
        System.out.println("Sign Before MD5:" + result);
        result = MD5.MD5Encode(result).toUpperCase();
        System.out.println("Sign Result:" + result);
        return result;
    }


    /**
     * 生成 app调起支付参数，注意参数名称和H5调起支付参数名大小写不一样
     * @param appId
     * @param partnerid
     * @param appPaySecret
     * @param prepayId
     * @return
     */
    public static WeiXinUnifiedParam getAppCallBack(String appId, String partnerid, String appPaySecret, String prepayId){

        Map<String, String> reqMap = new HashMap<>();
        Long timeStamp = ((new Date().getTime())/1000);
        String timeStampStr = timeStamp.toString();
        String nonceStr = StrUtil.getRandomStr(32);
        String packageStr = "Sign=WXPay";
        String signType = "MD5";
        reqMap.put("appid", appId);
        reqMap.put("partnerid", partnerid);
        reqMap.put("prepayid", prepayId);
        reqMap.put("package", packageStr);
        reqMap.put("noncestr", nonceStr);
        reqMap.put("timestamp", timeStampStr);
        //生成签名
        String sign = Signature.getSign(reqMap, appPaySecret, true);

        WeiXinUnifiedParam param = new WeiXinUnifiedParam();
        param.setAppId(appId);
        param.setPartnerId(partnerid);
        param.setTimeStamp(timeStampStr);
        param.setNonceStr(nonceStr);
        param.setPackageStr(packageStr);
        param.setSignType(signType);
        param.setSign(sign);
        param.setPrepayId(prepayId);

        System.out.println(String.format("统一下单返回参数:appid=%s;prepayId=%s,partnerid=%s;NonceStr=%s;TimeStamp=%s;SignType=%s,sign=%s",
                param.getAppId(),prepayId, partnerid, param.getNonceStr(), param.getTimeStamp(), param.getSignType(), param.getSign()));
        return param;
    }


    /**
     * 生成 jsapi吊起支付参数
     * @param appId
     * @param appPaySecret
     * @param prepayId
     * @return
     */
    public static WeiXinUnifiedParam getJsapiCallBack(String appId, String appPaySecret, String prepayId){

        Map<String, String> reqMap = new HashMap<>();
        Long timeStamp = ((new Date().getTime())/1000);
        String timeStampStr = timeStamp.toString();
        String nonceStr = StrUtil.getRandomStr(32);
        String packageStr = "prepay_id=" + prepayId;
        String signType = "MD5";
        reqMap.put("appId", appId);
        reqMap.put("timeStamp", timeStampStr);
        reqMap.put("nonceStr", nonceStr);
        reqMap.put("package", packageStr);
        reqMap.put("signType", signType);
        //生成签名
        String sign =  Signature.getSign(reqMap, appPaySecret, true);

        WeiXinUnifiedParam param = new WeiXinUnifiedParam();
        param.setAppId(appId);
        param.setTimeStamp(timeStampStr);
        param.setNonceStr(nonceStr);
        param.setPackageStr(packageStr);
        param.setSignType(signType);
        param.setSign(sign);

        System.out.println(String.format("统一下单返回参数:appid=%s;NonceStr=%s;TimeStamp=%s;SignType=%s,sign=%s",
                param.getAppId(), param.getNonceStr(), param.getTimeStamp(), param.getSignType(), param.getSign()));
        return param;
    }
}
