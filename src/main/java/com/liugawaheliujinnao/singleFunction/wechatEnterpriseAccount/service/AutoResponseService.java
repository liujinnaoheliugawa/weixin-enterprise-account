package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse.*;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.res.Article;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * 微信被动消息、事件-自动回复消息查询
 *
 * <p>本模块提供cms后台配置是微信被动消息自动回复数据
 * @author liugawaheliujinnao 2016年11月3日
 * @see
 * @since 1.0
 */
@Service
public interface AutoResponseService {

    /**
     * 查询微信事件类型
     * @return
     * @throws Exception
     */
    List<WxEventType> queryWxEventType() throws Exception;

    /**
     * 加载微信响应动作类型
     * @return
     * @throws Exception
     */
    List<WxResponseAction> queryWxResponseAction() throws Exception;

    /**
     * 查询微信消息类型
     * @return
     * @throws Exception
     */
    List<WxMessageType> queryWxMessageType() throws Exception;


    /**
     * 查询全部微信消息
     * @return
     * @throws Exception
     */
    List<WxMessage> queryWxMessage() throws Exception;


    /**
     * 查询微信响应事件
     * @return
     * @throws Exception
     */
    List<WxEventResponse> queryWxEventResponse() throws Exception;


    /**
     * 查询微信响应事件
     * @return
     * @throws Exception
     */
    WxEventResponse queryWxEventResponse(String wx_event) throws Exception;

    /**
     * 查询微信响应事件
     * @param wx_event
     * @return
     * @throws Exception
     */
    List<WxEventResponse> queryWxEventResponseList(String wx_event) throws Exception;

    /**
     * 根据ID查询微信消息
     * @return
     * @throws Exception
     */
    WxMessage queryWxMessageById(int messageId) throws Exception;


    /**
     * 查询全部文章
     * @return
     * @throws Exception
     */
    List<WxMessageArticle> queryWxMessageArticle() throws Exception;


    /**
     * 根据消息id查询关联的全部文章
     * @param messageId 消息id
     * @return
     * @throws Exception
     */
    List<WxMessageArticle> queryWxMessageArticleMyMsgId(int messageId) throws Exception;


    /**
     * 根据ID查询文章
     * @return
     * @throws Exception
     */
    WxMessageArticle queryWxMessageArticleById(String articleId) throws Exception;


    /**
     * 加载消息文本自动响应类型
     * @return
     * @throws Exception
     */
    List<WxUserMessageResponse> queryWxUserMessageResponse() throws Exception;


    /**
     * 根据消息类型查询响应动作
     * @param messageType
     * @return
     * @throws Exception
     */
    List<WxUserMessageResponse> queryWxUserMessageResponse(String messageType) throws Exception;


    /**
     * 根据类型查询系统常量
     * @param type
     * @return
     * @throws Exception
     */
    List<SysParameter> querySysParameter(String type) throws Exception;

    /**
     * 根据消息id查询关联的文章，并转换成List<Article>
     * @param messageId
     * @return
     * @throws Exception
     */
    List<Article> getMessageArticles(int messageId) throws Exception;
}
