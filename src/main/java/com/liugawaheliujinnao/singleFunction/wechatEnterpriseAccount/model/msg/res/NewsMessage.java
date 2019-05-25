package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.res;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wufei on 16/2/3.
 */
public class NewsMessage extends BaseMessage implements Serializable {

    /**图文消息个数,限制为10条以内**/
    private int ArticleCount;

    /**多条图文消息信息,默认第一个item为大图**/
    private List<Article> Articles;

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }

    public int getArticleCount() {

        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public NewsMessage() {

    }

    public NewsMessage(String fromUserName, String toUserName, String msgType) {
        super(fromUserName, toUserName, msgType);
    }
}
