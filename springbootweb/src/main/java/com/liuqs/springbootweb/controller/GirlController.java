package com.liuqs.springbootweb.controller;

import com.liuqs.springbootweb.entity.Girl;
import com.liuqs.springbootweb.service.GirlService;
import com.liuqs.springbootweb.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private GirlService girlService;

    /**
     * 表单验证
     *
     * @param girl
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/girls")
    public Object girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setAge(10);
        girl.setCupSize("C");
        girl.setId(1);
        girl.setMoney(123.23d);
        return ResultUtil.success(girl);
    }

    /**
     *
     * 统一异常处理
     * 在Controller中抛出的异常,当没有被try_catch处理时,ExceptionHandle中定义的处理方法可以起到作用
     *
     * @param age
     * @throws Exception
     */
    @GetMapping(value = "/girls/getAge/{age}")
    public void getAge(@PathVariable  int age) throws Exception {
        girlService.getAge(age);
    }
}
