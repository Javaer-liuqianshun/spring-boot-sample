package com.liuqs.sell.handler;

import com.liuqs.sell.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ Author: liuqianshun
 * @ Description: 对Controller中未捕获的异常进行统一捕获
 * @ Date: Created in 2018-02-26
 * @ Modified:
 **/
@ControllerAdvice
public class SellerExceptionHandler {

    @ExceptionHandler
    public String handler(Exception e){
        if (e instanceof SellerAuthorizeException){
            // 如果是 登录校验 异常则跳转到 http://www.baidu.com
            return "common/loginVerifyError";
        }
        return "common/error";
    }
}
