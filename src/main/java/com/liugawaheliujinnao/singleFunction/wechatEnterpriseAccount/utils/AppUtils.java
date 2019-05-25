package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class AppUtils {

    public static Map<String, String> prepareParameters(MultivaluedMap<String, String> queryParameters) {

        Map<String, String> parameters = new HashMap<String, String>();

        Iterator<String> it = queryParameters.keySet().iterator();


        while (it.hasNext()) {
            String theKey = it.next();
            parameters.put(theKey, queryParameters.getFirst(theKey));
        }

        return parameters;

    }

    public static <T> T convertRequestBody(HttpServletRequest request, Class<T> cls){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(request.getInputStream(),cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertBean(Object target){
        StringWriter sw = new StringWriter();
        try {
            JsonGenerator gen = new JsonFactory().createGenerator(sw);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(gen, target);
            gen.close();
            return sw.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
            e.printStackTrace();
        }
        return null;

    }
}
