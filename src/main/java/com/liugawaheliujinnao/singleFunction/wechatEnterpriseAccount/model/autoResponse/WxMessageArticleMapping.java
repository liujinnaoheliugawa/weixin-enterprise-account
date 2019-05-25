package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse;

/**
 * @author wgd
 * @date 2016-10-31
 * 微信消息
 */
public class WxMessageArticleMapping {

	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 关联wx_message.id
	 */
	private int message_id;
	
	/**
	 * 关联wx_message_article.id
	 */
	private int article_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	
}
