package com.liuqs.sell.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Author: liuqianshun
 * @ Description: 自定义日期格式
 * @ Date: Created in 2017-12-20
 * @ Modified:
 **/
public class CustomDateSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        System.out.println(1);
        System.out.println(1);
        System.out.println(1);
        System.out.println(1);
        jsonGenerator.writeNumber(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    }
}
