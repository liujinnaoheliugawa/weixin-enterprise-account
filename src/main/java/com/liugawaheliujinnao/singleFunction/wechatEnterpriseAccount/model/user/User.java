package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.ws.rs.FormParam;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class User implements Serializable {

    private String id; /**对应通用参数uid**/

    private String description; /**描述**/

    private String name; /**用户名**/

    private String title; /**标题**/

    private String birthday; /**生日**/

    private String authType; /**类型**/

    private String authTypeNum; /**类型值**/

    private Date createTime; /**注册时间**/

    private String email; /**邮件**/

    private Integer exps = 0; /**经验值**/

    private Integer gender; /**性别(1:男,2:女)**/

    private String headImg; /**头像**/

    private String mobile; /**手机**/

    private String nickname; /**昵称**/

    private String passwd; /**密码**/

    private Boolean pwSet; /**是否设置密码(0:未设置,1:已设置)**/

    private Integer points = 0; /**积分**/

    /**
     * 上一级id
     */
    private String parentId;

    /**
     * 现金余额
     */
    private Double readyMoney = 0.0;

    /**
     * 现金累计
     */
    private Double readyMoneyTotal = 0.0;

    /**
     * 邀请码
     */
    @Column(name = "invitation_code")
    @FormParam("invitation_code")
    private String invitationCode;

    /**
     *
     */
    private Integer signdays = 0;

    /**
     * zen币总数
     */
    private Integer pointsTotal = 0;

    private Integer subscribers = 0; /**粉丝数**/

    private Integer subscribings = 0; /**关注数**/

    private Boolean isSys; /**是否系统用户(0:否,1:是)**/

    private Boolean isTmp; /**是否临时用户(0:否,1:是)**/

    private Integer shares = 0; /**分享数**/

    /**
     * 分享链接点击次数
     */
    private Integer shareClick;

    private String imei; /**IIMEI号**/

    private Boolean expirePointProcessed = false; /**失效积分**/

    private String recommendMobile; /**推荐人手机**/

    private String deviceId; /**设备ID**/

    private String city; /**城市**/

    private Integer apiVersion = 0; /**API版本**/

    private Integer defaultFlag = 0; /**假用户**/

    private Integer terraceFlag = 0; /**手机0,PC为1**/

    private String qrName; /**渠道号**/

    private Boolean subscribed; /**微信号是否仍在关注**/

    private String deviceModel; /**机型**/

    private Integer level = 1; /**等级**/

    private Integer effect = 0; /**影响力**/

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getExps() {
        return exps;
    }

    public void setExps(Integer exps) {
        this.exps = exps;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Boolean getPwSet() {
        return pwSet;
    }

    public void setPwSet(Boolean pwSet) {
        this.pwSet = pwSet;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Integer subscribers) {
        this.subscribers = subscribers;
    }

    public Integer getSubscribings() {
        return subscribings;
    }

    public void setSubscribings(Integer subscribings) {
        this.subscribings = subscribings;
    }

    public Boolean getSys() {
        return isSys;
    }

    public void setSys(Boolean sys) {
        isSys = sys;
    }

    public Boolean getTmp() {
        return isTmp;
    }

    public void setTmp(Boolean tmp) {
        isTmp = tmp;
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

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Boolean getExpirePointProcessed() {
        return expirePointProcessed;
    }

    public void setExpirePointProcessed(Boolean expirePointProcessed) {
        this.expirePointProcessed = expirePointProcessed;
    }

    public String getRecommendMobile() {
        return recommendMobile;
    }

    public void setRecommendMobile(String recommendMobile) {
        this.recommendMobile = recommendMobile;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(Integer apiVersion) {
        this.apiVersion = apiVersion;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Double getReadyMoney() {
        return readyMoney;
    }

    public void setReadyMoney(Double readyMoney) {
        this.readyMoney = readyMoney;
    }

    public Double getReadyMoneyTotal() {
        return readyMoneyTotal;
    }

    public void setReadyMoneyTotal(Double readyMoneyTotal) {
        this.readyMoneyTotal = readyMoneyTotal;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Integer getSigndays() {
        return signdays;
    }

    public void setSigndays(Integer signdays) {
        this.signdays = signdays;
    }

    public Integer getPointsTotal() {
        return pointsTotal;
    }

    public void setPointsTotal(Integer pointsTotal) {
        this.pointsTotal = pointsTotal;
    }

    public Integer getTerraceFlag() {
        return terraceFlag;
    }

    public void setTerraceFlag(Integer terraceFlag) {
        this.terraceFlag = terraceFlag;
    }

    public String getQrName() {
        return qrName;
    }

    public void setQrName(String qrName) {
        this.qrName = qrName;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public String getDeviceModel() {
        return deviceModel;
    }
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getEffect() {
        return effect;
    }

    public void setEffect(Integer effect) {
        this.effect = effect;
    }
    public Integer getShareClick() {
        return shareClick;
    }

    public void setShareClick(Integer shareClick) {
        this.shareClick = shareClick;
    }
    public User() {

    }

    public User(String id, String city, String description, Integer gender, String headImg, String nickname, Integer apiVersion, String deviceId, String imei, String authType, String authTypeNum) {
        this.id = id;
        this.city = city;
        this.createTime = new Date();
        this.description = description;
        this.gender = gender;
        this.headImg = headImg;
        this.nickname = nickname;
        this.apiVersion = apiVersion == null ? 0 : apiVersion;
        this.deviceId = deviceId;
        this.imei = imei;
        this.authType = authType;
        this.subscribed = true;
        this.authTypeNum = authTypeNum;
    }

    public User(String uid, String pwd, String mobile, String nickname,  Integer apiVersion, String deviceId, String imei, String authType, String authTypeNum) {
        this.id = uid;
        this.passwd = pwd;
        this.name = mobile;
        this.mobile = mobile;
        this.nickname = nickname;
        this.apiVersion = apiVersion;
        this.deviceId = deviceId;
        this.imei = imei;
        this.authType = authType;
        this.authTypeNum = authTypeNum;
    }

    public User setProperties(String city, String description, Integer gender, String nickname, String headImg, String title) {
        this.city = city;
        this.description = description;
        this.gender = gender;
        this.nickname = nickname;
        this.headImg = headImg;
        this.title = title;
        return this;
    }
}
