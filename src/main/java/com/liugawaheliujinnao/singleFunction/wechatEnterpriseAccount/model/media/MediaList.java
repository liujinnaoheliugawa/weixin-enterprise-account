package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.media;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class MediaList implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<MediaItem> item;
    private int total_count;// 该类型的素材的总数
    private int item_count;// 本次查询获取的素材的数量

    public List<MediaItem> getItem() {
        return item;
    }

    public void setItem(List<MediaItem> item) {
        this.item = item;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }
}
