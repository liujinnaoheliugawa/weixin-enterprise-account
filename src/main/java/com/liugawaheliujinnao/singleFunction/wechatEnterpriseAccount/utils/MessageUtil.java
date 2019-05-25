package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.req.ImageBean;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.req.ImageMessage;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.res.Article;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.res.NewsMessage;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.XMLReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Serializable;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.*;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class MessageUtil {

    private static final Log Console = LogFactory.getLog(MessageUtil.class);

    /**文本请求**/
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**图片请求**/
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**链接请求**/
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**地理位置请求**/
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**音频请求**/
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**视频请求**/
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";

    /**推送请求**/
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**订阅事件**/
    public static final String EVENT_TYPE_SUBSCRIBE = "SUBSCRIBE";

    /**取消订阅事件**/
    public static final String EVENT_TYPE_UNSUBSCRIBE = "UNSUBSCRIBE";

    //已经关注的用户扫码
    public static final String EVENT_TYPE_SCAN = "SCAN";

    //上报地理位置
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    /**点击事件**/
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**HTML**/
    public static final String EVENT_TYPE_VIEW = "VIEW";

    /**文本返回**/
    public static final String RES_MESSAGE_TYPE_TEXT = "text";

    /**音乐返回**/
    public static final String RES_MESSAGE_TYPE_MUSIC = "music";

    /**图文返回**/
    public static final String RES_MESSAGE_TYPE_NEWS = "news";
    public static final String RES_MESSAGE_TYPE_IMAGE = "image";
    public static final String RES_MESSAGE_TYPE_VOICE = "voice";
    public static final String RES_MESSAGE_TYPE_VIDEO = "video";

    /**发送模板消息返回**/
    public static final String RES_MESSAGE_TYPE_TEMPLATEMSG = "templatesendjobfinish";

    //自动回复action开始
    public static final String RES_MESSAGE_ACTION_MESSAGE = "message";//固定消息
    public static final String RES_MESSAGE_ACTION_PERSONAL_QRCODE = "Personal_Qrcode";//专属二维码
    public static final String RES_MESSAGE_ACTION_CUSTOMER_SERVICE = "Customer_Service";//在线客服
    //自动回复action结束

    //微信菜单自定义点击事件key：
    public static final String MENU_CLICK_KEY_CC = "CONTACT_CUSTOMER_SERVICE";//勾搭小硕

    /**由于xstream框架本身并不支持CDATA块的生成，40~62行代码是对xtream做了扩展，使其支持在生成xml各元素值时添加CDATA块。**/
    private static XStream xStream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            PrettyPrintWriter printWriter = new MyPrettyPrintWriter(out);
            return printWriter;
        }
    });


    public static Map parseXml(String xml) throws Exception {
        /**将解析结果存储在HashMap中**/
        Document doc = DocumentHelper.parseText(xml);
        Element root = doc.getRootElement();
        Map<String, Object> map = new HashMap<>();
        List<Element> elementList = root.elements();
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
            Console.debug("key= " + e.getName() + ", value=" + e.getText());
        }
        return map;
    }

    /**
     * 解析微信发来的请求(XML)
     *
     * @param req
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map parseXml(HttpServletRequest req) throws Exception {
        /**将解析结果存储在HashMap中**/
        Map map = new HashMap();
        Console.info("weixin log the problem may be going to happen");
        /**从request中取得输入流**/
        InputStream inputStream = req.getInputStream();

        Console.info("weixin log 1");
        /**读取输入流**/
        SAXReader reader = new SAXReader();
        XMLReader xmlReader = reader.getXMLReader();

        xmlReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        Console.info("weixin log encoding type is " + reader.getEncoding());
        Document document = reader.read(inputStream);

        Console.info("weixin log problem is definitely here, input stream is " + inputStream);
        Console.info("weixin log 2");

        /**得到xml根元素**/
        Element root = document.getRootElement();
        Console.info("xml root name is " + root.getName() + ", text is " + root.getText());
        Console.info("weixin log 3");

        /**得到根元素的所有节点**/
        List<Element> elementList = root.elements();
        Console.info("weixin log 4");

        /**是否加密**/
        String encryptType = req.getParameter("encrypt_type");
        Console.info("weixin log encrypt type is " + encryptType);
        String msgSignature = req.getParameter("msg_signature");
        Console.info("weixin log message signature is " + msgSignature);

        String encryptString = null;
        if (StringUtils.isEmpty(encryptType) || "raw".equals(encryptType)) { /**消息未被加密**/
            /**遍历所有子节点**/
            Console.info("weixin log This is xml log");
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
                Console.info("weixin log name is " + e.getName() + ", text is " + e.getText());
            }
        }
        else if ("aes".equals(encryptType)) { /**消息被加密**/
            for (Element e : elementList) {
                if (("Encrypt").equals(e.getName())) {
                    encryptString = e.getText();
                    Console.info("weixin log entryptString is " + encryptString);
                }
            }
            String uri = req.getRequestURL().toString();
            Map parameterMap = req.getParameterMap();
            Iterator<Map.Entry> it = parameterMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = it.next();
                Console.info("weixin log parameter key is " + entry.getKey() + ", parameter value is " + entry.getValue());
            }

            Console.info("weixin log url is " + uri);

            String token = "token";
            String encodingAESKey = "encodingAesKey";
            String appId = "appId";
            String nonce = req.getParameter("nonce");

            Console.info("weixin log token is " + token);
            Console.info("weixin log encodingAESKey is " + encodingAESKey);
            Console.info("weixin log appId is " + appId);
            Console.info("weixin log nonce is " + nonce);
            WXBizMsgCrypt bizMsgCrypt = new WXBizMsgCrypt(token, encodingAESKey, appId);
            Date date = new Date();
            String timestamp = date.getTime() + "";
            String decryptMsg = bizMsgCrypt.decryptMsg(msgSignature, timestamp, nonce, encryptString);
            Console.info("weixin log should be decrypted, message is  " + decryptMsg);
        }

        /**释放资源**/
        inputStream.close();
        inputStream = null;

        return map;
    }

    /**
     * 创建是文本消息
     * @param fromUserName
     * @param toUserName
     * @param text
     * @return
     */
    public static String createTextMessage(String fromUserName, String toUserName, String text) {
        /** 回复文本消息 **/
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.RES_MESSAGE_TYPE_TEXT);
        textMessage.setFuncFlag(0);

        textMessage.setContent(text);

        String resMessage = MessageUtil.textMessageToXml(textMessage);
        return resMessage;
    }

    /**
     * 文本消息对象转换成xml
     *
     * @param textMessage 文本消息对象
     * @return xml
     **/
    public static String textMessageToXml(TextMessage textMessage) {
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    private static class BaseMessage implements Serializable {
        /**接收方帐号(openId)**/
        private String ToUserName;

        /**开发者微信账号**/
        private String FromUserName;

        /**消息创建时间**/
        private long CreateTime;

        /**消息类型**/
        private String MsgType;

        /**星标刚收到的消息**/
        private int FuncFlag;

        public String getToUserName() {
            return ToUserName;
        }

        public void setToUserName(String toUserName) {
            ToUserName = toUserName;
        }

        public String getFromUserName() {
            return FromUserName;
        }

        public void setFromUserName(String fromUserName) {
            FromUserName = fromUserName;
        }

        public long getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(long createTime) {
            CreateTime = createTime;
        }

        public String getMsgType() {
            return MsgType;
        }

        public void setMsgType(String msgType) {
            MsgType = msgType;
        }

        public int getFuncFlag() {
            return FuncFlag;
        }

        public void setFuncFlag(int funcFlag) {
            FuncFlag = funcFlag;
        }

        public BaseMessage() {

        }

        public BaseMessage(String fromUserName, String toUserName, String msgType) {
            this.FromUserName = fromUserName;
            this.ToUserName = toUserName;
            this.CreateTime = new Date().getTime();
            this.MsgType = msgType;
        }

        public BaseMessage(String fromUserName, String toUserName, Integer funcFlag, String msgType) {
            this.FromUserName = fromUserName;
            this.ToUserName = toUserName;
            this.FuncFlag = funcFlag;
            this.CreateTime = new Date().getTime();
            this.MsgType = msgType;
        }
    }

    private static class TextMessage extends BaseMessage implements Serializable {

        /**回复的消息内容**/
        private String Content;

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

        public TextMessage() {

        }

        public TextMessage(String fromUserName, String toUserName, Integer funcFlag, String msgType) {
            super(fromUserName, toUserName, funcFlag, msgType);
        }
    }


    private static class MyPrettyPrintWriter extends PrettyPrintWriter {
        /**对所有xml节点的转换都增加CDATA标记**/
        boolean cdata = true;

        public MyPrettyPrintWriter(Writer writer) {
            super(writer);
        }

        @Override
        protected void writeText(QuickWriter writer, String text) {
            if (cdata) {
                writer.write("<![CDATA[");
                writer.write(text);
                writer.write("]]>");
            } else {
                writer.write(text);
            }
        }
    }

    /**
     * 创建图片消息
     * @param fromUserName 接收方者的openid
     * @param toUserName 应用APPID
     * @param imageBean 图片列表
     * @return
     */
    public static String createImageMessage(String fromUserName, String toUserName, ImageBean imageBean) {
        ImageMessage imgMessage = new ImageMessage();
        imgMessage.setToUserName(fromUserName);
        imgMessage.setFromUserName(toUserName);
        imgMessage.setCreateTime(new Date().getTime());
        imgMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
        imgMessage.setImage(imageBean);

        String resContent = MessageUtil.messageToXml(imgMessage);
        return resContent;
    }

    /**
     * 图文消息对象转换成xml
     *
     * @param imageMessage 图文消息对象
     * @return
     */
    public static String messageToXml(ImageMessage imageMessage) {
        //使用注解的方式定义别名
        //xStream.alias("xml", ImageMessage.class);
        xStream.autodetectAnnotations(true);
        return xStream.toXML(imageMessage);
    }

    /**
     * 创建图文消息
     * @param fromUserName 接收方者的openid
     * @param toUserName 应用APPID
     * @param articleList 文章列表
     * @return
     * @throws Exception
     */
    public static String createArticleMessage(String toUserName, String fromUserName, List<Article> articleList) throws Exception {

        Console.debug("创建图文消息");
        NewsMessage posterMessage = new NewsMessage();
        posterMessage.setToUserName(toUserName);
        posterMessage.setFromUserName(fromUserName);
        posterMessage.setCreateTime(new Date().getTime());
        posterMessage.setMsgType(MessageUtil.RES_MESSAGE_TYPE_NEWS);

        /** 设置图文消息个数 **/
        posterMessage.setArticleCount(articleList.size());
        /** 设置图文消息包含的图文集合 **/
        posterMessage.setArticles(articleList);
        /** 将图文消息转换为xml字符串 **/
        String resContent = MessageUtil.newsMessageToXml(posterMessage);
        Console.debug("图文消息:" + resContent);
        return resContent;
    }

    /**
     * 图文消息对象转换成xml
     *
     * @param newsMessage 图文消息对象
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xStream.alias("xml", newsMessage.getClass());
        xStream.alias("item", new Article().getClass());
        return xStream.toXML(newsMessage);
    }

    /**
     * 转发消息到多客服系统
     * @param toUser 发起会话的粉丝openId
     * @param FromUser appID
     * @return
     */
    public static String customerMessage(String toUser, String FromUser) {
        Console.debug("转发消息到多客服系统");
        long longDate = System.currentTimeMillis();
        String sMessage = MessageFormat.format(
                "<xml><ToUserName><![CDATA[{0}]]></ToUserName><FromUserName><![CDATA[{1}]]></FromUserName><CreateTime>{2}</CreateTime><MsgType><![CDATA[transfer_customer_service]]></MsgType></xml>",
                toUser, FromUser, longDate);
        Console.debug("message:"+sMessage);
        return sMessage;
    }
}
