package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.job;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinErrorMessage;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.job.WeiXinJob;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.UTF8MediaType;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinJobService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.AppUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Controller
@Path("/weixinJob")
public class WeiXinJobController {

    @Autowired
    private WeiXinJobService jobService;

    @GET
    @Path("/init")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult init() {
        try {
            return WeiXinResult.success(jobService.init());
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }
    }

    @POST
    @Path("/start")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult start(@FormParam("jobId") Integer jobId) {
        WeiXinJob scheduleJob;
        try {
            scheduleJob = jobService.getTaskById(jobId);
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }
        try {
            return WeiXinResult.success(jobService.addJob(scheduleJob));
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }
    }

    @GET
    @Path("/getAllJob")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult getAllJob() {
        List<WeiXinJob> apps;
        try {
            apps = jobService.getAllTask();
            return WeiXinResult.success(apps);
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }

    }


    @GET
    @Path("/getAllScheduleJob")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult getAllScheduleJob() {
        List<WeiXinJob> apps;
        try {
            apps = jobService.getAllScheduleJob();
            return WeiXinResult.success(apps);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(UTF8MediaType.JSON)
    @Path("/save")
    public WeiXinResult save(@Context HttpServletRequest request) {
        try {
            WeiXinJob job = AppUtils.convertRequestBody(request, WeiXinJob.class);
            return WeiXinResult.success(jobService.addTask(job));
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(UTF8MediaType.JSON)
    @Path("/delete")
    public WeiXinResult delete(@FormParam("id") Integer id) {
        try {
            return WeiXinResult.success(jobService.deleteTaskById(id));
        } catch (DataIntegrityViolationException e) {
            return WeiXinResult.failed(WeiXinErrorMessage.WX_DELETE_CONSTRAINT);
        } catch (HibernateOptimisticLockingFailureException ex) {
            return WeiXinResult.failed(WeiXinErrorMessage.WX_DELETE_NO_DATA);
        } catch (RuntimeException e) {
            return WeiXinResult.failed(-100, e.getMessage());
        } catch (Exception e) {
            return WeiXinResult.failed(-1, e.getMessage());
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(UTF8MediaType.JSON)
    @Path("/pause")
    public WeiXinResult pause(@FormParam("jobName") String jobName) {

        try {
            return WeiXinResult.success( jobService.pauseJob(jobName));
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(UTF8MediaType.JSON)
    @Path("/resume")
    public WeiXinResult resume(@FormParam("jobName") String jobName) {

        try {
            return WeiXinResult.success(jobService.resumeJob(jobName));
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(UTF8MediaType.JSON)
    @Path("/remove")
    public WeiXinResult delete(@FormParam("jobName") String jobName) {

        try {
            return WeiXinResult.success(jobService.deleteJob(jobName));
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(UTF8MediaType.JSON)
    @Path("/updateCron")
    public WeiXinResult updateCron(@FormParam("jobName") String jobName, @FormParam("cron") String cron) {

        try {
            return WeiXinResult.success(jobService.updateCron(jobName,cron));
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(UTF8MediaType.JSON)
    @Path("/run")
    public WeiXinResult run(@FormParam("jobName") String jobName) {
        try {
            return WeiXinResult.success(jobService.runJob(jobName));
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }
    }
}
