package com.liuqs.sell.enums;

import lombok.Getter;

/**
 * @ Author: liuqianshun
 * @ Description: 支付状态
 * @ Date: Created in 2017-12-19
 * @ Modified:
 **/
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
