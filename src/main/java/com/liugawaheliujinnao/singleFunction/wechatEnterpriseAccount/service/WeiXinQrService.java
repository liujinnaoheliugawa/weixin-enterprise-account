package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel.*;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.res.Article;

import java.io.File;
import java.util.List;

public interface WeiXinQrService {

    WeiXinEternalActionInfo getEternalActionInfo(Integer sceneId);

    WeiXinEternalStrActionInfo getEternalStrActionInfo(String sceneStr);

    WeiXinTemplateActionInfo getTemplateActionInfo(String sceneStr);

    WeiXinEternalTicket fetchEternalTicket(WeiXinEternalActionInfo actionInfo) throws  Exception;

    WeiXinEternalTicket fetchEternalTicket(WeiXinEternalStrActionInfo actionInfo) throws  Exception;

    WeiXinTemplateTicket fetchTemplateTicket(WeiXinTemplateActionInfo actionInfo) throws  Exception;

    WeiXinTemplateTicket fetchTemplateTicket(WeiXinTemplateActionInfo actionInfo, String url) throws  Exception;

    Object downloadPic (WeiXinTicket ticket) throws  Exception;

    String getPicUrl(WeiXinTicket ticket) throws  Exception;

    File getPicFile(WeiXinTicket ticket) throws  Exception;

    String getFetchTicketUser() throws  Exception;

    /**
     * 获取公众号发布的文章列表
     * @param openId
     * @param uid
     * @return
     * @throws  Exception
     */
    List<Article> getTicketArticle(String openId, String uid) throws  Exception;

    WeiXinTemplateTicket getUserTicket(String openId, String uid) throws  Exception;
}
