package com.liuqs.sell.controller;

import com.liuqs.sell.config.ProjectUrlConfig;
import com.liuqs.sell.constant.CookieConstant;
import com.liuqs.sell.constant.RedisConstant;
import com.liuqs.sell.enums.ResultEnum;
import com.liuqs.sell.pojo.SellerInfo;
import com.liuqs.sell.service.SellerInfoService;
import com.liuqs.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-26
 * @ Modified:
 **/
@Controller
@RequestMapping(value = "/seller/user")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 登录
     *
     * @param openid
     * @param response
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/login")
    public String login(@RequestParam(value = "openid") String openid,
                        HttpServletResponse response,
                        ModelMap modelMap) {
        // 1.openid去和数据库的数据匹配
        SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(openid);
        if (null == sellerInfo) {
            log.info("【用户操作】登录失败");
            modelMap.addAttribute("msg", ResultEnum.LOGIN_FAIL.getMessage());
            modelMap.addAttribute("url", "/sell/seller/order/list");
            return "common/error";
        }
        // 2.设置token至redis
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, RedisConstant.EXPIRE, TimeUnit.SECONDS);
        // 3.设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

        /**
         * 跳转要用完整路径,这里为了方便使用相对路径
         */
        return "redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list";
    }

    /**
     * 登出
     *
     * @param request
     * @param response
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response,
                         ModelMap modelMap) {
        // 1.获取cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (null != cookie) {
            // 2.清除redis中存储的cookie
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            // 3.清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        modelMap.addAttribute("msg", ResultEnum.LOGIN_SUCCESS.getMessage());
        modelMap.addAttribute("url", "/sell/seller/order/list");
        return "common/success";
    }
}
