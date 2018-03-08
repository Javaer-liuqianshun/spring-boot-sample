package com.liuqs.springbootfastjson.controller;

import com.liuqs.springbootfastjson.entity.UserEntity;
import com.liuqs.springbootfastjson.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-03-08
 * @ Modified:
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/list")
    public List<UserEntity> list(){
        return userRepository.findAll();
    }
}
