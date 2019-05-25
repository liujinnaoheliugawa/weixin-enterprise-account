package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.menu.*;
import org.dom4j.Element;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class WeixinMenuUtil {

    private static String envName = "";
    private static long lastModified = 0L;
    private static Menu menu = new Menu();
    private static final String ENV_FILE = "/weixin_menu.xml";

    static {
        initParams(null);
    }

    public static synchronized void initParams(String fname) {
        try {
            if (fname == null || fname.equals("")) {
                fname = ENV_FILE;
            }
            String path = WeixinMenuUtil.class.getResource(fname).getPath();

            File file = new File(URLDecoder.decode(path, "UTF-8"));

            // 自动加载最新的文件
            long modified = file.lastModified();
            if (modified != lastModified) {
                lastModified = modified;
            } else {
                return;
            }

            if (file != null) {
                XMLParse xp = new XMLParse();
                xp.setFile(file);
                xp.parseXML();
                xp.search("//param-configuration/server-config");

                while (xp.next()) {
                    if (!isvalidNode(xp)) {
                        continue;
                    }

                    @SuppressWarnings("unchecked")
                    List<Element> paramList = xp.getSubElementList("property");
                    for (int i = 0; i < paramList.size(); i++) {
                        Element ele = paramList.get(i);

                        if ("weixin_menu".equals(ele.attributeValue("name"))) {

                            parseMenuInfo(ele);

                        }

                    }
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static boolean isvalidNode(XMLParse xp) {
        boolean flag = false;
        try {
            if (xp.getAttributeValue("default").equals("true")) {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
            System.err.println(e.getStackTrace());
        }

        return flag;
    }

    /**
     * 从xml文件中解析出来menu信息
     *
     * @param ele
     */
    @SuppressWarnings("unchecked")
    private static void parseMenuInfo(Element ele) {

        List<Button> subButtons = null;
        List<Button> buttons = new ArrayList<Button>();

        // 遍历menu节点
        List<Element> buttonList = ele.elements("button");

        Button button = null;
        ComplexButton complexButton = null;
        List<Element> subButtonList = null;

        for (Element buttonElement : buttonList) {
            subButtonList = buttonElement.elements("sub_button");
            if (null == subButtonList || subButtonList.isEmpty()) {
                button = createButton(buttonElement);
                buttons.add(button);
            } else {
                subButtons = new ArrayList<Button>();
                // 复合按钮只有名称属性
                complexButton = new ComplexButton();
                complexButton.setName(buttonElement.attributeValue("name"));
                for (Element subButtonElement : subButtonList) {

                    button = createButton(subButtonElement);

                    subButtons.add(button);

                }
                complexButton.setSub_button(subButtons.toArray(new Button[0]));
                buttons.add(complexButton);
            }
        }

        menu.setButton(buttons.toArray(new Button[0]));
    }

    /**
     * 创建一个新的button
     *
     * @param element
     * @return Button
     */
    private static Button createButton(Element element) {

        String type = Utils.trimString(element.attributeValue("type"));
        String name = Utils.trimString(element.attributeValue("name"));
        String url = Utils.trimString(element.attributeValue("url"));
        String key = Utils.trimString(element.attributeValue("key"));
        String media_id = Utils.trimString(element.attributeValue("media_id"));

        if(type.isEmpty()){
            System.err.println("无效的按钮定义:type不能为空!");
            return null;
        }

        //type和url/media_id约束检查
        if("view".equals(type))
        {
            return createViewButton(type, name, url);

        }
        else if("media_id".equals(type) || "view_limited".equals(type))
        {
            return createMediaButton(type, name, media_id);
        }
        else
        {
            return createCommonButton(type, name, key);
        }

    }

    /**
     * 创建通用 button，
     * <p>菜单接口明细参见：https://mp.weixin.qq.com/wiki/13/43de8269be54a0a6f64413e4dfa94f39.html
     * @param type 类型
     * @param name 名称
     * @param key
     * @return
     */
    private static Button createCommonButton(String type, String name, String key) {
        if(key.isEmpty())
        {
            System.err.println("无效的按钮定义:type=" + type + "时，key不能为空!");
            return null;
        }

        CommonButton button = new CommonButton();
        button.setType(type);
        button.setName(name);
        button.setKey(key);
        return button;
    }

    /**
     * 创建媒体类型的菜单
     * <p>菜单接口明细参见：https://mp.weixin.qq.com/wiki/13/43de8269be54a0a6f64413e4dfa94f39.html
     * @param type
     * @param name
     * @param media_id
     * @return
     */
    private static Button createMediaButton(String type, String name, String media_id) {
        if(media_id.isEmpty())
        {
            System.err.println("无效的按钮定义:type=" + type + "时，media_id不能为空!");
            return null;
        }
        MediaButton button = new MediaButton();
        button.setType(type);
        button.setName(name);
        button.setMedia_id(media_id);
        return button;
    }

    /**
     * 创建跳转菜单
     * <p>菜单接口明细参见：https://mp.weixin.qq.com/wiki/13/43de8269be54a0a6f64413e4dfa94f39.html
     * @param type
     * @param name
     * @param url
     * @return
     */
    private static Button createViewButton(String type, String name, String url) {
        if(null == url || url.isEmpty())
        {
            System.err.println("无效的按钮定义:type=" + type + "时，url不能为空!");
            return null;
        }
        //替换全局变量
        if(null != url && !url.isEmpty())
        {
            url = url.replaceAll("\\$\\{WEIXIN_APP_ID\\}", "appId");
            url = url.replaceAll("\\$\\{LOOGEAR_URL\\}", "logger_url");
            url = url.replaceAll("\\$\\{WEIXIN_AUTH_FOLDER\\}", "/auth");
            System.err.println("url=" + url);
        }

        ViewButton button = new ViewButton();
        button.setType(type);
        button.setName(name);
        button.setUrl(url);
        return button;
    }

    public static Menu getMenu() {

        initParams(null);
        return menu;
    }
}
