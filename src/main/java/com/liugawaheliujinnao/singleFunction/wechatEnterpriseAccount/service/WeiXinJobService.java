package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.job.WeiXinJob;
import org.quartz.SchedulerException;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public interface WeiXinJobService {

   List<WeiXinJob> getAllTask() throws Exception;

   Serializable addTask(WeiXinJob job) throws Exception;

   Serializable addTokenJob() throws Exception;

   WeiXinJob getTaskById(Integer jobId) throws Exception;

   WeiXinJob getTaskByName(String jobName) throws Exception;

    //   void changeStatus(Integer jobId, String cmd) throws SchedulerException;

   Object deleteTaskById(Integer jobId) throws Exception;

   Object updateTask(WeiXinJob job) throws Exception;

   Object updateCron(String jobName, String cron) throws Exception;

   Object addJob(WeiXinJob job) throws Exception;

   Object init() throws Exception;

   List<WeiXinJob> getAllScheduleJob() throws SchedulerException;

   List<WeiXinJob> getAllRunningJob() throws SchedulerException;

   Object pauseJob(String jobName) throws Exception;

   Object resumeJob(String jobName) throws Exception;

   Object deleteJob(String jobName) throws Exception;

   Object runJob(String jobName) throws Exception;

   void updateJobCron(WeiXinJob scheduleJob) throws SchedulerException;
}
