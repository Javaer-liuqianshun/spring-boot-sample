# Spring Boot 学习记录
## springbootstudy（简单入门案例）  
* @SpringBootApplication：是Spring&nbsp;Boot的核心注解，是一个组合注解，由@Configuration、@ComponentScan和@EnableAutoConfiguration组成。
其中@EnableAutoConfiguration会让Spring&nbsp;Boot根据类路径中的jar包依赖为当前项目自动配置。 
* 关闭自动配置@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
***
## springbootreadproperties（Spring Boot使用自定义的properties）
* 什么是类型安全配置？ 答：让一个配置文件的元数据与一个Bean及其属性相关联。
* @ConfigurationProperties(prefix = '')：prefix属性指定properties配置文件的前缀，同时@ConfigurationProperties将properties属性和一个Bean及其属性关联，从而实现类型安全配置
* @PropertySource("classpath:config/study.properties")：读取配置文件
* 日志配置
* profile配置：通过在application.properties中spring.profiles.active来指定活动的Profile，规则：application-{profile}.properties
***
## springbootfreemarker（Spring Boot整合FreeMarker）
* 导入freemarker start pom ：spring-boot-starter-freemarker
* 在application.properties中配置freemarker
***
## springbootconditional(@Conditional及@ConditionalXXX衍生注解的使用)
* @Bean：作用在方法上，声明该方法返回的对象是一个Bean
* @Conditional：需要一个类对象作为参数，该对象实现Condition接口，覆盖matches()方法，只有该方法返回true时，执行该方法；即根据条件实例化Bean
* @ConditionalOnClass：需要一个类对象作为参数，当项目中存在该类对象（包括自己创建和各jar中的）实例化Bean
* @ConditionalOnMissingClass：需要一个对象路径作为参数，当该对象路径指向的类对象不存在，实例化Bean
* @ConditionalOnBean：需要一个类对象作为参数，当该对象仅仅在当前上下文存在时，实例化Bean
* @ConditionalOnMissingBean：需要一个类对象作为参数，当该对象仅仅在当前上下文中 不 存在时，实例化Bean
* @ConditionalOnExpression：需要一个表达式作为参数，当该表达式为true时，实例化Bean
* @ConditionalOnProperty：需要一个配置项作为参数，当该配置项为true时，实例化Bean
***
## springbootweb(web进阶使用)
### 表单验证
在JavaBean类中的成员变量上使用@Min、@Max、@Null、@NotNull、@Size...等注解来对成员变量设置的值进行校验。<br>
在Controller层中成员方法入参之前使用@Valid，表示对该入参进行校验，还需BindingResult类型的参数，把校验的结果存入该参数。使用bindingResult.hasErrors()判断校验是否失败。
```
@PostMapping(value = "/girls")
    public Object girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }
    }
```
### 使用AOP处理请求,打印log日志
@Component注解声明让spring管理该类。<br>
@Aspect注解声明该类是一个切面类。<br>
@Ponitcut注解声明切入点。格式：
```
@Pointcut("execution(public * com.liuqs.springbootweb.controller.GirlController.girlAdd(..))")
    public void log() {

    }
```
@Beafore注解是前置通知。<br>
@Around注解是环绕通知，需要ProceedingJoinPoint对象的入参，调用ProceedingJoinPoint.proceed()方法调用目标对象。<br>
@After、@AfterReturn、@AfterThrowing注解都是后置通知。@After和@AfterReturn区别是@AfterReturn可以获取目标对象的返回值。@AfterThrowing是出现异常后调用。<br>

### 统一异常处理
@ControllerAdvice注解声明该类是Controllerce层增强类。<br>
@ExceptionHandler注解声明该方法捕获异常。<br>
@ControllerAdvice和@ExceptionHandler的联合使用表示对Controller层抛出的异常进行捕获处理。<br>
***
## sell(Spring Boot基本使用)
sell项目是一个简单的外卖系统，目的是加强对SpringBoot的使用。<br>
### 数据库配置
```
pom.xml中：
<!-- MySQL驱动 -->
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
</dependency>

application.yml中：
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: 123456
    url: jdbc:mysql://192.168.1.4:3306/sell?characterEncoding=utf-8&useSSL=false
```
### spring-jpa使用
```
pom.xml中：
<!-- Spring data-jpa -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
JavaBean类需要添加@Entity声明该类是一个实体类，类中的成员变量与表中字段一一对应。<br>
Dao层接口需要继承JpaRepository<T,ID extends Serializable>,T代表实体类，ID extends Serializable代表主键 id的类型。<br>

### 微信原生和微信MP SDK获取access_token的使用
使用 [微信Java开发包工具](https://github.com/Wechat-Group/weixin-java-tools)，不用重复造轮子。
```
pom.xml中：
<!-- 微信公众号 -->
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-mp</artifactId>
  <version>2.9.0</version>
</dependency>
```
### 使用Redis解决分布式(针对统一项目部署在多个服务器上)Seesion一致性问题(登录案例)
1. 把token设置到redis中
```
String token = UUID.randomUUID().toString();
redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, RedisConstant.EXPIRE, TimeUnit.SECONDS);
```
2. 把token设置到cookie中
```
Cookie cookie = new Cookie(name, value);
cookie.setPath("/");
cookie.setMaxAge(maxAge);
response.addCookie(cookie);
```
3. 自动登录
在用户登录一次后，浏览器保存了名为token的cookie，利用AOP技术，在执行每一个Controller层方法时，获取Request中名为token的cookie。
获得到cookie后，用cookie.getValue()从redis中获取openid，最后根据openid获取用户信息完成自动登录。
### 使用WebSocket建立长连接进行推送
前端JS代码,配合h5标准的websocket:
```
<script>
    var websocket = null;
    if ('WebSocket' in window){
        websocket  = new WebSocket('ws://localhost:8080/sell/webSocket');
    }else{
        alert('该浏览器不支持websocket！');
    }

    // 连接成功建立的回调方法
    websocket.onopen = function () {
        console.log('建立连接');
    }

    // 连接关闭的回调方法
    websocket.onclose = function () {
        console.log('连接关闭');
    }

    // 接受到消息的回调方法
    websocket.onmessage = function (event) {
        console.log('收到消息:' + event.data);
        // 弹窗提示,播放音乐
    }

    // 连接发生错误的回调方法
    websocket.onerror = function () {
        alert('websocket通信发生错误!')
    }

    // 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    websocket.onbeforeunload  = function () {
        websocket.onclose();
    }
</script>
```
```
pom.xml中:
<!-- websocket -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```
使用@ServerEndpoint创立websocket endpoint。首先要注入ServerEndpointExporter，这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
```
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
```
websocket的具体实现类
```
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {
    
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();


    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】有新的连接,总数:" + webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        log.info("【websocket消息】连接断开,总数:" + webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【websocket消息】收到客户端发来的消息:{}", message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            log.info("【websocket消息】广播消息,message ={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
```
### 使用redis解决秒杀超卖(更适用分布式，如果不是分布式，可以适用多线程线程安全来加锁）
用apache benchmarking压力测试：
```
ab -n 100 -c 10 http://localhost:8080/sell/skill/order/123456
```
模拟100个请求，并发量为10。出现超卖的情形：
```
国庆活动,皮蛋粥特价,限量份100000 还剩: 99990份 该商品成功下单用户数目:100 人
```
剩余数 + 下单数 = 100090 > 10000，说明出现超卖。<br>
ab压力测试源码：
```
@Override
public void orderProductMockDiffUser(String productId) {
    //1.查询该商品库存,为0则活动结束
    int stockNum = stock.get(productId);
    if (stockNum == 0){
        throw new SellException(100,"活动结束");
    }else{
        // 2.下单(模拟不同用户openid不同)
        orders.put(KeyUtil.getUniqueKey(),productId);
        // 3.减库存
        stockNum = stockNum -1;
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        stock.put(productId,stockNum);
    }
}
```
分析：但并发量大的时候，都会在Thread.sleep(100)等待100ms，会到导致减库存操作虽然执行但是没有保存，在并发中的每一个 线程拿到的stockNum都是没有保存之前的stockNum，所以导致只下单，没有真正减库存操作，俗称超卖的现象。<br>

解决方案：适用redis来加锁
```
@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     *
     * @param key
     * @param value 当前时间 + 超时时间
     * @return
     */
    public boolean lock(String key, String value) {

        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        String currentValue = redisTemplate.opsForValue().get(key);
        // 如果锁过期
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            // 获取上一个锁,并设置新的锁
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    public void unlock(String key,String value){
        try{
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("【redis锁】解锁异常,{}",e);
        }
    }
}
```
ab压测测试源码改造后：
```
@Override
public void orderProductMockDiffUser(String productId) {
    // 加锁
    long time = System.currentTimeMillis() + TIMEOUT;
    if (!redisLock.lock(productId, String.valueOf(time))) {
        throw new SellException(101,"哎呦喂，人也太多了，换个姿势再试试~~");
    }

    System.out.println("单身多年，手速快~~~~~~~~~~");

    //1.查询该商品库存,为0则活动结束
    int stockNum = stock.get(productId);
    if (stockNum == 0) {
        throw new SellException(100, "活动结束");
    } else {
        // 2.下单(模拟不同用户openid不同)
        orders.put(KeyUtil.getUniqueKey(), productId);
        // 3.减库存
        stockNum = stockNum - 1;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stock.put(productId, stockNum);
    }

    // 解锁
    redisLock.unlock(productId,String.valueOf(time));
}
```




