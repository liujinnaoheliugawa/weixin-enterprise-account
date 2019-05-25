package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.res;

import java.io.Serializable;

/**
 * 
 * 被动回复图片消息类
 * <p>回复图片等多媒体消息时需要预先通过素材管理接口上传临时素材到微信服务器
 * @author ZZ 2016年9月9日
 * @see
 * @since 1.0
 */
public class ImageMessage extends BaseMessage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
