package com.liuqs.springbootweb.exception;

import com.liuqs.springbootweb.enums.ResultEnum;
import lombok.Getter;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-09
 * @ Modified:
 **/
@Getter
public class WebException extends RuntimeException {

    private Integer code;

    public WebException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        code = resultEnum.getCode();
    }
}
