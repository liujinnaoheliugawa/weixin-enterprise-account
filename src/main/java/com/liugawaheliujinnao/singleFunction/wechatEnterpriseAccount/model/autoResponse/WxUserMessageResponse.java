package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse;

/**
 * @author wgd
 * @date 2016-10-31
 * 微信自动回复用户消息
 */
public class WxUserMessageResponse {

	/**
	 * 主键
	 */
	private int id;
	
	/**
     * 用户发送的消息类型，文本、图片、语音等，默认是文本
     */
    private String message_type;
    
	/**
	 * 关键词,如果有多个用","分割;关键词不能重复
	 */
	private String keywords;
	
	/**
	 * 响应动作，对应wx_response_action.code
	 */
	private String action;
	
	/**
	 * 关联wx_message.id，当action为message时必填
	 */
	private int message_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }
}
