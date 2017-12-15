package com.liuqs.springbootsecuritybean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootsecuritybeanApplication {

    @Autowired
    private StudyServiceImpl studyServiceImpl;

    @RequestMapping(value = "/index")
    public String index(){
        String say = studyServiceImpl.say();
        return say;
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootsecuritybeanApplication.class, args);
	}
}
