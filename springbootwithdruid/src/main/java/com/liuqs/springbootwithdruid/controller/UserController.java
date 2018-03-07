package com.liuqs.springbootwithdruid.controller;

import com.liuqs.springbootwithdruid.entity.UserEntity;
import com.liuqs.springbootwithdruid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-03-07
 * @ Modified:
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 查询用户方法
     */
    @GetMapping(value = "/list")
    public List<UserEntity> findAllUser(){
        List<UserEntity> list = userRepository.findAll();
        return list;
    }

}
