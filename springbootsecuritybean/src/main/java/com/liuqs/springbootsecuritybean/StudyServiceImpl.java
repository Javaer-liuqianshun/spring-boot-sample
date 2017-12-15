package com.liuqs.springbootsecuritybean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * @ Author: liuqianshun
 * @ Description:类型安全配置，@ConfigurationProperties将properties属性和一个Bean及其属性关联，从而实现类型安全配置
 * prefix = "study"属性指定properties配置文件的前缀，private String name属性名对应properties配置文件的后缀
 * @ Date: Created in 2017-12-14
 * @ Modified:
 **/
@Service
@PropertySource("classpath:config/study.properties")
@ConfigurationProperties(prefix = "study")
public class StudyServiceImpl {

    private String name;
    private String date;
    private String place;

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String say() {
        return name + ", " + date + ", " + place;
    }

}
