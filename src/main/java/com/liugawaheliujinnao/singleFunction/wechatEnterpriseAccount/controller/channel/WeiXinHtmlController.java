package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.channel;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.UTF8MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Component
@Path("/html")
public class WeiXinHtmlController {

    @GET
    @Produces(UTF8MediaType.JSON)
    @Path("/getQrHtml")
    public WeiXinResult getQrHtml() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("<html>");
            sb.append("<head>");
            sb.append("<title>test</title>");
            sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
            sb.append("<style type=\"text/css\">");
            sb.append("TABLE{border-collapse:collapse;border-left:solid 1 #000000; border-top:solid 1 #000000;padding:5px;}");
            sb.append("TH{border-right:solid 1 #000000;border-bottom:solid 1 #000000;}");
            sb.append("TD{font:normal;border-right:solid 1 #000000;border-bottom:solid 1 #000000;}");
            sb.append("</style></head>");
            sb.append("<body bgcolor=\"#FFF8DC\">");
            sb.append("<div align=\"center\">");
            sb.append("<br/>");
            sb.append("<br/>");
            sb.append("<br/><br/>");
            sb.append("</div></body></html>");
            FileOutputStream outputStream = new FileOutputStream("/Users/wufei/Desktop/test.html");
            outputStream.write(sb.toString().getBytes());
            outputStream.close();
            return WeiXinResult.success();
        } catch (FileNotFoundException e) {
            System.out.println("weixin log file not found exception happend");
            e.printStackTrace();
            return WeiXinResult.success("FileNotFoundException happend");
        } catch (IOException e) {
            System.out.println("weixin log io exception happend");
            e.printStackTrace();
            return WeiXinResult.success("IOException happend");
        }
    }
}
