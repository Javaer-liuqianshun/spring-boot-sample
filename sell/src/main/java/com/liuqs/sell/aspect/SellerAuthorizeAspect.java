package com.liuqs.sell.aspect;

import com.liuqs.sell.constant.CookieConstant;
import com.liuqs.sell.constant.RedisConstant;
import com.liuqs.sell.exception.SellerAuthorizeException;
import com.liuqs.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ Author: liuqianshun
 * @ Description: 登录校验 AOP
 * @ Date: Created in 2018-02-26
 * @ Modified:
 **/
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.liuqs.sell.controller.Seller*.*(..))" +
            "&& !execution(public * com.liuqs.sell.controller.SellerUserController.*(..))")
    public void verify() {
    }


    /**
     * 在每个方法调用前 进行登录校验
     */
    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (null == cookie) {
            log.info("【登录校验】Cookie中查询不到token");
            throw new SellerAuthorizeException();
        }
        // 去redis中查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.info("【登录校验】redis中查询不到token");
            throw new SellerAuthorizeException();
        }
    }
}
