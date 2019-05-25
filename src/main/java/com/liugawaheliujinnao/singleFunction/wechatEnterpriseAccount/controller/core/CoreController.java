package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.core;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-24
 */
@Component
@Path("/core")
public class CoreController {

    @Autowired
    private CoreService coreService;


    @GET
    public void get(@Context HttpServletRequest req, @Context HttpServletResponse res) throws ServletException, IOException {
        /**微信加密签名,时间戳,随机数,随机字符串**/
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        PrintWriter out = res.getWriter();
        /**请求校验,若校验成功则原样返回echostr,表示接入成功,否则接入失败**/
        if (checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
    }

    @POST
    public void doPost(@Context HttpServletRequest req, @Context HttpServletResponse res) throws ServletException, IOException, Exception {
        /**将请求,相应的编码均设置为UTF-8(防止中文乱码)**/
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=utf-8");

        /**接收参数微信加密签名,时间戳,随机数**/
        String resMessage = coreService.processRequest(req);

        /**响应消息**/
        PrintWriter out = res.getWriter();
        out.print(resMessage);
        out.close();
    }

    /***
     *  校验签名
     *
     *  @param signature 微信加密签名
     *  @param timestamp 时间戳
     *  @param nonce 随机数
     *  @return
     ***/
    private static boolean checkSignature(String signature, String timestamp, String nonce) {
        //逻辑
        return true;
    }
}
