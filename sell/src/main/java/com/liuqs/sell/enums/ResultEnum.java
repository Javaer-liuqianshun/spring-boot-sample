package com.liuqs.sell.enums;


import lombok.Getter;

/**
 * @ Author: liuqianshun
 * @ Description: 返回信息
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Getter
public enum ResultEnum {

    PRODUCT_NO_EXIST(10,"商品不存在"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
