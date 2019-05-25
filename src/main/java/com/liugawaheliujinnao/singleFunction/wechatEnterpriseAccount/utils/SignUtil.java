package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class SignUtil {

    /**
     * log4j日志
     */
    protected static Logger logger = Logger.getLogger(SignUtil.class);

    public static String sign(String jsapi_ticket, String url) {
        JSONObject ret = new JSONObject();
        String nonce_str = Utils.create_nonce_str();
        String timestamp = Utils.create_timestamp();
        // String nonce_str = "82693e11-b9bc-448e-892f-f5289f46cd0f";//wx
        // config配置
        // String timestamp = "1419835025";
        String string1;
        String signature = "";
        // 注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
        logger.error(string1);
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId", "appId");
        return ret.toString();
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] { "token", timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     *
     * 将字节数组转换为十六进制字符串
     *
     *
     *
     * @param byteArray
     *
     * @return
     */

    private static String byteToStr(byte[] byteArray) {

        String strDigest = "";

        for (int i = 0; i < byteArray.length; i++) {

            strDigest += byteToHexStr(byteArray[i]);

        }

        return strDigest;

    }

    /**
     *
     * 将字节转换为十六进制字符串
     *
     *
     *
     * @param mByte
     *
     * @return
     */

    private static String byteToHexStr(byte mByte) {

        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        char[] tempArr = new char[2];

        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];

        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);

        return s;

    }
}
