package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse;

/**
 * @author wgd
 * @date 2016-10-31 
 * 微信消息类型(只提供表模型和默认数据)
 */
public class WxMessage {

	/**
	 * 主键
	 */
	private int id;

	/**
	 * 响应消息类型, 关联wx_message_type.code
	 */
	private String msg_type;
	
	/**
	 * 文本内容，文本消息必填
	 */
	private String content;
	
	/**
	 * 通过微信素材管理接口上传多媒体文件，得到的id;图片、语音、图文、音乐、视频消息必填
	 */
	private String mediaid;
	
	/**
	 * 消息的标题，视频、音乐、图文消息选填
	 */
	private String title;
	
	/**
	 * 消息的描述，视频、音乐、图文消息选填
	 */
	private String description;
	
	/**
	 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐;音乐专有属性，非必填
	 */
	private String music_url;
	
	/**
	 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐;音乐专有属性，非必填
	 */
	private String hqmusic_url;
	
	/**
	 * 缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id;音乐专有属性，非必填
	 */
	private String thumb_mediaid;
	
	/**
	 * 文章个数不能超过10个
	 */
	private int articlecount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMediaid() {
		return mediaid;
	}

	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusic_url() {
		return music_url;
	}

	public void setMusic_url(String music_url) {
		this.music_url = music_url;
	}

	public String getHqmusic_url() {
		return hqmusic_url;
	}

	public void setHqmusic_url(String hqmusic_url) {
		this.hqmusic_url = hqmusic_url;
	}

	public String getThumb_mediaid() {
		return thumb_mediaid;
	}

	public void setThumb_mediaid(String thumb_mediaid) {
		this.thumb_mediaid = thumb_mediaid;
	}

	public int getArticlecount() {
		return articlecount;
	}

	public void setArticlecount(int articlecount) {
		this.articlecount = articlecount;
	}
	
}
