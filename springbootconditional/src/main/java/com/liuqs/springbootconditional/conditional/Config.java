package com.liuqs.springbootconditional.conditional;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @ Author: liuqianshun
 * @ Description:
 * @ Date: Created in 2017-12-18
 * @ Modified:
 **/
@Configuration
public class Config {

    /**
     * Conditional注解需要一个类对象作为参数，该对象实现Condition接口，
     * 覆盖matches()方法，只有该方法返回true时，执行该方法
     * @return
     */
    @Bean //声明当前返回的对象是一个Bean
    @Conditional(MyCondition.class)
    public String myCondition(){
        System.out.println("自定义的condition(MyCondition)的matches方法返回值为true时，进入该方法实例化bean");
        return "";
    }


    /**
     * ConditionalOnClass注解要一个类对象作为参数，当项目中存在该类对象（包括自己创建和各jar中的）实例化Bean
     * @return
     */
    @Bean
    @ConditionalOnClass(Abc.class)
    public String onClass(){
        System.out.println("当Abc.class在项目中存在时，实例化一个Bean");
        return "";
    }

    /**
     * ConditionalOnMissingClass注解要一个对象路径作为参数，当该对象路径指向的类对象不存在，实例化Bean
     * @return
     */
    @Bean
    @ConditionalOnMissingClass("com.liuqs.springbootconditional.conditional.Cba")
    public String onMissingClass(){
        System.out.println("当某个对象在项目中不存在时，实例化一个Bean");
        return "";
    }

    /**
     * ConditionalOnBean注解需要一个类对象作为参数，当该对象仅仅在当前上下文中存在时，实例化Bean
     * @return
     */
    @Bean
    @ConditionalOnBean(Abc.class)
    public String onBean(){
        System.out.println("仅仅在当前上下文中存在某个对象时，实例化一个Bean");
        return "";
    }

    /**
     * ConditionalOnMissingBean注解需要一个类对象作为参数，当该对象仅仅在当前上下文中 不 存在时，实例化Bean
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(Abc.class)
    public String onMissingBean(){
        System.out.println("仅仅在当前上下文中 不 存在某个对象时，实例化一个Bean");
        return "";
    }

    /**
     * conditionalOnExpression注解需要一个表达式作为参数，当该表达式为true时，实例化Bean
     * @return
     */
    @Bean
    @ConditionalOnExpression(value = "1==1")
    public String expresssion() {
        System.out.println("当该表达式为true时，实例化Bean");
        return "";
    }
}
