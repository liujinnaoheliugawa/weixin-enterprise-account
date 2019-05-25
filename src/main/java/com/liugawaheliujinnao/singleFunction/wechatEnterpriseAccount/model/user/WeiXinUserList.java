package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class WeiXinUserList {

    private Integer total;

    private Integer count;

    private WeiXinOpenIdList data;

    private String next_openid;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public WeiXinOpenIdList getData() {
        return data;
    }

    public void setData(WeiXinOpenIdList data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }
}
