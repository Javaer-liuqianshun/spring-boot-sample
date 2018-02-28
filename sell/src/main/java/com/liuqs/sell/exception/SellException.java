package com.liuqs.sell.exception;


import com.liuqs.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * @ Author: liuqianshun
 * @ Description: 自定义异常
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Getter
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
