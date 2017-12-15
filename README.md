# Spring Boot 学习记录
## springbootstudy（简单入门案例）  
* @SpringBootApplication：是Spring&nbsp;Boot的核心注解，是一个组合注解，由@Configuration、@ComponentScan和@EnableAutoConfiguration组成。
其中@EnableAutoConfiguration会让Spring&nbsp;Boot根据类路径中的jar包依赖为当前项目自动配置。 
* 关闭自动配置@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
## springbootsecuritybean（基于properties类型安全配置）
* 什么是类型安全配置？ 答：让一个配置文件的元数据与一个Bean及其属性相关联。
* @ConfigurationProperties(prefix = '')：prefix属性指定properties配置文件的前缀，同时@ConfigurationProperties将properties属性和一个Bean及其属性关联，从而实现类型安全配置
* @PropertySource("classpath:config/study.properties")：读取配置文件
* 日志配置
* profile配置：通过在application.properties中spring.profiles.active来指定活动的Profile，规则：application-{profile}.properties
## springbootfreemarker（Spring Boot整合FreeMarker）
* 导入freemarker start pom ：spring-boot-starter-freemarker
* 在application.properties中配置freemarker



