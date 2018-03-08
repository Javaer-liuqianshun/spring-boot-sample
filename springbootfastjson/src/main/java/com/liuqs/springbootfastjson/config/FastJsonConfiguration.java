package com.liuqs.springbootfastjson.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2018-03-08
 * @ Modified:
 **/
@Configuration
public class FastJsonConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        // 创建FastJson消息转化器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        /** 修改配置返回内容的过滤

         FastJson SerializerFeature:
             WriteNullListAsEmpty：List字段如果为null,输出为[],而非null
             WriteNullStringAsEmpty： 字符类型字段如果为null,输出为"",而非null
             DisableCircularReferenceDetect：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
             WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
             WriteMapNullValue：是否输出值为null的字段,默认为false。
         */
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
        );
        //处理中文乱码问题
        List<MediaType> fastMediaType = new ArrayList<>();
        fastMediaType.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaType);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);



    }
}
