package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.controller.job;

import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.common.WeiXinResult;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.menu.*;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.model.token.AccessToken;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.rpc.UTF8MediaType;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.service.WeiXinTokenService;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.WeiXinUtil;
import com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils.WeixinMenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
@Controller
@Path("/weixinMenu")
public class WeiXinMenuController {

    @Autowired
    private WeiXinTokenService tokenService;

    @GET
    @Path("/createMenu")
    @Produces(UTF8MediaType.JSON)
    public WeiXinResult createMenu() {
        try {
            AccessToken token = tokenService.fetch();
            int result = 0;
            if (token != null) {
                /**调用接口创建菜单**/
                result = WeiXinUtil.createMenu(getMenu(), token.getAccess_token());
            }
            return 0 == result ? WeiXinResult.success() : WeiXinResult.failed(result);
        } catch (Exception e) {
            e.printStackTrace();
            return WeiXinResult.failed(-1,e.getMessage());
        }
    }

    private static Menu getMenu()
    {
        return WeixinMenuUtil.getMenu();
    }

    private static Menu getMenuTest() {
        System.out.println("weixin log in menu");

        ViewButton btn1 = new ViewButton();
        btn1.setName("首页");
        btn1.setType("view");
        btn1.setUrl("");

        ViewButton btn2 = new ViewButton();
        btn2.setName("子页");
        btn2.setType("view");
        btn2.setUrl("");

        ViewButton btn3 = new ViewButton();
        btn3.setName("个人");
        btn3.setType("view");
        btn3.setUrl("");

        ViewButton btn4 = new ViewButton();
        btn4.setName("活动");
        btn4.setType("click");
        btn4.setUrl("");

        CommonButton btn22 = new CommonButton();
        btn22.setName("活动");
        btn22.setType("click");
        btn22.setKey("");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("");
        mainBtn1.setSub_button(new ViewButton[] {btn2, btn4});

        /***
         * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义？
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该怎样定义
         * menu.setButton(new Button[]{mainBtn1, mainBtn2, btn33})
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] {btn1, btn2, btn3});
        return menu;
    }
}
