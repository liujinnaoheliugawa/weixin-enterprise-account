package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.pay;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinErrorMessage;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.pay.*;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.UTF8MediaType;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinPayService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinProductService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Component
@Path("/pay")
public class WeiXinPayController {

    @Autowired
    private WeiXinProductService productService;

    @Autowired
    private WeiXinPayService payService;

    @GET
    @Produces(UTF8MediaType.JSON)
    public void getEternalQr(@Context UriInfo ui) {
        System.out.println("weixin log in pay controller");
    }

    @GET
    @Path("/generateProduct")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult generateProduct(@Context UriInfo ui){
        try {
            Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
            String body = map.get("body");
            Double price = Double.parseDouble(map.get("price"));
            WeiXinProduct product = new WeiXinProduct(StrUtil.encodeMD5(body + new Date().getTime() + Math.random() * Math.pow(10, 10)), body, price, new Date());

            Serializable obj = productService.save(product);
            return WeiXinResult.success(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(WeiXinErrorMessage.WX_CREATE_PRODUCT_FAILED);
        }
    }

    @POST
    @Path("/getRaw")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult getRaw(@Context UriInfo ui) {
        Map<String, String> map = AppUtils.prepareParameters(ui.getQueryParameters());
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        
        String attach = null;
        if(map.containsKey("attach")){
            attach = map.get("attach");
        }
        String appPaySecret = WeiXinUtil.getWXPayParam(attach, "", "");

        String sign = payService.getRaw(map,appPaySecret);
        return WeiXinResult.success(sign);
    }

    @POST
    @Path("/getUnified")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult getUnified(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        
        String attach = null;
        if(map.containsKey("attach")){
            attach = map.get("attach");
        }

        String appId = WeiXinUtil.getWXPayParam(attach,"weixin_app_id", "ssr_app_id");
        String mchId = WeiXinUtil.getWXPayParam(attach,"weixin_mch_id", "ssr_app_id");
        String appPaySecret = WeiXinUtil.getWXPayParam(attach, "", "");

        String prepayId = "";
        try{
            prepayId = payService.getPrepayId(map, appId, mchId, appPaySecret);
            System.out.println("prepayId="+prepayId);
        }catch(Exception e)
        {
            e.printStackTrace();
            return WeiXinResult.failed(-1, e.getMessage());
        }

        WeiXinUnifiedParam param = null;
        String tradeType = map.get("trade_type");
        
        if("JSAPI".equals(tradeType) ){
            param = Signature.getJsapiCallBack( appId,  appPaySecret,  prepayId);
        }
        else if("APP".equals(tradeType) ){
            param = Signature.getAppCallBack( appId, mchId, appPaySecret,  prepayId);
        }
        else{
        }
        
        return WeiXinResult.success(param);
    }


    @POST
    @Path("/notify")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult notify(@Context UriInfo ui, @Context HttpServletRequest req) {
        System.out.println("����΢��֧�����֪ͨ:��ʼ");
        try {
            System.out.println("weixin log absolute path is " + ui.getAbsolutePath());
            System.out.println("weixin log path is " + ui.getPath());
            System.out.println("weixin log notify in");
            Map<String, String> map = MessageUtil.parseXml(req);
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                System.out.println(entry.getKey()+":"+entry.getValue());
            }
            boolean saveFlag = payService.updateOrder(map);
            return WeiXinResult.success(saveFlag);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/query")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult query(@Context HttpServletRequest request, @Context ServletContext context, Map<String, String> map) {
        String attach = null;
        if(map.containsKey("attach")){
            attach = map.get("attach");
        }

        String appId = WeiXinUtil.getWXPayParam(attach,"appId", "ssrAppId");
        String appPaySecret = WeiXinUtil.getWXPayParam(attach,"commercial_key","ssr_partner_key");
        String mchId = WeiXinUtil.getWXPayParam(attach,"mchId","ssrAppId");

        WeiXinTradeResult result = payService.getTradeState(map,appId,mchId,appPaySecret);
        return WeiXinResult.success(result);
    }

    @POST
    @Path("/close")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult close(Map<String, String> map) {
        System.out.println("ȡ��֧��:��ʼ");
        //��ȡ������Ϣ
        String attach = null;
        if(map.containsKey("attach")){
            attach = map.get("attach");
        }
        //32λ֧����Կ
        String appPaySecret = WeiXinUtil.getWXPayParam(attach,"commercial_key","ssr_partner_key");

        WeiXinOrderQueryResult result = payService.closeOrder(map,appPaySecret);
        System.out.println("ȡ��֧��:���");
        return WeiXinResult.success(result);
    }

    @POST
    @Path("/refund")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult refund(@Context HttpServletRequest request, @Context ServletContext context, Map<String, String> map) {

        System.out.println("�˿ʼ");
        //��ȡ������Ϣ
        String attach = null;
        if(map.containsKey("attach")){
            attach = map.get("attach");
        }

        String pemFileName = WeiXinUtil.getWXPayParam(attach, "Mp_apiclient_cert.p12", "SSR_apiclient_cert.p12");
        String pemFilePath = context.getRealPath("/") + "/WEB-INF/classes/cert/" + pemFileName;
        File file = new File(pemFilePath);
        if(!file.exists()){
            return WeiXinResult.failed(-1);
        }

        //String appId = WeiXinUtil.getWXPayParam(attach,ConstInfo.WEIXIN_APP_ID, ConstInfo.SSR_APP_ID);
        String mchId = WeiXinUtil.getWXPayParam(attach,"mch_id","ssr_mch_id");
        String appSecret = WeiXinUtil.getWXPayParam(attach,"secret","app_secret");
        String appPaySecret = WeiXinUtil.getWXPayParam(attach,"partner_key","ssr_partner_key");

        WeiXinRefundResult result = payService.refund(pemFilePath, map,appSecret, appPaySecret, mchId);
        return WeiXinResult.success(result);
    }

    @POST
    @Path("/refundQuery")
    @Consumes(UTF8MediaType.JSON)
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult refundQuery(@Context HttpServletRequest request, Map<String, String> map) {
        String attach = null;
        if(map.containsKey("attach")){
            attach = map.get("attach");
        }

        String appPaySecret = WeiXinUtil.getWXPayParam(attach,"commercial_key","partner_key");
        WeiXinRefundQueryResult result = payService.refundQuery(map,appPaySecret);
        return WeiXinResult.success(result);
    }
}
