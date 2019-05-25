package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.menu.Menu;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class WeiXinUtil {

    /**菜单创建(POST) 开发者限100（次/天）测试**/
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            /**创建SSLContext对象，并使用我们制定的信任管理器初始化**/
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new SecureRandom());

            /**从上述SSLContext对象中得到SSLSocketFactory对象**/
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            /**设置请求方式(GET/POST)**/
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            /**当有数据需要提交时**/
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                /**注意编码格式，防止中文乱码**/
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            /**将返回的输入流转换成字符串**/
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();

            /**释放资源**/
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            System.err.println("Weixin log weixin server connection timed out.");
        } catch (Exception e) {
            System.err.println("Weixin log htps request error");
        }
        return jsonObject;
    }

    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;

        System.err.println("weixin log result is " + result);

        /**拼装创建菜单的url**/
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        System.err.println("weixin log menu url is " + url);

        /**将菜单对象转换成json字符串**/
        String jsonMenu = JSONObject.fromObject(menu).toString();
        System.err.println("weixin log json menu is " + jsonMenu);

        /**调用接口创建菜单**/
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
        System.err.println("weixin log jsonObject is " + jsonObject);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                System.err.println("创建菜单失败");
            }
        }
        return result;
    }

    /**
     * 根据附加信息获取应用ID
     * @param attach
     * @param defalutValue
     * @param targetValue
     * @return
     */
    public static String getWXPayParam( String attach, String defalutValue, String targetValue) {
        String appId = defalutValue;
        if( null != attach && attach.startsWith("prefix") ){
            appId = targetValue;
        }
        return appId;
    }
}
