package com.liuqs.sell.constant;

/**
 * @ Author: liuqianshun
 * @ Description: redis 相关常量
 * @ Date: Created in 2018-02-26
 * @ Modified:
 **/
public interface RedisConstant {
    // 前缀
    String TOKEN_PREFIX = "token_%s";
    // 过期时间
    Integer EXPIRE = 60 * 60;
}
