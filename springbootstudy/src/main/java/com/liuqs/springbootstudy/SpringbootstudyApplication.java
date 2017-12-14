package com.liuqs.springbootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // 是一个组合注解,相当于@Controller + @ResponseBody
public class SpringbootstudyApplication {

	@RequestMapping(value = "/")
	public String index(){
		return "Hello Spring Boot, I Want Conquer You!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootstudyApplication.class, args);
	}
}
