package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse;

/**
 * @author wgd
 * @date 2016-10-31 
 * 微信消息类型(只提供表模型和默认数据)
 */
public class WxMessageType {

	/**
	 * 主键
	 */
	private int id;
	
	/**
	 * 消息类型
	 * text--文本
	 * image--图片
	 * voice--语音
	 * video--视频
	 * music--音乐消息 
	 * news-图文消息
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
