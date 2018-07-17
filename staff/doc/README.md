框架说明文档
-----

[TOC]

# 目录


## 项目运行
这是一个maven项目，关于如何导进eclipse，可参照网上的教程。

打成war包是在项目目录下(trunk/code/xxx)执行命令mvn clean package -Dmaven.test.skip，生成的war包就会在该目录下的target文件夹下.

运行：
	可以在eclipse里配置tomcat的方式运行，这种方式的话起来后访问地址就是http://localhost:8080/xxx/admin/index
	也可以在eclipse里用jetty:run的方式运行，具体步骤参照网上教程，这种方式的话起来后访问地址就是http://localhost:8080/admin/index
	登陆用户名密码是admin/333333

数据库文件是项目下的 doc/db.sql

数据库配置文件是项目下的 src/main/resources/app.properties

## 几个重要原则
### 不要相信用户的输入
需要做验证的地方，后台一定要做验证，只有前端的验证是不够的，不要相信用户会老实地按要求输入
### 宁缺勿滥
数据的正确性和完整性是最重要的，尤其是涉及到钱的时候，宁可操作失败回滚，也不能让错误的数据写入数据库。如果一个请求做了多件事（比如写了a表，又写了b表），一定要保证在一个事务里面。
### 宁可错杀一千，不可错放一人
重要的操作明细尽可能地详细记录，不要嫌字段多，可能N天也用不到这些明细，一旦出问题的时候，就会感激这些明细
### 提供查错的方式
重要的操作，一定要写日志，或记录数据库，不然，如果线上出了问题，完全没地方可查原因

## 数据库规范
1. 表名全部小写, 多个单词的用下划线分隔，比如sys_user, 注意，这里的sys_相当于表名的前缀，当表比较多时，可以用前缀来给表划分模块。视具体情况而定。
1. 每个表都要有个主键id，类型为bigint(20)，并且自增
1. 表名要有中文注释，所有字段也要有中文注释
1. 字段用下划线分隔，比如`last_login_time`
1. 如果引用了其它一个表，比如`sys_user`表的id，字段就是写`user_id`（不用考虑前缀`sys_`）
1. 布尔类型用tinyint(1)
1. 状态，类型这种字段用int
1. 如果引用了这个表本身的id比如父id，字段名也是以`_id`结尾，比如`parent_menu_id`
1. 日期都是用datetime

## 登陆和权限控制
* 采用的spring security框架
* 配置文件是applicationContext-security.xml
* 登陆时查找用户的代码在MyUserDetailsService
* 登陆成功后会执行MyAuthenticationSuccessHandler进行后续处理
* MyUsernamePasswordAuthenticationFilter过滤器可以阻止用户登陆，可以用来实现验证码这种登陆前的逻辑
* 编码的方式登陆request.login(username, password)，这是servlet3的功能。


## 日志
* 配置文件log4j.xml，里面可以配置接收异常的邮箱
* 代码里建议使用slf4j来写，比如定义logger
  
  `private static Logger logger = LoggerFactory.getLogger(Xxx.class)`
  
  输出日志
  
  `logger.info("更新用户{}成功", userId)`
* 建议在关键业务的地方，都输出日志，级别为info


## API文档

使用swagger2进行api文档的管理。

根据实际情况修改SwaggerConfig的配置。

swagger2注解的使用可参照ApiExampleController。

访问`http://localhost:8080/swagger-ui.html`即可看到swagger文档界面。
# 杂项
## 读取properties文件的值
* 利用@Value可以读取*.properties中的配置项,比如

` @Value("${imageType}")`

` private Integer type;`

如果没有默认值会报错，指定默认值的方法是

`@Value("${imageType:1}")`

## 防止重复提交
### 配置   
1. 在main-servlet.xml里增加以下配置

   ```
   <mvc:interceptors>  
      <mvc:interceptor>  
        <mvc:mapping path="/admin/**/*" />  
        <bean id="controllerInterceptor" class="com.hnluchuan.core.support.TokenInterceptor"></bean>
      </mvc:interceptor>  
   </mvc:interceptors>  

   ```
2. 在父controller，一般都有个@ExceptionHandler标注的方法，在这个方法里，增加如下代码
    
    ```
    // 把token还原回去
    	String token = request.getParameter(TokenInterceptor.FORM_NAME);
    	if (StringUtils.isNotBlank(token)) {
    		@SuppressWarnings("unchecked")
			Set<String> set = (Set<String>) request.getSession().getAttribute(TokenInterceptor.SET_KEY);
    		if (set != null) {
    			set.add(token);
    		}
    	}
    ```
    这个request是方法里的参数，完整方法参考如下
    
    ```
    @ResponseBody
    @ExceptionHandler
    public String onException(Exception e, HttpServletRequest request) {
    	// 把token还原回去
    	String token = request.getParameter(TokenInterceptor.FORM_NAME);
    	if (StringUtils.isNotBlank(token)) {
    		@SuppressWarnings("unchecked")
			Set<String> set = (Set<String>) request.getSession().getAttribute(TokenInterceptor.SET_KEY);
    		if (set != null) {
    			set.add(token);
    		}
    	}
    	String msg = e.getMessage();
    	if (e instanceof KException) {
    		msg = e.getMessage();
    	} else {
    		logger.error(e.getMessage(), e);
    	}
    	if (e instanceof HibernateOptimisticLockingFailureException) {
    	    msg = "请勿重复操作！";
        }
        JSONObject json = new JSONObject();
        json.put("success", false);
        json.put("msg", msg);
        return json.toJSONString();
    }
    ```
    
### 使用
1. 在需要打开页面的方法上增加注解@Token(save = true)，比如在AdminExampleController的detail方法上。
2. 在提交的方法上增加@Token(remove = true)，比如在controller的save方法上。
3. 在提交的表单里，增加以下隐藏输入项

```
<input type="hidden" name="_token" value="${_token }"/>
```
# Hibernate相关知识

## model的id不可更改
从数据库查出来的model，id是不可更改的，其它字段都是可以改的

## Hibernate的三种状态
关于hibernate的三种状态可以参考
`http://www.cnblogs.com/binjoo/articles/1621254.html`
一般不需要过多关注，需要注意的是，在持久态的model，如果更改了某些属性，就算不手动调用dao的update方法，这些更改也会保存到数据库。因为怕误用，所以框架里基本上model都不会逃出service层跑到controller层，都是返回对应的dto对象，就是怕在controller里有误用（以为不会更改到数据库所以修改了model的属性）。

虽然不手动调用dao的update也会更新，但开发时所有的更新的地方都要手动调用dao的update，以免产生二义性。

## 常用HQL语法
* 查询User表

  `from User where ...`
  
* select的用法
  
  `from User n ` 和 `select n from User n`是等价的，所以省略select的时候，默认就是查出from后面的model
  
  假设如果User对象有个Card对象属性(即User里有个private Card card字段，想从User里查出card，select部分可以写成如下
  
  `select n.card from User n`
  
* Hibernate继承映射(@Inheritance)

 参考`http://blog.csdn.net/benjamin_whx/article/details/44521315` 

## 增加事务
在方法增加注解@Transactional，就会保证方法里执行的在一个事务里，service里的对数据库进行了写操作的都要加上这个，如果在controller方法里调了多个service的方法，为了保证多个service方法都是在一个事务里，需要在controller的方法上增加@Transactional

## 定时任务
在类上增加@Component
在方法加增加@Scheduled注解，就可以变成定时任务了

## 事件驱动
Spring可以发布一个事件，然后由监听这个事件的监听器进行处理，这样的好处是

1. 解耦，比如注册完用户后要执行某些后续操作，而注册用户的地方又有好几个，这时候就可以在注册完后发送一个事件，然后让监听器处理。
2. 异步处理，比如某个api在执行成功后需要发送邮件，而发送邮件是比较费时的，这时就可以把发送邮件这一步用事件通知来处理。

###使用方法

1. 自定义一个event类，继承自ApplicationEvent
 
 ```
public class LogEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    
    public LogEvent(Object source) {
        super(source);
    }
}
```
2. 自定义一个监听器(记得加@Component)


  ```
@Component
public class LogEventListener implements ApplicationListener<LogEvent> {
    private static Logger logger = Logger.getLogger(LogEventListener.class);
    
    @Autowired
    private SystemLogService systemLogService;
    
    @Override
    @Transactional
    @Async
    public void onApplicationEvent(LogEvent event) {
    	
        systemLogService.create((SystemLogDTO) event.getSource());
        if (logger.isDebugEnabled()) {
            logger.debug("created system log");
        }
    }
}
```

3. 在需要发布事件的地方，比如注册完成后，通过applicationContext进行发布事件

  `
applicationContext.publishEvent(new LogEvent(new User()));
`

  applicationContext是注入的
  
  `
  @Autowired
    private ApplicationContext applicationContext;
  `
  
### 注意事项
在Listener的`onApplicationEvent`方法里，可以增加@Async注解，这个注解的意思是异步执行。如果不加这个注解，则这个listener和发送事件的地方，是同一个线程执行，并且是在同一个事务里的，也就是说，如果onApplicationEvent方法里抛了异常，那么发送事件那里的事务也会回滚。

如果加了@Async注解的话，就是一个新的线程来执行，成功失败都与发送事件的事务无关。

因为是一个新的线程来执行，所以如果在这个方法里要操作数据库，就要加@Transactional注解。

所以要根据实际情况来使用，比如像发邮件这种，适合用@Async，第一不会影响发起事件的方法的执行时间，第二成功失败对发起事件的事务没有影响。

又比如，用户微信支付成功后，保存了支付信息，然后进行支付后的处理，比如开通会员，这种情况也是适合用@Async的，不然如果开通会员失败了的话，保存的支付信息也会回滚，会让我们错误地以为用户支付没有成功。

### 线程池
既然可以异步执行，那么异步的线程是从哪来的？在applicationContext.xml的配置文件里

```
<bean id ="taskExecutor" class ="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor" >  
	    <property name ="corePoolSize" value ="30" /> 
	    <property name ="keepAliveSeconds" value ="200" /> 
	    <property name ="maxPoolSize" value ="50" /> 
	    <property name ="queueCapacity" value ="5" /> 
    </bean>
```
除非特殊情况，一般不需要调整都是够用的。

### 和new Thread的区别
像发邮件这种，也可以通过new Thread的方式来做

```
new Thread() {
    public void run() {  
      // 发邮件
    }
}.start();
```

和事件的方式 有什么区别呢？最大的区别就是，这种方式没法访问数据库，会报no session的异常。这是spring的机制决定的。

另外这种方式不能调得太频繁，不然会开大量的线程（因为没有线程池的控制），把系统搞挂。事件方式的话，线程池有个maxPoolSize，超过这个size就不会新开线程.





## 乐观锁
### 问题
考虑以下情况

有个帐户表account，金额字段money=100，user_id = 1，表示1这个用户有100块钱。

接下来对这个用户的帐户进行改动，增加20元钱，第一个线程操作如下

```
Account a = findByUser();   // step 1
a.setMoney(a.getMoney() + 20)  // step2
update(a)  // step3
```
假设在step2的时候，另一个地方，也刚好在操作这个用户的帐户，为这个用户扣10元钱，代码如下

```
Account a = findByUser();   // step 1
a.setMoney(a.getMoney() - 10)  // step2
update(a)  // step3

```

由于第一个线程还没有执行step3，所以，第二个线程从数据库读出account来的时候，money还是100。

然后第一个线程执行step3，数据库这行记录money被更新为120了。

然后第二个线程执行step3，数据库这行记录money被更新为90了。

最终这个用户的帐户就是90。

很显然，这是不对的。正确的，应该是100 + 20 - 10 = 110。

### 什么是乐观锁/悲观锁
为了防止上面的这种情况，最暴力的情况就是锁表，也就是悲观锁，为什么叫悲观呢，因为它很悲观，老是以为会出现多个线程同时修改同一行记录，所以把表锁了。很显然这种方式最大的问题就是性能。如果更新一次要1秒钟，10个请求同时操作就要等10秒钟了，不说应对高并发，就算是中等并发也应对不了。

而乐观锁就比较乐观了，它觉得不会有人和它改同一条记录，所以不锁表。但是，万一有人和它同时修改同一个记录，它就会抛出异常，更新不成功。毕竟，与其数据出错，不如抛异常。

### 实现方式
比如要对Account增加乐观锁，步骤如下：

1. 在account表增加version int(10) not null default 0
2. 在Account.java增加version字段，并在字段上增加@Version注解，如

```
@Version
private Integer version = 0;
```

3. 可以了。

### 实现原理
hibernate在更新的时候，会把version放在where条件里，并且将值增加1保存到数据库，比如
```
update Account set version = 2, money = 120 where id = 1 and version = 1
```

如果返回的更新记录数为0，就抛个异常，因为没有记录被更新的话，说明没有id = 1 and version = 1的记录了，说明version被别的线程修改了，这就说明同一条记录被两个线程同时修改了。

## 浮点数安全
### 问题
浮点数的计算是不准确的，不光乘法，加减法也是，考虑如下的代码

```
System.out.println(0.2 + 0.4);
```
输出不会是0.6，而是0.6000000000000001

### 解决方案
1. 数据库对应字段用Decimal，并且指定精度，比如Decimal(10,2)，避免数据库出现不精确的小数
2. 在代码里double/float做运算，都要用MoneyUtils进行操作
3. 只从数据库取出来，然后显示出来，就不需要特殊处理，因为没有做运算，所以，不会有精度问题
4. 页面格式化显示用<fmt:format标签，比如
  	
  	```
  	<fmt:formatNumber type="number" value="${totalAmount}" pattern="0.00" maxFractionDigits="2"/>
  	```