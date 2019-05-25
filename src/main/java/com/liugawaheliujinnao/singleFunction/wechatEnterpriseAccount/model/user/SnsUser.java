package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user;

import javax.persistence.*;
import javax.ws.rs.FormParam;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class SnsUser implements Serializable {

    private String id;

    private String description; /**描述**/

    private String name; /**名字**/

    private String title; /**标题**/

    private String accessToken; /**微信token**/

    private String authType; /**类型**/

    private String authTypeNum; /**类型值**/

    private String city; /**微信token**/

    private Date createTime; /**注册时间**/

    private Integer gender; /**性别(1:男,2:女)**/

    private String headImg; /**头像**/

    private String nickname; /**昵称**/

    private String openId; /**关联**/

    private String relId; /**关联**/

    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthTypeNum() {
        return authTypeNum;
    }

    public void setAuthTypeNum(String authTypeNum) {
        this.authTypeNum = authTypeNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SnsUser () {

    }

    public SnsUser (String id, String accessToken, String authType, String city, String description, Integer gender, String headImg, String nickname, String openId, String relId, String title, User user, String authTypeNum) {
        this.id = id;
        this.accessToken = accessToken;
        this.authType = authType;
        this.city = city;
        this.createTime = new Date();
        this.description = description;
        this.gender = gender;
        this.headImg = headImg;
        this.nickname = nickname;
        this.openId = openId;
        this.relId = relId;
        this.title = title;
        this.user = user;
        this.authTypeNum = authTypeNum;
    }

    public SnsUser setProperties(String city, String description, Integer gender, String nickname, String headImg, String title) {
        this.city = city;
        this.description = description;
        this.gender = gender;
        this.nickname = nickname;
        this.headImg = headImg;
        this.title = title;
        return this;
    }
}
