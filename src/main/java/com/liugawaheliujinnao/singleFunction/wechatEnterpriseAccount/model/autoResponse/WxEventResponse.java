package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse;

/**
 * @author wgd
 * @date 2016-10-31
 * 微信事件响应
 */
public class WxEventResponse {

	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 主键
	 */
	private String wx_event;
	
	/**
	 * 微信菜单key值，当event=CLICK时必填
	 */
	private String wx_key;
	
	/**
	 * 微信菜单key值，当event=CLICK时必填
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

	public String getWx_event() {
		return wx_event;
	}

	public void setWx_event(String wx_event) {
		this.wx_event = wx_event;
	}

	public String getWx_key() {
		return wx_key;
	}

	public void setWx_key(String wx_key) {
		this.wx_key = wx_key;
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
	
}
