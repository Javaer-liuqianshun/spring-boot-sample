package com.liuqs.sell.utils;

import java.util.Random;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式:时间+随机数
     *
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
