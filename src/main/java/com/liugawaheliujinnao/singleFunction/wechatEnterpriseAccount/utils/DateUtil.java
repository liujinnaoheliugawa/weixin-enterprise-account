package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class DateUtil {

    public final static String DATATIME_FORMAT_DAY = "yyyy-MM-dd";

    public static String dateToString(Date date, String formate){

        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        return sdf.format(new Date());

    }
    /**
     * 将日期格式化为天
     * @param sformat
     * @return
     */
    public static String getNowDay(Date date, String sformat) {

        if (null == sformat || sformat.isEmpty()) {
            sformat = DATATIME_FORMAT_DAY;
        }
        SimpleDateFormat s = new SimpleDateFormat(sformat);
        String str = s.format(date);
        return str;
    }
}
