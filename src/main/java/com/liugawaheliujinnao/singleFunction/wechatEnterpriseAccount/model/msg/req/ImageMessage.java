package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.req;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 
 * 被动回复图片消息类
 * <p>
 * 回复图片等多媒体消息时需要预先通过素材管理接口上传临时素材到微信服务器
 * 
 * @author ZZ 2016年9月9日
 * @see
 * @since 1.0
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @XStreamAlias("Image")
    private ImageBean Image;

    public ImageBean getImage() {
        return Image;
    }

    public void setImage(ImageBean image) {
        Image = image;
    }

}
