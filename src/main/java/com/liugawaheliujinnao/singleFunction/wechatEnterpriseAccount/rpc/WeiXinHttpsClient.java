package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.Utils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class WeiXinHttpsClient {

    static int TIME_OUT = 0;
    static boolean TRUE = true;
    static boolean FALSE = false;
    static String GET = "";
    static String POST = "";
    static String CHARSET_KEY = "";
    static String CHARSET = "";
    static String CONNECTION_KEY = "";
    static String ALIVE_CONNECTION = "";
    static String CONTENT_TYPE_KEY = "";
    static String TEXT_XML = "";

    public static File getPic(String urlStr) {
        try {
            String PIC_SAVE_PATH = "xxx.jpeg";
            HttpsURLConnection conn = getConnection(urlStr, true);
            InputStream inStream = conn.getInputStream();
            byte[] data = readInputStream(inStream);
            File imageFile = new File(PIC_SAVE_PATH);
            FileOutputStream outStream = new FileOutputStream(imageFile);
            outStream.write(data);
            outStream.close();
            imageFile.mkdirs();
            return imageFile;
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static File getPic(String url, String path, String name) {
        try {

            if (null == path || path.isEmpty()) {
                path = "file_upload_dir";
                path = (path.endsWith(File.separator)) ? path : path + File.separator;
            }

            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
                Runtime.getRuntime().exec("chmod +w " + path);
            }

            URL u = new URL(url);
            SSLContext sslc = SSLContext.getInstance("TLSv1");
            sslc.init(
                    null,
                    new TrustManager[] { new WeiXinHttpsClient().new MyTrustManager() },
                    new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslc
                    .getSocketFactory());
            HttpsURLConnection
                    .setDefaultHostnameVerifier(new WeiXinHttpsClient().new MyHostnameVerifier());
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            if (url.toUpperCase().startsWith("https"))
                conn = (HttpsURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            InputStream inStream = conn.getInputStream();
            byte[] data = readInputStream(inStream);

            File imageFile = new File(path + name );
            if(!imageFile.exists() ){
                imageFile.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(imageFile);
            outStream.write(data);
            outStream.close();

            return imageFile;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String get(String urlStr){
        StringBuilder r = new StringBuilder();
        try {
            HttpsURLConnection conn = getConnection(urlStr, true);
            BufferedReader bf;
            InputStream instream = conn.getInputStream();
            bf = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
            append(instream, r, bf);
        }catch (Exception ex) {
            ex.printStackTrace();

        }
        return r.toString();
    }

    private static void append(InputStream instream, StringBuilder r, BufferedReader bf) {
        try {
            try {
                String str;
                while ((str = bf.readLine()) != null) {
                    r.append(str);
                }
            } finally {
                if (instream != null) {
                    instream.close();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static <T> T  get(String urlStr, Object obj){
        Class<T> clazz = null;
        TypeReference<T> reference = null;
        if (obj instanceof  Class) {
            clazz = (Class<T>) obj;
        } else if (obj instanceof  TypeReference) {
            reference = (TypeReference<T>) obj;
        }

        try {
            HttpsURLConnection conn = getConnection(urlStr, true);
            InputStream instream = conn.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                if (clazz != null) {
                    return mapper.readValue(instream, clazz);
                } else {
                    return mapper.readValue(instream, reference);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(instream != null) {
                    instream.close();
                }
            }
            // Create a response handler

        }catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public static <T> T post(String urlStr, TypeReference cls, String params) {
        try {
            FakeX509TrustManager.allowAllSSL(null);
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", "165");

            conn.getOutputStream().write(params.getBytes());
            InputStream inStrm = conn.getInputStream();

            // Create a response handler

            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return mapper.readValue(inStrm, cls);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(inStrm != null) {
                    inStrm.close();
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static <T> T post(String urlStr, Map<String, String> headers, TypeReference cls, String params) {
        try {
            FakeX509TrustManager.allowAllSSL(null);
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", "165");
            if (headers != null) {
                for (String key : headers.keySet()) {
                    conn.setRequestProperty(key, headers.get(key));
                }
            }
            conn.getOutputStream().write(params.getBytes());
            InputStream inStrm = conn.getInputStream();

            // Create a response handler

            ObjectMapper mapper = new ObjectMapper();
            try {
                String result = Utils.convertStreamToString(inStrm);
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return mapper.readValue(result, cls);
                //return mapper.readValue(inStrm, cls);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(inStrm != null) {
                    inStrm.close();
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private class MyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    /**
     * Created by will on 14-10-10.
     */
    public static class FakeX509TrustManager implements X509TrustManager {

        private static final X509Certificate[] _AcceptedIssuers = new
                X509Certificate[] {};
        private static TrustManager[] trustManagers;

        public static void allowAllSSL(HttpsURLConnection connection) {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                public boolean verify(String arg0, SSLSession arg1) {
                    // TODO Auto-generated method stub
                    return true;
                }

            });

            SSLContext context = null;
            if (trustManagers == null) {
                trustManagers = new TrustManager[] { new FakeX509TrustManager() };
            }

            try {
                context = SSLContext.getInstance("TLS");
                context.init(null, trustManagers, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        }

        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public boolean isClientTrusted(X509Certificate[] chain) {
            return true;
        }

        public boolean isServerTrusted(X509Certificate[] chain) {
            return true;
        }

        public X509Certificate[] getAcceptedIssuers() {
            return _AcceptedIssuers;
        }

    }

    private class MyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static HttpsURLConnection getConnection(String urlStr, boolean flag) {
        try {
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoOutput(TRUE);
            conn.setUseCaches(FALSE);
            if (flag == true) {
                conn.setRequestMethod(GET);
            } else {
                conn.setRequestMethod(POST);
            }
            conn.setRequestProperty(CHARSET_KEY, CHARSET);
            conn.setRequestProperty(CONNECTION_KEY, ALIVE_CONNECTION);
            conn.setRequestProperty(CONTENT_TYPE_KEY, TEXT_XML);
            return conn;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        /**创建一个Buffer字符串**/
        byte[] buffer = new byte[1024];
        /**每次读取的字符串长度，如果为-1，代表全部读取完毕**/
        int len = 0;
        /**使用一个输入流从buffer里把数据读取出来**/
        while( (len=inStream.read(buffer)) != -1 ){
            /**用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度**/
            outStream.write(buffer, 0, len);
        }
        /**关闭输入流**/
        inStream.close();
        /**把outStream里的数据写入内存**/
        return outStream.toByteArray();
    }
}
