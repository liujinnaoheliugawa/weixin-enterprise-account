package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class IgnoreDTDEntityResolver  implements EntityResolver {
    public InputSource resolveEntity (String publicId, String systemId)
    {
        return new InputSource(new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
    }
}
