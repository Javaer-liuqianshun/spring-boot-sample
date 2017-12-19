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
## springbootconditional(@Conditional及@ConditionalXXX衍生注解的使用)
* @Bean：作用在方法上，声明该方法返回的对象是一个Bean
* @Conditional：需要一个类对象作为参数，该对象实现Condition接口，覆盖matches()方法，只有该方法返回true时，执行该方法；即根据条件实例化Bean
* @ConditionalOnClass：需要一个类对象作为参数，当项目中存在该类对象（包括自己创建和各jar中的）实例化Bean
* @ConditionalOnMissingClass：需要一个对象路径作为参数，当该对象路径指向的类对象不存在，实例化Bean
* @ConditionalOnBean：需要一个类对象作为参数，当该对象仅仅在当前上下文存在时，实例化Bean
* @ConditionalOnMissingBean：需要一个类对象作为参数，当该对象仅仅在当前上下文中 不 存在时，实例化Bean
* @ConditionalOnExpression：需要一个表达式作为参数，当该表达式为true时，实例化Bean
* @ConditionalOnProperty：需要一个配置项作为参数，当该配置项为true时，实例化Bean



