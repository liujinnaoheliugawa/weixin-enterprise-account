package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.ShareClickFlow;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.user.User;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Service
public interface ShareClickFlowService {

    Long selectCount(ShareClickFlow flow);

    void save(ShareClickFlow flow);

    User shareCount(String uid);

    Integer countShares(String uid, String type, String goodsId);

    /**
     * 检查今天是否点击此分享对象
     * @param flow
     * @return
     */
    int getShareClickByObj( ShareClickFlow flow );
}
