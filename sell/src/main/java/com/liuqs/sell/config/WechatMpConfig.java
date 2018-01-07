package com.liuqs.sell.config;

import lombok.Data;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2017-12-25
 * @ Modified:
 **/
@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WechatMpConfig {

    private String mpAppId;
    private String mpAppSecret;

    @Bean
    public WxMpService wxOpenService() {
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxOpenService;
    }

    private WxMpConfigStorage wxOpenConfigStorage() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(mpAppId);
        wxMpInMemoryConfigStorage.setSecret(mpAppSecret);
        return wxMpInMemoryConfigStorage;
    }
}
