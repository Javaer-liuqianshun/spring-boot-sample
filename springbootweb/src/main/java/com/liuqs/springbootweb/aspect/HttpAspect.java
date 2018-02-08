package com.liuqs.springbootweb.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-08
 * @ Modified:
 **/
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.liuqs.springbootweb.controller.GirlController.girlAdd(..))")
    public void log(){

    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        logger.info("url={}",request.getRequestURL());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //参数
        logger.info("args={}",joinPoint.getArgs());
        System.out.println(joinPoint.getKind());
        System.out.println("1111");
    }

    @After("log()")
    public void doAfter(){
        System.out.println("22222");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturing(Object object){
        logger.info("response={}",object.toString());
    }


}
