package com.liuqs.sell.controller;


import com.liuqs.sell.enums.ResultEnum;
import com.liuqs.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2017-12-25
 * @ Modified:
 **/
@Controller
@RequestMapping("/wechat/sdk")
@Slf4j
public class WechatBySDKController {

    @Autowired
    private WxMpService wxMpService;

    /**
     * 请求url:如http://m4miac.natappfree.cc/sell/wechat/sdk/authorize?returnUrl=http://www.baidu.com
     * 获取code
     *
     * @param returnUrl
     * @return
     */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        // 获取code返回的url地址
        String url = "http://7sde3g.natappfree.cc/sell/wechat/sdk/userinfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    /**
     * 通过code获取到accessto_token
     *
     * @param code
     * @param returnUrl
     * @return
     */
    @GetMapping("/userinfo")
    public String userinfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("【微信网页授权】{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
