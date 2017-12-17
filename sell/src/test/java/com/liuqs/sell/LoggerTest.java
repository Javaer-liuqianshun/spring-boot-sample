package com.liuqs.sell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoggerTest {

    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        String name = "liuqs";
        String password = "123456";
        logger.debug("debug...");
        logger.info("info....");
        logger.info("name:{},password:{}",name,password);
        logger.error("error...");
        logger.warn("warn....");
    }
}
