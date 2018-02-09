package com.liuqs.springbootweb.handle;

import com.liuqs.springbootweb.entity.Result;
import com.liuqs.springbootweb.exception.WebException;
import com.liuqs.springbootweb.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ Author: liuqianshun
 * @ Description:
 *
 * 在Controller中抛出的异常,当没有被try_catch处理时,ExceptionHandle中定义的处理方法可以起到作用
 * 在方法使用@ExceptionHandler
 *
 *
 * @ Date: Created in 2018-02-09
 * @ Modified:
 **/
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof WebException){
            WebException webException = (WebException) e;
            return ResultUtil.error(webException.getCode(),webException.getMessage());
        }
        return ResultUtil.error(-1, "未知错误");
    }

}
