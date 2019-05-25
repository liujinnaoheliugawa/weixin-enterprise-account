package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.TempMediaResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.media.MediaList;

/**
 *
 * 微信模板消息接口类
 *
 * <p>1、对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除（所以用户发送给开发者的素材，若开发者需要，应尽快下载到本地），以节省服务器资源。
 * 2、media_id是可复用的。
 * 3、素材的格式大小等要求与公众平台官网一致。具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，语音大小不超过2M，长度不超过60秒（公众平台官网可以在文章中插入小于30分钟的语音，但这些语音不能用于群发等场景，只能放在文章内，这方面接口暂不支持），支持mp3/wma/wav/amr格式
 * 4、需使用https调用本接口。
 * <p>更多信息请阅读 https://mp.weixin.qq.com/wiki/15/2d353966323806a202cd2deaafe8e557.html
 * @author liugawaheliujinnao 2016年9月5日
 * @see
 * @since 1.0
 */
public interface MediaApiService {

    /**
     * 新增一个临时素材
     * <p>媒体文件在后台保存时间为3天，即3天后media_id失效
     * @param accessToken 接口调用凭证
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param mediaPath 媒体文件路径
     * @return 正常： {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
     *         异常：{"errcode":40004,"errmsg":"invalid media type"}
     */
    TempMediaResult addTempMedia(String accessToken, String type, String mediaPath);

    /**
     * 查询一个临时素材
     * @param accessToken 接口调用凭证
     * @param media_id 媒体文件ID
     * @return 正常：http响应，eg： "HTTP/1.1 200 OK
    Connection: close
    Content-Type: image/jpeg
    Content-disposition: attachment; filename="MEDIA_ID.jpg"
    Date: Sun, 06 Jan 2013 10:20:18 GMT
    Cache-Control: no-cache, must-revalidate
    Content-Length: 339721"
     *         异常：{"errcode":40007,"errmsg":"invalid media_id"}
     */
    public String getTempMedia(String accessToken, String media_id );


    /**
     * 查询临时素材列表
     * @param accessToken
     * @return
     */
    MediaList getTempMediaList(String accessToken, String type, int offset, int limit );
}
