package com.liuqs.springbootreadproperties.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-03-07
 * @ Modified:
 **/
@PropertySource("classpath:config/study.properties")
@ConfigurationProperties(prefix = "study")
@Component
public class Study {
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
