package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class WeiXinTokenStringUtil {

    private static final Log Console = LogFactory.getLog(WeiXinTokenStringUtil.class);

    private static final Random random = new Random();
    private static final String baseString = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int baseLength = baseString.length();

    public static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(baseLength);
            sb.append(baseString.charAt(num));
        }
        return sb.toString();
    }

    public static String encodeMD5(String inStr) {
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes;
        try {
            md5Bytes = MessageDigest.getInstance("MD5").digest(byteArray);

            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertToString(InputStream in) throws Exception {
        if(in == null) {
            return "";
        }
        BufferedReader bf = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
        StringBuilder r = new StringBuilder();
        String str;
        while ((str = bf.readLine()) != null) {
            r.append(str);
        }
        return r.toString();
    }

    public static void main(String[] arg) throws Exception {
        System.out.println(encodeMD5("admin"));
    }

    public static String convertUrl (String url, Map<String, String> map) {
        Console.info("weixin log convert url now");

        if (null == map) {
            return url;
        }
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        List<String> keyList = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            keyList.add(entry.getKey());
            Console.info("roy log convert url key is " + entry.getKey());
            Console.info("roy log convert url value is " + entry.getValue());
        }
        String paramStr = mapToString(map);
        // 如果不包含?
        if (!url.contains("?")) {
            url = url + "?";
        }
        // 如果结尾不是&?
        char lastChar = url.charAt(url.length() - 1);
        if ('&' != lastChar && '?' != lastChar) {
            paramStr = "&" + paramStr;
        }
        url = url + paramStr;
        Console.info("weixin log convert url is " + url);
        return url;
    }

    public static String convertObjectUrl (String url, Map<String, Object> map) {
        Console.info("weixin log convert object url now");
        StringBuffer sb = new StringBuffer(url);
        sb.append("?");
        boolean isFirst = true;
        for (String key : map.keySet()) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append("&");
            }
            Object value = map.get(key);
            sb.append(key);
            sb.append("=");
            if (!StringUtils.isEmpty(value)) {
                sb.append(value);
            }
        }
        Console.info("weixin log convert object url is " + sb.toString());
        return sb.toString();
    }

    // 将参数转为字符串
    private static String mapToString(Map<String, String> map) {
        if (null == map || map.isEmpty()) {
            return "";
        }
        Set<String> set = map.keySet();
        StringBuffer buf = new StringBuffer();
        for (String key : set) {
            String value = map.get(key);
            if (null != value) {
                String valueEn = null;
                try {
                    valueEn = URLEncoder.encode(value, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                buf.append(key).append("=").append(valueEn).append("&");
            }
        }
        buf.deleteCharAt(buf.length() - 1);
        return buf.toString();
    }
}
