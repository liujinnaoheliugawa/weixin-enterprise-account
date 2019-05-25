package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.logger;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
public class MyMessage implements Serializable {

    private Integer m_msg_id;

    private String m_msg_user_id;

    private String m_msg_text;

    private Date m_msg_date;

    private String m_msg_status;

    private String m_msg_type;

    private String m_msg_channel;

    public Integer getM_msg_id() {
        return m_msg_id;
    }

    public void setM_msg_id(Integer m_msg_id) {
        this.m_msg_id = m_msg_id;
    }

    public String getM_msg_user_id() {
        return m_msg_user_id;
    }

    public void setM_msg_user_id(String m_msg_user_id) {
        this.m_msg_user_id = m_msg_user_id;
    }

    public String getM_msg_text() {
        return m_msg_text;
    }

    public void setM_msg_text(String m_msg_text) {
        this.m_msg_text = m_msg_text;
    }

    public Date getM_msg_date() {
        return m_msg_date;
    }

    public void setM_msg_date(Date m_msg_date) {
        this.m_msg_date = m_msg_date;
    }

    public String getM_msg_status() {
        return m_msg_status;
    }

    public void setM_msg_status(String m_msg_status) {
        this.m_msg_status = m_msg_status;
    }

    public String getM_msg_type() {
        return m_msg_type;
    }

    public void setM_msg_type(String m_msg_type) {
        this.m_msg_type = m_msg_type;
    }

    public String getM_msg_channel() {
        return m_msg_channel;
    }

    public void setM_msg_channel(String m_msg_channel) {
        this.m_msg_channel = m_msg_channel;
    }
}
