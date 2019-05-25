package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.behavior;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.User;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class WeiXinUserClick implements Serializable {

    private Integer id;

    private User user;

    private WeiXinClick click;

    private Date clickTime; /**注册时间**/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WeiXinClick getClick() {
        return click;
    }

    public void setClick(WeiXinClick click) {
        this.click = click;
    }

    public Date getClickTime() {
        return clickTime;
    }

    public void setClickTime(Date clickTime) {
        this.clickTime = clickTime;
    }
}
