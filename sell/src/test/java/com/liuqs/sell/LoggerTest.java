package com.liuqs.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j //使用lombok的@Slf4j注解,直接调用log.info(),log.error()就可以输出日志
public class LoggerTest {

    @Test
    public void test1(){
        String name = "liuqs";
        String password = "123456";
        log.debug("debug...");
        log.info("info....");
        log.info("name:{},password:{}",name,password);
        log.error("error...");
        log.warn("warn....");
    }
}
