package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.impl;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.api.WeixinApi;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse.WxEventResponse;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse.WxMessage;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse.WxUserMessageResponse;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.behavior.WeiXinClick;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.behavior.WeiXinUserClick;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.behavior.WeiXinUserView;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.behavior.WeiXinView;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.callcenter.KfInfo;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.callcenter.KfListInfo;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel.WeiXinChannel;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.TempMediaResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.group.WeixinGroup;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.logger.MyMessage;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.req.ImageBean;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.res.Article;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.token.AccessToken;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.SnsUser;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.User;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.WeiXinUserInfo;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.WeiXinHttpsClient;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.*;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.MessageUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.*;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
@Service
public class CoreServiceImpl implements CoreService {

    private final Log LOG = LogFactory.getLog(CoreServiceImpl.class);

    // 默认问候语
    private String greetings = "";

    private String ccRegards;

    private String qrWaiting;

    private String ccOutDuty = "";

    @Autowired
    private SnsUserService snsUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private WeiXinTokenService tokenService;

    @Autowired
    private WeiXinChannelService channelService;

    @Autowired
    private WeiXinClickService clickService;

    @Autowired
    private WeiXinUserClickService userClickService;

    @Autowired
    private WeiXinViewService viewService;

    @Autowired
    private WeiXinUserViewService userViewService;

    @Autowired
    private MyMessageService myMessageService;

    @Autowired
    private MyMsgTemplateService templateService;

    @Autowired
    private MediaApiService mediaApiService;

    @Autowired
    private WeiXinUserInfoService userInfoService;

    @Autowired
    private AutoResponseService autoResponseService;

    @Autowired
    private CcApiService ccApiService;

    private AccessToken token = null;

    @Override
    public String processRequest(HttpServletRequest req) {
        // 注意，由于spring默认是单实例的，必须重置 resContent
        String resContent = "";
        try {
            // xml请求解析
            Map<String, String> reqMap = MessageUtil.parseXml(req);
            resContent = processMsg(reqMap);

        } catch (Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            LOG.error("======weixin log error log is======");
            LOG.error(str);
        }
        LOG.info("weixin log res message is " + resContent);
        return resContent;
    }

    public String processMsg(Map<String, String> reqMap) throws Exception {
        String resContent;
        String ticket = getTicket(reqMap);
        LOG.info("weixin log ticket is " + ticket);

        String fromUserName = reqMap.get("FromUserName");// 发送方账号(open_id)
        String toUserName = reqMap.get("ToUserName");// 公众账号
        String msgType = reqMap.get("MsgType"); // 消息类型
        String event = reqMap.get("Event"); // 微信事件
        String content = reqMap.get("Content"); // 消息内容
        String eventKey = reqMap.get("EventKey");// 点击事件的key值

        // 将类型转换成小写
        if (null != msgType && !msgType.isEmpty()) {
            msgType = msgType.toLowerCase();
        }
        // 将事件转换成大写
        if (null != event && !event.isEmpty()) {
            event = event.toUpperCase();
        }
        content = (null == content || content.isEmpty()) ? "" : content;
        LOG.info("fromUserName=" + fromUserName + "||toUserName=" + toUserName + "||msgType=" + msgType + "||event="
                + event + "||content=" + content + "||eventKey=" + eventKey);

        // 获取分享渠道号--目前没有使用
        WeiXinChannel channel = null;
        try{
            if (event != null && event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE) && ticket != null
                    && !ticket.equals("")) {
                /** 带参数的二维码 **/
                channel = channelService.getChannelByTicket(ticket);
                /** 渠道关注 **/
                if (channel != null) {
                    LOG.info("weixin log qr name is " + channel.getQr());
                    //修改用户分组
                    updateGroupId(channel.getGroup(), fromUserName);
                } else { /** 个人二维码关注 **/
                    // TODO
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        // 根据配置的后台数据，自动回复
        if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
            // 处理事件消息
            resContent = dealEventMsg(ticket, reqMap, fromUserName, toUserName, event, eventKey, channel);
        } else {
            // 处理普通消息
            resContent = dealNormalMsg(fromUserName, toUserName, msgType, content);
            // 用户发送的消息，必须要有响应
            if (null == resContent || resContent.isEmpty()) {
                resContent = MessageUtil.createTextMessage(fromUserName, toUserName, this.greetings);
            }
        }
        return resContent;
    }

    /**
     * 获取二维码分享者的ticket
     *
     * @param map
     * @return
     */
    private String getTicket(Map<String, String> map) {
        String ticket = null;
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            LOG.info("weixin log reqmap key is " + entry.getKey() + ", reqmap value is " + entry.getValue());
            if (entry.getKey().equals("Ticket")) {
                ticket = entry.getValue();
                LOG.info("weixin log ticket is " + ticket);
                return ticket;
            }
        }
        return ticket;
    }

    private String dealEventMsg(String ticket, Map<String, String> reqMap, String fromUserName, String toUserName,
                                String event, String eventKey, WeiXinChannel channel) throws Exception {

        String resContent = "";

        LOG.info("weixin log 进入的事件是 " + event);
        if (event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 订阅
            LOG.info("weixin log 下级流程 channel is " + channel + "ticket is " + ticket + "fromUserName is "
                    + fromUserName);

            // 创建新用户
            User newUser = userService.insertUser(channel, ticket, fromUserName);


            // 发送新增合伙人通知
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("username", newUser.getNickname());
            sendNewPartnerNofity(newUser, params);

            // 根据事件类型查询响应
            resContent = processEventRequest(fromUserName, toUserName, MessageUtil.EVENT_TYPE_SUBSCRIBE);
            //如果没有配置回复操作，则使用默认问候语
            if( null ==resContent || resContent.isEmpty() ){
                resContent = MessageUtil.createTextMessage(fromUserName, toUserName, this.greetings);
            }
        }
        // 取消订阅
        else if (event.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
            /** 取消关注用户将不再收到消息,因此不需要回复消息 **/
            SnsUser snsUser = getSnsUserByOpenid(fromUserName);
            if (snsUser != null) {
                User user = userService.getByUid(snsUser.getUser().getId());
                user.setSubscribed(false);
                userService.update(user);
            }

            // 根据事件类型查询响应
            resContent = processEventRequest(fromUserName, toUserName, MessageUtil.EVENT_TYPE_UNSUBSCRIBE);
        }
        // 自定义菜单点击事件
        else if (event.equals(MessageUtil.EVENT_TYPE_CLICK)) {

            eventKey = eventKey.toUpperCase();
            LOG.info("微信自定义菜单点击事件,event_key=" + eventKey);

            // 记录用户点击数据
            saveUserClickInfo(fromUserName, eventKey);

            // 根据事件类型查询响应
            resContent = processEventRequest(fromUserName, toUserName, MessageUtil.EVENT_TYPE_CLICK, eventKey);

            // 如果
            if (null == resContent || resContent.isEmpty()) {
                // 点击转到人工客服
                if (MessageUtil.MENU_CLICK_KEY_CC.equals(eventKey)) {
                    resContent = contactCallCenter(fromUserName, toUserName);
                }
                else
                {
                    //如果没有配置回复操作，则使用默认问候语
                    resContent = MessageUtil.createTextMessage(fromUserName, toUserName, ccRegards);
                }
            }
        }
        else if (event.endsWith(MessageUtil.EVENT_TYPE_SCAN)) {

            // 根据事件类型查询响应
            resContent = processEventRequest(fromUserName, toUserName, MessageUtil.EVENT_TYPE_SCAN);

            //如果没有配置回复操作，则使用默认问候语
            if( null ==resContent || resContent.isEmpty() ){
                resContent = MessageUtil.createTextMessage(fromUserName, toUserName, this.greetings);
            }
        }
        else if (event.endsWith(MessageUtil.EVENT_TYPE_VIEW)) {
            // 记录用户浏览数据
            saveUserViewInfo(fromUserName, eventKey);

            // 根据事件类型查询响应
            resContent = processEventRequest(fromUserName, toUserName, MessageUtil.EVENT_TYPE_VIEW);
        }
        // 发送模板消息返回
        else if (event.endsWith(MessageUtil.RES_MESSAGE_TYPE_TEMPLATEMSG)) {

            LOG.debug("模板消息执行返回了.....");

            Iterator<String> item = reqMap.keySet().iterator();
            String key = "";
            String value = "";

            while (item.hasNext()) {
                key = item.next();
                value = reqMap.get(key);
                LOG.debug(key + "=" + value);
            }

            // 暂时不处理
            /*
             * 参数 说明 ToUserName 公众号微信号 FromUserName 接收模板消息的用户的openid CreateTime
             * 创建时间 MsgType 消息类型是事件 Event 事件为模板消息发送结束 MsgID 消息id Status 发送状态:
             * success/user block(用户拒收)/system failed(其它原因)
             *
             * <xml> <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
             * <FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></
             * FromUserName> <CreateTime>1395658920</CreateTime>
             * <MsgType><![CDATA[event]]></MsgType>
             * <Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>
             * <MsgID>200163836</MsgID> <Status><![CDATA[success]]></Status>
             * </xml>
             */
        }
        return resContent;
    }

    /**
     * 保存用户点击的view类型的菜单数据
     *
     * @param fromUserName
     * @param eventKey
     * @throws Exception
     */
    private void saveUserViewInfo(String fromUserName, String eventKey) throws Exception {
        SnsUser snsUser = getSnsUserByOpenid(fromUserName);

        String WEIXIN_HOMEOAUTH = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "appId" + "&redirect_uri=" + "author_folder" + "/home.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
        String WEIXIN_PLAYOAUTH = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "appId" + "&redirect_uri=" + "author_folder" + "/play.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
        String WEIXIN_CENTEROAUTH = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "appId" + "&redirect_uri=" + "author_folder" + "/center.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";

        if (snsUser != null) {
            User user = snsUser.getUser();

            WeiXinView view = null;
            if (eventKey.equals(WEIXIN_HOMEOAUTH)) {
                view = viewService.getByName("home");
            } else if (eventKey.equals(WEIXIN_PLAYOAUTH)) {
                view = viewService.getByName("play");
            } else if (eventKey.equals(WEIXIN_CENTEROAUTH)) {
                view = viewService.getByName("center");
            }
            if (view != null) {
                WeiXinUserView userView = new WeiXinUserView();
                userView.setUser(user);
                userView.setView(view);
                userView.setClickTime(new Date());
                userViewService.save(userView);
            }
        }
    }

    /**
     * 保存用户点击的click类型的菜单数据
     *
     * @param fromUserName
     * @param eventKey
     * @throws Exception
     */
    private void saveUserClickInfo(String fromUserName, String eventKey) throws Exception {
        WeiXinClick click = clickService.getByKey(eventKey);
        SnsUser snsUser = getSnsUserByOpenid(fromUserName);
        if (null != snsUser) {
            User user = snsUser.getUser();
            WeiXinUserClick userClick = new WeiXinUserClick();
            userClick.setUser(user);
            userClick.setClick(click);
            userClick.setClickTime(new Date());
            userClickService.save(userClick);
        }
    }

    /**
     * 处理普通消息
     *
     * @param fromUserName
     * @param toUserName
     * @param msgType
     * @param content
     * @return
     * @throws Exception
     */
    private String dealNormalMsg(String fromUserName, String toUserName, String msgType, String content)
            throws Exception {

        String resContent = "";
        // 文本消息
        if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

            LOG.debug("根据消息类型加载数据库中预设的关键词");
            List<WxUserMessageResponse> wxUserMessageResponseList = autoResponseService
                    .queryWxUserMessageResponse(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
            if (null != content && !content.isEmpty()) {
                LOG.debug("根据关键词过滤响应动作");
                WxUserMessageResponse wxUserMessageResponse = getUserMessageResponseByKeyword(content,
                        wxUserMessageResponseList);
                if (null != wxUserMessageResponse) {
                    String action = wxUserMessageResponse.getAction();
                    int messageId = wxUserMessageResponse.getMessage_id();

                    LOG.debug("创建响应消息:action=" + action + "||messageId=" + messageId);
                    resContent = createMessageResponseByAction(fromUserName, toUserName, action, messageId);
                } else {
                    LOG.warn("没有匹配到关键词,使用默认消息");
                }
            } else {
                LOG.warn("用户输入的是空文本!");
            }

        }
        // 图片消息
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
            LOG.info("用户发送的是图片消息");
        }
        // 地理位置消息
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
            LOG.info("用户发送的是地理位置消息");
        }
        // 链接消息
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
            LOG.info("用户发送的是链接消息");
        }
        // 音频消息
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
            LOG.info("用户发送的是音频消息");
        }
        // 视频消息
        else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
            LOG.info("用户发送的是视频消息");
        }

        LOG.warn("生成的返回消息:" + resContent);

        return resContent;
    }

    /**
     * 处理微信时间请求
     *
     * @param fromUserName
     * @param toUserName
     * @param eventType
     *            微信事件
     * @return
     * @throws Exception
     */
    private String processEventRequest(String fromUserName, String toUserName, String eventType)
            throws Exception {
        String resContent = "";
        WxEventResponse wxEventResponse = autoResponseService.queryWxEventResponse(eventType);
        if (null != wxEventResponse) {
            String action = wxEventResponse.getAction();
            int messageId = wxEventResponse.getMessage_id();
            // 创建响应消息
            resContent = createMessageResponseByAction(fromUserName, toUserName, action, messageId);
        }
        return resContent;
    }

    /**
     * 处理微信时间请求
     *
     * @param fromUserName
     * @param toUserName
     * @param eventType 微信事件     微信事件
     * @param clickKey
     * @return
     * @throws Exception
     */
    private String processEventRequest(String fromUserName, String toUserName, String eventType, String clickKey)
            throws Exception {
        String resContent = "";
        String action = "";
        int messageId = 0;
        String eventKey = "";
        List<WxEventResponse> wxEventResponseList = autoResponseService.queryWxEventResponseList(eventType);
        for(WxEventResponse wxEventResponse: wxEventResponseList ){
            action = wxEventResponse.getAction();
            messageId = wxEventResponse.getMessage_id();
            eventKey = wxEventResponse.getWx_event();
            if( clickKey.equals(eventKey) ){
                // 创建响应消息
                resContent = createMessageResponseByAction(fromUserName, toUserName, action, messageId);
                break;
            }
        }

        return resContent;
    }

    /**
     * 保存站内通知消息
     *
     * @param newUser
     * @param params
     * @throws Exception
     */
    private void sendNewPartnerNofity(User newUser, Map<String, Object> params) throws Exception {
        try{
            String msg = templateService.queryMsgTemplateByMsgType(params, "m_msg_real_invi");
            /** 消息中心添加通知 **/
            MyMessage myMessage = new MyMessage();
            myMessage.setM_msg_date(new Date());
            myMessage.setM_msg_status("0");
            myMessage.setM_msg_text(msg);
            myMessage.setM_msg_type("effect");
            myMessage.setM_msg_user_id(newUser.getParentId());
            myMessage.setM_msg_channel("inner_site");
            myMessageService.save(myMessage);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据action创建返回消息
     *
     * @param fromUserName
     * @param toUserName
     * @param action
     * @param messageId
     * @return
     * @throws Exception
     */
    private String createMessageResponseByAction(String fromUserName, String toUserName, String action, int messageId)
            throws Exception {
        String resContent;
        switch (action) {
            // 固定消息
            case MessageUtil.RES_MESSAGE_ACTION_MESSAGE:
                resContent = createMessageResponse(fromUserName, toUserName, messageId);
                break;
            // 专属二维码
            case MessageUtil.RES_MESSAGE_ACTION_PERSONAL_QRCODE:
                sendKFMessage(fromUserName, qrWaiting);
                resContent = createQrImageMessage(fromUserName, toUserName);
                break;
            // 在线客服
            case MessageUtil.RES_MESSAGE_ACTION_CUSTOMER_SERVICE:
                // 发送客服消息
                //senfKFMessage(fromUserName,this.ccRegards);
                resContent = contactCallCenter(fromUserName, toUserName);
                break;
            default:
                resContent = "";
                break;
        }
        return resContent;
    }

    /**
     * 创建回复消息
     *
     * @param fromUserName
     * @param toUserName
     * @param messageId
     * @return
     * @throws Exception
     */
    private String createMessageResponse(String fromUserName, String toUserName, int messageId) throws Exception {
        String resContent = "";
        LOG.debug("根据消息ID获取消息对象");
        WxMessage wxMessage = autoResponseService.queryWxMessageById(messageId);
        String respMsgType = wxMessage.getMsg_type();
        if (null != respMsgType && !respMsgType.isEmpty()) {
            respMsgType = respMsgType.toLowerCase();

            switch (respMsgType) {

                case MessageUtil.RES_MESSAGE_TYPE_NEWS:
                    List<Article> articleList = autoResponseService.getMessageArticles(messageId);
                    if (null != articleList && !articleList.isEmpty()) {
                        resContent = MessageUtil.createArticleMessage(fromUserName, toUserName, articleList);
                    }
                    break;
                case MessageUtil.RES_MESSAGE_TYPE_IMAGE:
                    ImageBean imageBean = new ImageBean();
                    List<String> medias = new ArrayList<String>();
                    medias.add(wxMessage.getMediaid());
                    imageBean.setMediaId(medias);
                    resContent = MessageUtil.createImageMessage(fromUserName, toUserName, imageBean);
                    break;
                default:
                    // 默认回复文本消息
                    resContent = MessageUtil.createTextMessage(fromUserName, toUserName, wxMessage.getContent());
                    break;
            }
        }
        return resContent;
    }

    /**
     * 回复二维码图片消息
     *
     * @param fromUserName
     * @param toUserName
     * @return
     */
    private String createQrImageMessage(String fromUserName, String toUserName) {
        String resContent = "系统繁忙， 请稍后再试";
        try {
            LOG.debug("查询个人的二维码,开始");
            token = tokenService.fetch();
            String qrcodeUrl = "logger_dir" + "/qrCode/shareQrPic.do?openId={0}";
            qrcodeUrl = MessageFormat.format(qrcodeUrl, fromUserName);
            LOG.debug("个人的二维码 下载路径:qrcodeUrl=" + qrcodeUrl);

            String qrImgName = fromUserName + ".png";
            String picSavePath = "file_upload_dir";
            picSavePath = picSavePath.endsWith(File.separator) ? picSavePath : picSavePath + File.separator;
            String mediaPath = picSavePath + qrImgName;
            LOG.debug("个人的二维码保存路径:mediaPath=" + mediaPath);

            WeiXinHttpsClient.getPic(qrcodeUrl, picSavePath, qrImgName);

            LOG.debug("增加临时素材");
            TempMediaResult result = mediaApiService.addTempMedia(token.getAccess_token(),
                    MessageUtil.REQ_MESSAGE_TYPE_IMAGE, mediaPath);
            LOG.debug("添加结果：code = " + result.getErrcode() + "||msg=" + result.getErrmsg() + "||media_id="
                    + result.getMedia_id());

            LOG.debug("发送图片消息");
            ImageBean imageBean = new ImageBean();
            List<String> medias = new ArrayList<String>();
            medias.add(result.getMedia_id());
            imageBean.setMediaId(medias);

            resContent = MessageUtil.createImageMessage(fromUserName, toUserName, imageBean);
            LOG.debug("发送结果:" + resContent);

        } catch (Exception e) {
            LOG.error(e);
        }
        return resContent;
    }

    /**
     * 匹配关键词
     *
     * @param content
     * @param wxUserMessageResponseList
     * @return
     * @author ZZ
     */
    private WxUserMessageResponse getUserMessageResponseByKeyword(String content,
                                                                  List<WxUserMessageResponse> wxUserMessageResponseList) {
        WxUserMessageResponse relObj = null;
        LOG.debug("开始匹配关键词");
        for (WxUserMessageResponse wxUserMessageResponse : wxUserMessageResponseList) {
            if (null != wxUserMessageResponse) {
                String[] keyWords = wxUserMessageResponse.getKeywords().split(",");
                for (String keyWord : keyWords) {
                    if (content.indexOf(keyWord) != -1) {
                        LOG.debug("匹配到关键词：" + keyWord);
                        relObj = wxUserMessageResponse;
                        break;
                    }
                }
            }
        }
        return relObj;
    }

    /**
     * 根据openid获取省事儿用户数据 1、先根据openid获取wx_user.unionid, 2、根据unionid获取sns_user
     *
     * @param openid
     * @return
     */
    private SnsUser getSnsUserByOpenid(String openid) {
        SnsUser snsUser = null;
        try {
            WeiXinUserInfo weixinUser = userInfoService.fetchUserInfoByOpenid(openid);
            if (null != weixinUser) {
                snsUser = snsUserService.getUserByUnionId(weixinUser.getUnionid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return snsUser;
    }

    /**
     * 修改用户的分组信息
     */
    private void updateGroupId(String groupName, String openId) {
        try{
            AccessToken at = tokenService.fetch();
            //增加分组信息
            Map<String, WeixinGroup> groupMap = WeixinApi.getWeixinGroupMap(at.getAccess_token());
            WeixinGroup group = null;
            //宝岛邀请函分组
            group = groupMap.get("");

            //修改分组
            WeixinApi.chageUserGroup(at.getAccess_token(), openId, group.getId());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 联系呼叫中心
     */
    private String contactCallCenter(String fromUserName, String toUserName) {
        LOG.debug("联系呼叫中心");
        String retVal = null;
        token = tokenService.fetch();
        KfListInfo kfListInfo = ccApiService.getKfOnlineState(token.getAccess_token());
        LOG.debug("检查客服是否在线");
        if (null != kfListInfo) {
            List<KfInfo> kfList = kfListInfo.getKf_online_list();
            if (null != kfList && !kfList.isEmpty()) {

                LOG.debug("有客服在线，转到客服系统");
                retVal = MessageUtil.customerMessage(fromUserName, toUserName);
            }
        }
        if (null == retVal || retVal.isEmpty()) {
            LOG.debug("如果都不在线，发送提示消息");
            retVal = MessageUtil.createTextMessage(fromUserName, toUserName, ccOutDuty);
        }
        return retVal;
    }

    /**
     * 发送客服提示提示消息
     * @param fromUserName
     * @param message
     */
    private void sendKFMessage(String fromUserName, String message) {
        // 客服消息模板
        String kfMessage = "{\"touser\":\"{openid}\",\"msgtype\":\"text\",\"text\":{\"content\":\"{content}\"}}";
        kfMessage = kfMessage.replaceAll("\\{openid\\}", fromUserName);
        kfMessage = kfMessage.replaceAll("\\{content\\}", message);
        token = tokenService.fetch();
        ccApiService.sendKfMessage(token.getAccess_token(), kfMessage);
    }
}
