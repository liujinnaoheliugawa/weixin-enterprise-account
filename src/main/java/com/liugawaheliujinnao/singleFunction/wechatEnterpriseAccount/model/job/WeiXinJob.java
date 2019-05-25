package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.job;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wufei on 16/1/30.
 */
public class WeiXinJob implements Serializable {

    private Integer jobId;

    private Date createTime = new Date();

    private Date updateTime = new Date();

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务状态 0禁用 1启用 2暂停
     */
    private String jobStatus;

    /**
     * 任务运行时间表达式
     */
    private String cronExpression;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 任务执行时调用类：包名+类名
     */
    private String springBeanClass;

    /**
     * 任务是否有状态
     */
    private String isConcurrent;

    /**
     * 任务执行时调用的类ID
     */
    private String springBeanId;

    /**
     * 任务调用的方法名
     */
    private String methodName;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpringBeanClass() {
        return springBeanClass;
    }

    public void setSpringBeanClass(String springBeanClass) {
        this.springBeanClass = springBeanClass;
    }

    public String getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public String getSpringBeanId() {
        return springBeanId;
    }

    public void setSpringBeanId(String springBeanId) {
        this.springBeanId = springBeanId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
