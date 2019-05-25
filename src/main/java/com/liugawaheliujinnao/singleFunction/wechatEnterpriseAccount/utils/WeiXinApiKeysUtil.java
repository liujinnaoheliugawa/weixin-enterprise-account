package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.apiKey.ApiKeys;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Singleton
@Component
public class WeiXinApiKeysUtil {

    @Autowired
    ApiKeyService apiKeysService;

    private Map<String, ApiKeys> keysMap = null;

    public void load() {
        synchronized (this) {
            try {
                System.out.println("ZLApiKeysUtil : LOAD");
                List<ApiKeys> keys = apiKeysService.getAll();
                Map<String, ApiKeys> map = new HashMap<>();
                for(ApiKeys key : keys) {
                    map.put(key.getApiKey(), key);
                }
                this.keysMap = map;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ApiKeys get(String key) {
        return this.keysMap.get(key);
    }

    public String getPrivateKey(String key) {
        return getPrivateKey(key, null);
    }

    public String getPrivateKey(String key, String defaultValue) {
        ApiKeys systemConfig = get(key);
        if(systemConfig != null) {
            return systemConfig.getPrivateKey();
        }
        return defaultValue;
    }
}

