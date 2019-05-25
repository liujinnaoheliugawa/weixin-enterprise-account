package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel.WeiXinChannel;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.channel.WeixinUserTicket;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.User;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public interface UserService {

    public Serializable save(User model);

    public Object update(User User);

    public Boolean delete(String uid);

    public User getByUid(String uid);

    void updateUserShare(String userId);

    /**
     * 更新分享点击次数
     * @param userId
     */
    void updateUserShareClick(String userId);

    /**
     * 粉丝关注微信公众号
     *
     * @param channel
     *            合作伙伴渠道
     * @param ticket
     * @param openId
     * @return
     * @throws Exception
     */
    public User insertUser(WeiXinChannel channel, String ticket, String openId) throws Exception;

    /**
     * 更新用户的影响力
     * @param uid
     * @return
     */
    public Integer effect(String uid);

    /**
     * 发送模板消息
     *
     * @param inferiorUser
     * @param nickName
     * @param accessToken
     *            身份标识
     */
    public String createTemplateMessage(User inferiorUser, String nickName, String accessToken, String type);


    /**
     * 根据ticket查询用户信息
     * @param ticket
     * @return
     */
    public WeixinUserTicket getWeixinUserbyTicket(String ticket);
}
