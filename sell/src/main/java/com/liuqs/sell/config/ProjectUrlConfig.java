package com.liuqs.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-02-26
 * @ Modified:
 **/
@Component
@ConfigurationProperties(prefix = "projectUrl")
@Data
public class ProjectUrlConfig {

    private String sell;
}
