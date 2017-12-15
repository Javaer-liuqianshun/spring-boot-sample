package com.liuqs.springbootstudy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // 是一个组合注解,相当于@Controller + @ResponseBody
public class SpringbootstudyApplication {

    @Value("${study.name}")
    private String studyName;

    @Value("${study.date}")
    private String studyDate;

    @RequestMapping(value = "/")
    public String index() {
        return "我的名字叫: " + studyName + ", 我从 " + studyDate + " 开始学习Spring Boot.";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootstudyApplication.class, args);
    }
}
