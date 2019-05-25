package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.callcenter.CcRecord;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.callcenter.KfInfo;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.callcenter.KfListInfo;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * 维系多客服接口
 *
 * <p>本模块负责通过接口的方式维护客服信息，发送消息
 * <P>更多信息请参考
 *    <ul>
 *      <li>客服消息：https://mp.weixin.qq.com/wiki/11/c88c270ae8935291626538f9c64bd123.html</li>
 *      <li>微信多客服：https://mp.weixin.qq.com/wiki/11/f0e34a15cec66fefb28cf1c0388f68ab.html</li>
 *    </ul>
 * @author liugawaheliujinnao 2016年10月9日
 * @see
 * @since 1.0
 */
@Service
public interface CcApiService {

    //添加客服
    public final String ADD_KF = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token={0}";
    /**
     * 添加客服账号
     * POST数据示例如下：
     {
     "kf_account" : "test1@test",
     "nickname" : "客服1",
     "password" : "96e79218965eb72c92a549dd5a330112"
     }
     返回结果
     {
     "errcode"   : 0,
     "errmsg"    : "ok"
     }
     错误码说明：
     0   成功(no error)
     61451   参数错误(invalid parameter)
     61452   无效客服账号(invalid kf_account)
     61453   账号已存在(kf_account exsited)
     61454   账号名长度超过限制(前缀10个英文字符)(invalid kf_acount length)
     61455   账号名包含非法字符(英文+数字)(illegal character in kf_account)
     61456   账号个数超过限制(100个客服账号)(kf_account count exceeded)
     61457   无效头像文件类型(invalid file type)

     * @param access_token
     * @return
     */
    public WeiXinResult<String> addKf(String access_token);

    //修改客服信息
    public final String SET_KF = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token={0}";

    /**
     * 设置客服信息
     * POST数据示例如下：
     {
     "kf_account" : "test1@test",
     "nickname" : "客服1",
     "password" : "96e79218965eb72c92a549dd5a330112"
     }
     返回信息：
     {
     "errcode" : 0,
     "errmsg" : "ok"
     }
     * @param access_token
     * @return
     */
    public WeiXinResult<String> setKf(String access_token);

    //上传客服账号头像
    public final String upload_kf_headImg = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token={0}&kf_account={1}";

    /**
     * 上传客服头像
     * <p>头像图片文件必须是jpg格式，推荐使用640*640大小的图片以达到最佳效果。
     * 返回数据示例（正确时的JSON返回结果）：
     {
     "errcode" : 0,
     "errmsg" : "ok"
     }
     * @param access_token
     * @param kfid
     * @return
     */
    public WeiXinResult<String> uploadHeadImg(String access_token, String kfid);

    //删除客服账号
    public final String DEL_KF = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token={0}&kf_account={1}";

    /**
     * 删除客服账号
     * 返回数据示例（正确时的JSON返回结果）：
     {
     "errcode" : 0,
     "errmsg" : "ok"
     }
     * @param access_token
     * @param kfid
     * @return
     */
    public WeiXinResult<String> delKf(String access_token, String kfid);


    //查询客服聊天记录
    public final String GET_RECORD = "https://api.weixin.qq.com/customservice/msgrecord/getrecord?access_token={0}";
    //查询客服信息
    public final String GET_KFLIST = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token={0}";

    /**
     * POST数据示例如下：
     {
     "endtime" : 987654321,
     "pageindex" : 1,
     "pagesize" : 10,
     "starttime" : 123456789
     }
     * 返回数格式如下：
     *  {
     "errcode": 0,
     "errmsg": "",
     "retcode": 0,
     "recordlist" : [
     {
     "openid" : "oDF3iY9WMaswOPWjCIp_f3Bnpljk",
     "opercode" : 2002,
     "text" : " 您好，客服test1为您服务。",
     "time" : 1400563710,
     "worker" : "test1"
     },
     {
     "openid" : "oDF3iY9WMaswOPWjCIp_f3Bnpljk",
     "opercode" : 2003,
     "text" : "你好，有什么事情？",
     "time" : 1400563731,
     "worker" : "test1"
     }
     ]
     }
     * @param access_token 调用接口凭证
     * @param starttime 查询开始时间，UNIX时间戳
     * @param endtime 查询结束时间，UNIX时间戳，每次查询不能跨日查询
     * @param pagesize 每页大小，每页最多拉取50条
     * @param pageindex 查询第几页，从1开始
     * @return
     *
     */
    public List<CcRecord> getRecord(String access_token, String starttime, String endtime, int pagesize, int pageindex );

    /**
     * GET方式,根据AppID获取公众号中所设置的客服基本信息，包括客服工号、客服昵称、客服登录账号。开发者利用客服基本信息，结合客服接待情况，可以开发例如“指定客服接待”等功能。
     * @param access_token 调用接口凭证
     * @return
     * {
    "kf_list" : [
    {
    "kf_account" : "test1@test",
    "kf_headimgurl" : "http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0",
    "kf_id" : "1001",
    "kf_nick" : "ntest1"
    },
    {
    "kf_account" : "test3@test",
    "kf_headimgurl" : "http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0",
    "kf_id" : "1003",
    "kf_nick" : "ntest3"
    }
    ]
    }
     */
    public List<KfInfo> getKfList(String access_token);

    public final String GET_KF_ONLINE_STATE = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token={0}";
    /**
     * 获取在线客服接待信息
     * @param access_token
     * @return
     * 返回数据示例（正确时的JSON返回结果）：
    {
    "kf_online_list": [
    {
    "kf_account": "test1@test",
    "status": 1,
    "kf_id": "1001",
    "auto_accept": 0,
    "accepted_case": 1
    },
    {
    "kf_account": "test2@test",
    "status": 1,
    "kf_id": "1002",
    "auto_accept": 0,
    "accepted_case": 2
    }
    ]
    }
     */
    public KfListInfo getKfOnlineState(String access_token);

    //客服接口-发消息
    public static final String SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={0}";

    /**
     * 客服接口-发消息
     * @param access_token 调用接口凭证
     * @param message 消息字符串
     * @return
     */
    public WeiXinResult<String> sendKfMessage(String access_token, String message);
}
