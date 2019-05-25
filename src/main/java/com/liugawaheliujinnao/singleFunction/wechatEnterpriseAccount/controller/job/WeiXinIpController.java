package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinErrorMessage;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.job.WeiXinIp;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.job.WeiXinIpList;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.UTF8MediaType;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.WeiXinHttpsClient;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinIpService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Controller
@Path("/weixinIp")
public class WeiXinIpController {

    @Autowired
    private WeiXinIpService ipService;

    @GET
    @Produces(UTF8MediaType.JSON)
    @Path("/ips")
    public WeiXinResult get(@Context UriInfo ui) {
        try {
            Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
            String url = ipService.getIpUrl(map);
            TypeReference reference = new TypeReference<WeiXinIpList>() {
            };
            WeiXinIpList list = WeiXinHttpsClient.get(url, reference);
            List<WeiXinIp> ipList = new ArrayList<>();
            ipService.truncate(); /**清除旧ip**/
            for (String ip : (List<String>)list.getIp_list()) {
                WeiXinIp weiXinIp = new WeiXinIp();
                if (!StringUtils.isEmpty(ip)) {
                    weiXinIp.setIp(ip);
                    ipService.save(weiXinIp);
                    ipList.add(weiXinIp);
                }
            }
            return WeiXinResult.success(ipList);
        } catch (Exception e) {
            return WeiXinResult.failed(WeiXinErrorMessage.WX_FETCH_TOKEN_FAILED);
        }
    }
}
