package com.liuqs.springbootreadproperties;

import com.liuqs.springbootreadproperties.entity.Study;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootreadpropertiesApplication {

	@Autowired
	private Study studyBean;

	@GetMapping("/index")
	public String index(){
		return studyBean.say();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootreadpropertiesApplication.class, args);
	}
}
