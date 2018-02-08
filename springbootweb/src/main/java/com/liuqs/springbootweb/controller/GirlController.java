package com.liuqs.springbootweb.controller;

import com.liuqs.springbootweb.entity.Girl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @ Author: liuqianshun
 * @ Description:
 *
 * 包括表单验证
 *
 * @ Date: Created in 2018-02-08
 * @ Modified:
 **/
@RestController
public class GirlController {

    /**
     * 表单验证
     *
     * @param girl
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/girls")
    public Girl girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        return girl;
    }
}
