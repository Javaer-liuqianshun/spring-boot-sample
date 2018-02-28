package com.liuqs.sell.handler;

import com.liuqs.sell.exception.SellException;
import com.liuqs.sell.exception.SellerAuthorizeException;
import com.liuqs.sell.pojo.VO.ResultVO;
import com.liuqs.sell.utils.ResultVOUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ Author: liuqianshun
 * @ Description: 对Controller中未捕获的异常进行统一捕获
 * @ Date: Created in 2018-02-26
 * @ Modified:
 **/
@ControllerAdvice
public class SellerExceptionHandler {

    @ExceptionHandler(value = SellerAuthorizeException.class)

    public String handlerSellerAuthorizeException() {
        // 登录校验 异常则跳转到 loginVerifyError.html页面
        return "common/loginVerifyError";
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 出现异常,希望返回的状态码不是200
    @ResponseBody
    public ResultVO handlerSellException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
