package com.liuqs.springbootweb.entity;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-08
 * @ Modified:
 **/
@Data
public class Girl {
    private Integer id;
    private String cupSize;
    @Min(value = 18,message = "未成年少女禁止入内")
    //@NotNull
    //@Max
    //@Length
    private Integer age;

}
