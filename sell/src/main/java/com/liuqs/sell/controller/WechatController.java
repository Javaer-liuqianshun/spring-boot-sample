package com.liuqs.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ Author: liuqianshun
 * @ Description:微信公众号接入Controller
 * @ Date: Created in 2017-12-23
 * @ Modified:
 **/
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    // appid
    private final String APPID = "wx6cd62266aa038924";
    // appsecret
    private final String APPSECRET = "9a711885bea1d12226313c82d18f3be2";
    // 获取access_token的url
    private final String GET_ACCESS_TOREN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * 获取微信access_token
     *
     * @return
     */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("code") String code) {
        log.info("进入微信authorize方法");
        log.info("【票据code】:{}",code);
        // 1.替换url
        String url = GET_ACCESS_TOREN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
        // 2.使用Spring的RestTemplate发起get请求
        RestTemplate httpClient= new RestTemplate();
        //String object = httpClient.getForObject(url, String.class);
        ResponseEntity<String> entity = httpClient.getForEntity(url,String.class);

        //log.info("【getForObject】:{}",object);
        if (entity.getStatusCodeValue() == 200){
            log.info("【请求成功】响应信息:{}",entity);
        }else{
            log.info("【请求失败】statusCode:{}",entity.getStatusCodeValue());
        }

        return null;
    }
}
