package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse;

/**
 * @author wgd
 * @date 2016-10-31
 * 微信事件(只提供表模型和默认数据)
 */
public class WxEventType {

	/**
	 * 主键
	 */
	private int id;
	/**
	 * 事件类型
	 * subscribe--订阅,
	 * unsubscribe--取消订阅,
	 * SCAN--已经关注用户扫码,
	 * LOCATION--上报地理位置,
	 * CLICK--点击菜单拉取消息 
	 * VIEW-点击菜单跳转链接
	 */
	private String code;
	
	/**
	 * 主键
	 */
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
