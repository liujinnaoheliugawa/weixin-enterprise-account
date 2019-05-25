package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.msg.req;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * simple introduction
 *
 * <p>
 * detailed comment
 * 
 * @author ZZ 2016年9月12日
 * @see
 * @since 1.0
 */
public class ImageBean implements Serializable {

    @XStreamImplicit(itemFieldName = "MediaId")
    private List<String> MediaId;

    public List<String> getMediaId() {
        return MediaId;
    }

    public void setMediaId(List<String> MediaId) {
        this.MediaId = MediaId;
    }

}
