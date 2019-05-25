package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.auth;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.SignUtil;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Controller
@Path("/signature")
public class SignatureController {

    /**
     * 生成签名
     */
    @Path("/signature")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public String signature(@QueryParam(value = "url")String url) {
        System.out.println("signature url = " + url);
        System.out.println("jsticket=" + "");
        String ret = SignUtil.sign("", url);
        return ret;
    }

    @Path("/validateToken")
    @Produces({ MediaType.APPLICATION_JSON })
    public String validateToken(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        // 微信加密签名
        String signature = request.getParameter("signature");
//		logger.error("************微信加密签名************" + signature);
        // 时间戳
        String timestamp = request.getParameter("timestamp");
//		logger.error("************时间戳************" + timestamp);
        // 随机数
        String nonce = request.getParameter("nonce");
//		logger.error("************随机数************" + nonce);
        // 随机字符串
        String echostr = request.getParameter("echostr");
//		logger.error("************随机字符串************" + echostr);
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
//			logger.error("************token校验成功************");
            return echostr;
        }
        System.err.println("************token校验失败************");
        return null;
    }
}
