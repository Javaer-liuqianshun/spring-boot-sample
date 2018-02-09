package com.liuqs.springbootweb.entity;

import lombok.Data;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-09
 * @ Modified:
 **/
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
}
