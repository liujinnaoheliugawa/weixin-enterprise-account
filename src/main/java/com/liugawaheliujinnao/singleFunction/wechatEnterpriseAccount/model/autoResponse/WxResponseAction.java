package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse;

/**
 * @author wgd
 * @date 2016-10-31
 * 微信文本消息响应动作类型(只提供表模型和默认数据)
 */
public class WxResponseAction {

	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 响应类型, message-固定消息，Personal_Qrcode--专属二维码，Customer_Service--在线客服
	 */
	private String code;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
