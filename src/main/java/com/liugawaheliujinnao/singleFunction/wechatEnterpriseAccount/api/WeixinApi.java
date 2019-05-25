package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.group.WeixinGroup;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.group.WeixinGroups;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.WeiXinHttpsClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Component
public class WeixinApi {

    /**
     * 查询分组列表
     * @param access_token
     * @return
     */
    public static WeixinGroups getWeixinGroups(String access_token ){
        WeixinGroups result = null;
        try {
            String rawUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" + access_token;
            TypeReference<WeixinGroups> reference = new TypeReference<WeixinGroups>() {
            };

            result = WeiXinHttpsClient.get(rawUrl, reference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询公众号后台创建的分组信息，并按照名称为key存放到map中
     * @param access_token
     * @return
     */
    public static Map<String, WeixinGroup> getWeixinGroupMap(String access_token){

        Map<String, WeixinGroup>  result = new HashMap<String , WeixinGroup>();
        WeixinGroups weixinGroups =  getWeixinGroups(access_token);
        if( null != weixinGroups){
            List<WeixinGroup> weixinGroupList = weixinGroups.getGroups();
            WeixinGroup weixinGroup = null;
            for(int i=0; i < weixinGroupList.size(); i++)
            {
                weixinGroup = weixinGroupList.get(i);
                result.put(weixinGroup.getName(), weixinGroup);
            }
        }
        return result;
    }

    private static String convertMap2String(Map<String, Object> params) {
        String parmsStr = "";
        if( null != params && !params.isEmpty()){
            JSONObject obj = new JSONObject();
            Iterator<Map.Entry<String, Object>> item = params.entrySet().iterator();
            Map.Entry<String, Object> entry = null;
            while(item.hasNext())
            {
                entry = item.next();
                obj.put(entry.getKey(), entry.getValue());
            }
            parmsStr = obj.toString();
        }
        return parmsStr;
    }

    /**
     * 根据用户openID查询所在的分组
     * @param access_token
     * @param openId
     * @return { "groupid": 102 }
     */
    public static int getWeixinGroupByOpenid( String access_token , String openId){
        int groupId = -1;
        try {
            String rawUrl = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=" + access_token;

            TypeReference<WeiXinResult<String>> reference = new TypeReference<WeiXinResult<String>>() {
            };

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("openid", openId);

            WeiXinResult<String> weiXinResult = WeiXinHttpsClient.post(rawUrl, reference, convertMap2String(params));
            String result = weiXinResult.getMsg();
            result = result.substring(0, result.lastIndexOf("}")+1);
            JSONObject obj = JSONObject.fromObject(result);
            groupId = obj.getInt("groupid");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupId;
    }

    /**
     * 移动用户分组
     * @param access_token
     * @param openid
     * @param to_groupid
     * @return
     */
    public static WeiXinResult<String> chageUserGroup( String access_token, String openid, int to_groupid ){
        WeiXinResult<String> result = null;
        try {
            String rawUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=" + access_token;
            TypeReference<WeiXinResult<String>> reference = new TypeReference<WeiXinResult<String>>() {
            };
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("openid", openid);
            params.put("to_groupid", to_groupid);
            result = WeiXinHttpsClient.post(rawUrl, reference,convertMap2String(params));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量移动用户分组
     * @param access_token
     * @param openidList
     * @param to_groupid
     * @return
     */
    public static WeiXinResult<String> batchChageUserGroup(String access_token, List<String> openidList, int to_groupid) {
        WeiXinResult<String> result = null;
        try {
            String rawUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=" + access_token;
            TypeReference<WeiXinResult<String>> reference = new TypeReference<WeiXinResult<String>>() {
            };

            JSONObject obj = new JSONObject();
            obj.put("to_groupid", to_groupid);

            JSONArray jarray = new JSONArray();
            for (int i = 0; i < openidList.size(); i++) {
                jarray.add(openidList.get(i));
            }

            obj.put("openid_list", jarray);
            obj.put("to_groupid", to_groupid);
            result = WeiXinHttpsClient.post(rawUrl, reference, obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
