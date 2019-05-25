package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.autoResponse;

/**
 * 
 * @author liugawaheliujinnao
 * @date 2016-04-21 数据库常量
 */
@SuppressWarnings("serial")
public class SysParameter {

    /**
     * 主键
     */
    private int m_id;

    /**
     * 类型
     */
    private String m_type;

    /**
     * 系数值
     */
    private double m_value;

    /**
     * 描述
     */
    private String m_desc;

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_type() {
        return m_type;
    }

    public void setM_type(String m_type) {
        this.m_type = m_type;
    }

    public double getM_value() {
        return m_value;
    }

    public void setM_value(double m_value) {
        this.m_value = m_value;
    }

    public String getM_desc() {
        return m_desc;
    }

    public void setM_desc(String m_desc) {
        this.m_desc = m_desc;
    }

}
