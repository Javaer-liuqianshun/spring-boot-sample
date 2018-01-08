package com.liuqs.sell.enums;

import lombok.Getter;

/**
 * @ Author: liuqianshun
 * @ Description: 商品状态
 * @ Date: Created in 2017-12-17
 * @ Modified:
 **/
@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0, "在架"),
    DOWN(1, "下架"),
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
