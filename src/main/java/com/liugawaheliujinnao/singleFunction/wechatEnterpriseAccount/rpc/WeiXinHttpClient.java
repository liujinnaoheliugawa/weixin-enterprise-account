package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class WeiXinHttpClient {

    private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    private static CloseableHttpClient client = null;
    private static RequestConfig requestConfig = null;
    private static volatile boolean shutdown = false;
    public static synchronized CloseableHttpClient getClientInstance() {
        if (client == null) {
            synchronized (WeiXinHttpClient.class) {
                cm.setMaxTotal(0);
                // Increase default max connection per route to 20
                cm.setDefaultMaxPerRoute(0);
                cm.setDefaultConnectionConfig(ConnectionConfig.custom()
                        .setMalformedInputAction(CodingErrorAction.IGNORE)
                        .setUnmappableInputAction(CodingErrorAction.IGNORE)
                        .setCharset(Consts.UTF_8).build());
                requestConfig = RequestConfig.custom()
                        .setSocketTimeout(0)
                        .setConnectTimeout(0)
                        .setConnectionRequestTimeout(0)
                        .build();

                HttpClientBuilder builder = HttpClients.custom();
                //builder.setConnectionManager(connManager)
                if (!StringUtils.isEmpty("")&&1>0) {
                    builder.setRoutePlanner(new DefaultProxyRoutePlanner(
                            new HttpHost("",
                                    0)));
                }

                client = builder.setConnectionManager(cm).build();
                System.out.println("HttpClient initial complete...");
                startIdleConnectionMonitorThread();
            }
        }
        return client;
    }

    private static void  startIdleConnectionMonitorThread(){
        new Thread(){
            @Override
            public void run() {
                try {
                    while (!shutdown) {
                        synchronized (this) {
                            wait(300000);
                            // Close expired connections
                            cm.closeExpiredConnections();
                            // Optionally, close connections
                            // that have been idle longer than 30 sec
                            cm.closeIdleConnections(5, TimeUnit.MINUTES);
                            System.out.println("Clear HttpClient Idle Connection...");
                        }
                    }
                } catch (InterruptedException ex) {
                    // terminate
                }
            }

        }.start();
    }

    public static <T> T  get(String url, Object obj) throws Exception{
        CloseableHttpClient httpClient = getClientInstance();
        Class<T> clazz = null;
        TypeReference<T> reference = null;
        if (obj instanceof Class) {
            clazz = (Class<T>)obj;
        } else if (obj instanceof TypeReference) {
            reference = (TypeReference<T>)obj;
        }
        try {
            HttpGet post = new HttpGet(url);
            post.setConfig(requestConfig);

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        if (clazz != null) {
                            return mapper.readValue(instream, clazz);
                        } else if (reference != null) {
                            return mapper.readValue(instream, reference);
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    } finally {
                        if(instream != null) {
                            instream.close();
                        }
                    }
                }
            } finally {
                if(response!=null)response.close();
            }
            return null;
        }catch(Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    public static String get(String url) throws Exception{
        try {
            HttpGet post = new HttpGet(url);
            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);
            excuteRequest(post, r);
            return r.toString();
        }catch(Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    public static String post(String url, Map<String, String> params) throws Exception{
        try {
            HttpPost post = new HttpPost(url);
            StringBuilder r = new StringBuilder();
            Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
            List<NameValuePair> formparams = new ArrayList<>();

            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                System.out.println(entry.getKey()+":"+entry.getValue());
                formparams.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
            post.setConfig(requestConfig);
            post.setEntity(new UrlEncodedFormEntity(formparams, Consts.UTF_8));
            excuteRequest(post, r);
            return r.toString();
        }catch(Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    public static String post(String url) throws Exception{
        try {
            HttpPost post = new HttpPost(url);
            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);
            excuteRequest(post, r);
            return r.toString();
        }catch(Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    public static String postStr(String url, String postStr) throws Exception{
        try {
            HttpPost post = new HttpPost(url);

            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);
            post.setEntity(new StringEntity(postStr, Consts.UTF_8));
            excuteRequest(post, r);
            return r.toString();
        }catch(Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    public static String postJSonStr(String url, String postStr) throws Exception{
        try {
            HttpPost post = new HttpPost(url);

            StringBuilder r = new StringBuilder();
            post.setConfig(requestConfig);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(postStr, Consts.UTF_8));
            excuteRequest(post, r);
            return r.toString();
        }catch(Exception e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    private static void excuteRequest(Object obj, StringBuilder r) {
        HttpGet get = null;
        HttpPost post = null;
        if (obj instanceof  HttpGet) {
            get = (HttpGet) obj;
        } else if (obj instanceof HttpPost) {
            post = (HttpPost) obj;
        }
        BufferedReader bf;
        CloseableHttpClient httpClient = getClientInstance();
        CloseableHttpResponse response = null;
        try {
            response = get != null ? httpClient.execute(get) : httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                bf = new BufferedReader(new InputStreamReader(instream,Consts.UTF_8));
                try {
                    String str = "";
                    while ((str = bf.readLine()) != null) {
                        r.append(str);
                    }
                } finally {
                    instream.close();
                }
            }
        } catch (IOException e) {

        } finally {
            try {
                if (response != null) response.close();
            } catch (IOException e) {

            }
        }
    }
}
