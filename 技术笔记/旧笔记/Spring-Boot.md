---
title: Spring Boot
date: 2020-09-20 15:29:43
tags:
---

# SpringBoot简介

[官方文档](https://spring.io/projects/spring-boot#learn)

* Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。
* SpringBoot所具备的特征有：
（1）可以创建独立的Spring应用程序，并且基于其Maven或Gradle插件，可以创建可执行的JARs和WARs；
（2）内嵌Tomcat或Jetty等Servlet容器；
（3）提供自动配置的“starter”项目对象模型（POMS）以简化Maven配置；
（4）尽可能自动配置Spring容器；
（5）提供准备好的特性，如指标、健康检查和外部化配置；
（6）绝对没有代码生成，不需要XML配置。
* 微服务（或微服务架构）是一种云原生架构方法，其中单个应用程序由许多松散耦合且可独立部署的较小组件或服务组成。这些服务通常有自己的堆栈，包括数据库和数据模型；
通过REST API，事件流和消息代理的组合相互通信；
* 它们是按业务能力组织的，分隔服务的线通常称为有界上下文。
* 尽管有关微服务的许多讨论都围绕体系结构定义和特征展开，但它们的价值可以通过相当简单的业务和组织收益更普遍地理解：
* 可以更轻松地更新代码。
* 团队可以为不同的组件使用不同的堆栈。
* 组件可以彼此独立地进行缩放，从而减少了因必须缩放整个应用程序而产生的浪费和成本，因为单个功能可能面临过多的负载。

# 快速入门

* 可以在[官网](https://start.spring.io/)直接配置下载入门项目。
* 也可以在idea中通过spring initializer快速创建springboot项目。
* ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20210314111338.png)
* 默认项目结构如下图：
    ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20210314111453.png)
  * 可以删除不必要的文件，如.mvn,.gitignore等。
    ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20210314112222.png)
* 项目结构解释：
  * `SpringbootApplicaton.java`（项目名+Application.java）:是整个SpringBoot项目的主入口，不能删也不能改。
  * `application.properties`是SpringBoot的配置文件（一般会用.yml文件）。
  * test包下默认有一个测试类：`SpringbootApplicationTests.java`，即在主入口名后加上Tests。
  * 项目的代码**放在Application的同级目录中，在此例中，所有业务代码都应放在`com.zestaken.springboot`包下**。（可以在这个包下再建包）
  * 编写controller层代码，在页面上显示hello：
  ```java
  package com.zestaken.springboot.controller;


    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class HelloController {

        @RequestMapping("/hello")
        public String hello() {
            return "hello springboot!";
        }
    }
    ```
    * 无需编写视图层，便能直接将结果显示到页面上：
    ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20210314113132.png)
    ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20210314113033.png)
* pom.xml配置解释：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
<!--    有一个父项目 继承spring-boot-starter-parent的依赖管理，控制版本与打包等内容-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.3</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.zestaken</groupId>
    <artifactId>springboot</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>springboot</name>
    <description>Demo project for Spring Boot</description>

<!--    java版本-->
    <properties>
        <java.version>1.8</java.version>
    </properties>


    <dependencies>

<!--        web依赖-->
<!--        所有springboot的依赖都是使用spring-boot-starter开头的-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

<!--        单元测试依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
<!--            打jar包的插件-->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```
 
* 修改项目的端口号，在application.properties中添加如下配置：
  ```properties
  # 更改项目的端口号为8081（默认为8080）
  server.port = 8081
  ```
    
* 修改背景图片：使用在线工具生成banner.txt 放到application.properties的同级目录下：
    ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/img/20210315182722.png)

# Springboot自动装配原理

## 配置解析

* `spring-boot-dependencies`:核心依赖，在父工程（`spring-boot-starter-parent`）中;
  * 作用：配置了springboot依赖的版本仓库，使我们在写或者引入springboot依赖的时候不用写版本号；
* `spring-boot-starter`:启动器
```xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter</artifactId>
<version>2.4.3</version>
</dependency>
```
  * 配置了SpringBoot的启动场景，比如spring-boot-starter-web就会帮我们导入web环境的所有的依赖。
  * SpringBoot会将所有的**功能场景，都变成一个个的启动器**。
  * 需要使用什么功能，只需要找到对应的启动器。
  * [启动器介绍文档](https://docs.spring.io/spring-boot/docs/2.4.3/reference/html/using-spring-boot.html#using-boot-starter)

## 主程序

* 本例中中主程序是SpringbootApplication(项目名+Application):
```java
package com.zestaken.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication //标注这个这个类是一个springboot的应用
public class SpringbootApplication {

    public static void main(String[] args) {
        //将SpringBoot应用启动
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
```
* `@SpringBootApplication`注解：这个注解是一个组合注解，其中包含的比较重要的注解如下
  *  `@SpringBootConfiguration`:springboot的配置注解，这个注解也是一个组合注解，包含的比较重要的注解如下：
     *  `@Configuration`：spring的配置注解，这个注解也是一个组合注解，包含的比较重要的注解如下：
        *  `@Component`:spring的组件注解。
     * 从根本来说，这个主程序也是**spring的一个组件**。
* `@EnableAutoConfiguration`:springboot自动配置注解，这个注解也是一个组合注解，包含的比较重要的注解如下：
  * `@AutoConfigurationPackage`:自动配置包注解，这个注解也是一个组合注解，包含的比较重要的注解如下：
    * `@Import({Registrar.class})`自动配置包的注册
  * `@Import({AutoConfigurationImportSelector.class})`:自动配置导入选择器，这个注解也是一个组合注解，包含的比较重要的注解如下：

# 主程序

* 实现的工作：
  1. 推断应用的类型是普通的项目还是web项目；
  2. 查找并加载所有可用的**初始化器**，设置到initializers属性中；
  3. 找出所有的应用程序监听器，设置到listeners属性中；
  4. 推断并设置main方法的定义类，找到运行的主类。

# SpringBoot配置

* SpringBoot使用一个全局的配置文件，配置文件的名称是固定的为`application`,但是可以使用两种格式：
  * `application.properties`:
    * 语法结构：`key = value`
  * `application.yml`:
    * 语法结构：`key : (此处有空格) value`
* 配置文件的作用：修改SpringBoot的自动配置的默认值。
* 在springboot项目中，在配置文件中设置`debug=true`（yaml中是`debug: true`），在控制台查看springboot自动配置的服务.
  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210318085939.png)
  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210318085955.png)
  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210318090009.png)
  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210318090018.png)

## yaml

* YAML 是 "YAML Ain't a Markup Language"（YAML 不是一种标记语言）的递归缩写。
* YAML 的语法和其他高级语言类似，并且可以简单表达清单、散列表，标量等数据形态。它使用**空白符号缩进**和大量依赖外观的特色，特别适合用来表达或编辑数据结构、各种配置文件、倾印调试内容、文件大纲（例如：许多电子邮件标题格式和YAML非常接近）。
* **YAML 的配置文件后缀为 .yml或者.yaml**，如：`application.yml`或者`application.yaml`，官方推荐使用`.yaml`
* yaml的特点：
  * 大小写敏感
  * 使用缩进表示层级关系 
  * 缩进不允许使用tab，只允许空格
  * 缩进的空格数不重要，只要相同层级的元素左对齐即可
  * `#`表示注释。
* YAML 支持以下几种数据类型：
  * 对象：键值对的集合，又称为映射（mapping）/ 哈希（hashes） / 字典（dictionary）
  * 数组：一组按次序排列的值，又称为序列（sequence） / 列表（list）
  * 纯量（scalars）：单个的、不可再分的值
* 纯量（普通的数据）：
  * `name: zestaken`
* 对象:
  * 空格缩进存对象：
  ```yml
  # 包含name和age属性值的student对象。
  studen:
   name: zestaken
   age: 19
  ```
  * 行内使用花括号：
  ```yml
  # 还是student对象
  student: {name: zestaken, age: 19}
  ```
  * 利用yaml可以存储对象的特点，可以用于给**对象赋值**。
* 数组：
  * 使用空格缩进和`-`:
  ```yml
  # 包含三个元素的pets数组
  pets:
   - cat
   - dog
   - pig
  ```
  * 行内使用中括号：
  ```yml
  pets: [cat, dog, pig]
  ```
###  使用yaml给对象赋值：

* Yaml注入配置文件@ConfigurationProperties
1. 在springboot项目中的resources目录下新建一个文件 application.yml
2. 编写一个实体类 Dog
```java
@Component//注册bean到容器中 
public class Dog {

    private String name;
    private Integer age;
     //有参无参构造、get、set方法、toString()方法 
}
```
`3. 思考，我们原来是如何给bean注入属性值的！ @Value，给狗狗类测试一下：
```java
@Component 
//注册bean 
public class Dog {    
	@Value("阿黄")    
	private String name;    
	@Value("18")    
	private Integer age; 
}
```
4. 在SpringBoot的测试类下注入狗狗输出一下；
```java
@SpringBootTest 
class DemoApplicationTests {
    @Autowired //将狗狗自动注入进来    
    Dog dog;
    @Test    
    public void contextLoads() {        
    System.out.println(dog); //打印看下狗狗对象    
}
```
  * 结果成功输出，@Value注入成功，这是我们原来的办法
5. 我们再编写一个复杂一点的实体类：Person 类
```java
@Component
public class Person {
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String, Object> map;
    private List<Object> list;
    private Dog dog;
```
6. 我们来使用yaml配置的方式进行注入，大家写的时候注意区别和优势，我们编写一个yaml配置
```yml
Person:
  name: zyx${random.uuid} #随机uuid
  age: ${random.int} #随机int
  happy: true
  birth: 1992/10/07
  map: {k1: v1,k2: v2}
  list:
    - 唱歌
    - 跳舞
    - 表演
  dog:
    name: haha
    age: 3
```
7、我们刚才已经把person这个对象的所有值都写好了，我们现在来注入到我们的类中！\
```java
 //@ConfigurationProperties作用： 将配置文件中配置的每一个属性的值，映射到这个组件中；告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
//参数 prefix = “person” : 将配置文件中的person下面的所有属性一一对应 
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String, Object> map;
    private List<Object> list;
    private Dog dog;
```
8、IDEA 提示，springboot配置注解处理器没有找到，让我们看文档，我们可以查看文档，找到一个依赖
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210317182009.png)
```xml
<!-- 导入配置文件处理器，配置文件进行绑定就会有提示，需要重启 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```
9、确认以上配置都OK之后，我们去测试类中测试一下
```java
@SpringBootTest
class Springboot01PropertyApplicationTests {

    @Autowired
    private Person person;//将person自动注入进来
    @Test
    void contextLoads() {

        System.out.println(person); //打印person信息 
    }

}
```
* 结果：所有值全部注入成功！

## JSR-303校验

* JSR-303 是 JAVA EE 6 中的一项子规范，叫做 Bean Validation。
* Bean Validation 为 JavaBean 验证定义了相应的元数据模型和 API。缺省的元数据是 Java Annotations，通过使用 XML 可以对原有的元数据信息进行覆盖和扩展。在应用程序中，通过使用 Bean Validation 或是你自己定义的 constraint，例如 @NotNull, @Max, @ZipCode， 就可以确保数据模型（JavaBean）的正确性。constraint 可以附加到字段，getter 方法，类或者接口上面。对于一些特定的需求，用户可以很容易的开发定制化的 constraint。Bean Validation 是一个运行时的数据验证框架，在验证之后验证的错误信息会被马上返回。
* Bean Validation 规范内嵌的约束注解：
![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210317183152.png)
* SpringBoot使用：
  1. 引入依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

   2. 给参数对象添加校验注解
```java
@Data
public class User {
    
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$", message = "密码必须为8~16个字母和数字组合")
    private String password;
    @Email
    private String email;
    private Integer gender;

}
```
   3. Controller 中需要校验的参数Bean前添加 @Valid 开启校验功能，紧跟在校验的Bean后添加一个BindingResult，BindingResult封装了前面Bean的校验结果。
```java
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("")
    public Result save (@Valid User user , BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            Map<String , String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach( (item) -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put( field , message );
            } );
            return Result.build( 400 , "非法参数 !" , map);
        }
        return Result.ok();
    }
}
```


## 配置文件的位置

* SpringBoot配置文件默认可以放到以下目录中，可以自动读取到：
   1. 项目根目录下
   2. 项目根目录中config目录下
   3. 项目的resources目录下
   4. 项目resources目录中config目录下
   ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210318082734.png)
* 不同位置的配置文件的优先级：
  1. config/application.properties（项目根目录中config目录下）
  2. config/application.yml
  3. application.properties（项目根目录下）
  4. application.yml
  5. resources/config/application.properties（项目resources目录中config目录下）
  6. resources/config/application.yml
  7. resources/application.properties（项目的resources目录下）
  6. resources/application.yml
   *  注：
     * 如果同一个目录下，有application.yml也有application.properties，默认先读取application.properties。
     * 如果同一个配置属性，在多个配置文件都配置了，默认使用第1个读取到的，后面读取的不覆盖前面读取到的。
     * 创建SpringBoot项目时，一般的配置文件放置在“项目的resources目录下”

## 多环境配置

* 在日常开发中，我们项目会有多个环境。例如开发环境（develop）、生产环境（production ）等。在这，springboot给我们提供了非常好的支持，那就是多环境配置。我们可以将多种环境一起配置在项目中，只需要执行不同的运行命令，就可以达到切换环境的目的了。
1. 在Spring Boot中多环境配置文件名需要满足application-{profile}.properties的格式
其中{profile}对应你的环境标识，比如：
  * application-dev.properties：开发环境
  * application-test.properties：测试环境
  * application-prod.properties：生产环境
2. 至于哪个具体的配置文件会被加载，需要在`application.properties`文件中通过spring.profiles.active属性来设置，其值对应{profile}值。如：`spring.profiles.active=test`就会加载`application-test.properties`配置文件内容。**因为默认是使用`application.properties`.**
3. 同样的，也可以创建多个yaml文件来实现多环境配置，但是yaml还提供了**一个文件可以写多个配置模块的功能**，可以用一个yaml文件配置多环境。
   1. 在一个yml文件中，通过 `---` 分隔多个不同配置，根据`spring.profiles.active` 的值来决定启用哪个配置，例如：
   ```yml
   #公共配置
    spring:
      profiles:
        active: pro #使用名为pro的配置，这里可以切换成dev
      datasource:
        url: jdbc:mysql://localhost:3306/test_db?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
        password: 123456
        username: root
    ---
    #开发环境配置
    spring:
      profiles: dev #profiles属性代表配置的名称

    server:
      port: 8080
    ---
    #生产环境配置
    spring:
      profiles: pro

    server:
      port: 80
  ```

# SpringBoot静态资源导入

* 通过Shift+Ctrl+N快捷键搜索`WebMvcAutoConfiguration.class`文件，进入此文件。我们在这个文件中找到一个名为`WebMvcAutoConfigurationAdapter`的静态类，找到此类下的`addResourceHandlers(ResourceHandlerRegistry registry)`方法，代码如下：
```java
public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//判断是否有自定义配置
            if (!this.resourceProperties.isAddMappings()) {
                logger.debug("Default resource handling disabled");
            } else {
                Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
                CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
                if (!registry.hasMappingForPattern("/webjars/**")) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }

                String staticPathPattern = this.mvcProperties.getStaticPathPattern();
                if (!registry.hasMappingForPattern(staticPathPattern)) {
                    this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern}).addResourceLocations(WebMvcAutoConfiguration.getResourceLocations(this.resourceProperties.getStaticLocations())).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
                }

            }
        }
```
* 读一下源码，当路径是/webjars/**，会从classpath:/META-INF/resources/webjars/找资源
  * 什么是webjars 呢？
    * WebJars是以Jar形式为Web项目提供资源文件，然后借助Maven这些依赖库的管理，保证这Web资源版本唯一性
  * Webjars多应用于基于Spring Boot创建微服务项目，需要打包所有资源为可执行的jar
  * 网站：https://www.webjars.org
  * 拿jQuery为例，要使用jQuery，只需导入对应的pom依赖
  ```xml
  <dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.4.1</version>
  </dependency>
  ```
  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210318094929.png)
  * 运行：只要是静态资源，SpringBoot就会去对应的路径寻找资源，我们这里访问：http://localhost:8080/webjars/jquery/3.4.1/jquery.js
* 当请求是`/**`时，会去下面这四个目录找静态资源
  * "classpath:/META-INF/resources/":就是上述的webjars
  * "classpath:/resources/":reources目录下的resources目录，自己新建
  * "classpath:/static/":resources目录下的static目录，项目自带
  * "classpath:/public/":resources目录下的public目录，自己新建
  * public,static,/**,resources 访问方式为localhost:8080/
    * 优先级顺序为：META-INF/resources > resources > static > public。
  * 测试：在public目录下新建一个1.js, 运行， 访问http://localhost:8080/1.js 即可看到1.js的内容。
* 我们可以通过**在配置文件中修改`spring.mvc.static-path-pattern`来修改默认的映射路径**，例如我修改为`/hong/**`,运行的时候访问http://localhost:8080/hong/index.html 才会对应到index.html的页面.

## 首页与图标定制

* 首页可以放在任何一个静态资源目录里，取名为index，就可以被映射到 http://localhost:8080/端口;
  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210318180843.png)
* 网站的图标，可以在配置文件中关闭默认图标以后，把想要使用的图标放到静态资源目录里，就可以生效:
  ```yml
  #关闭默认图标
  spring.mvc.favicon.enabled=false
  ```
* 较新版本中，不需要关闭默认图标，只需要把图标名称和格式写为：`favicon.ico`，放到静态资源目录下就可以了
  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210318181059.png)

# thymyleaf模板引擎

[thymyleaf官方文档](https://www.thymeleaf.org/documentation.html)
* 模板引擎是为了使用户界面与业务数据（内容）分离而产生的，它可以生成特定格式的文档，用于网站的模板引擎就会生成一个标准的[HTML]文档；SpringBoot推荐使用模板引擎
* 开发传统Java WEB工程时，我们可以使用JSP页面模板语言，但是在SpringBoot中已经不推荐使用了。SpringBoot支持如下页面模板语言
  * Thymeleaf
  * FreeMarker
  * Velocity
  * Groovy
  * JSP
  * 上面并没有列举所有SpringBoot支持的页面模板技术。其中Thymeleaf是SpringBoot官方所推荐使用的，下面来谈谈Thymeleaf一些常用的语法规则。
  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210318184053.png)
  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210318184148.png)
* 要想使用Thhymeleaf，首先要在pom.xml文件中单独添加Thymeleaf依赖。
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
  * Spring Boot默认存放模板页面的路径在`src/main/resources/templates`或者`src/main/view/templates`，这个无论是使用什么模板语言都一样，当然默认路径是可以自定义的，不过一般不推荐这样做。另外Thymeleaf默认的页面文件后缀是`.html`。
* 我们首先得按照SpringBoot的自动配置原理看一下我们这个Thymeleaf的自动配置规则，在按照那个规则，我们进行使用。我们去找一下Thymeleaf的自动配置类：`ThymeleafProperties`:
  ![](https://zjpicture.oss-cn-beijing.aliyuncs.com/giteePic/picgo-master/20210318184337.png)
* 在MVC的开发过程中，我们经常需要通过Controller将数据传递到页面中，让页面进行动态展示。
  * 创建一个Controller对象，在其中进行参数的传递
  ```java
  @Controller
  public class ThymeleafController {

      @RequestMapping(value = "show", method = RequestMethod.GET)
      public String show(Model model){
          model.addAttribute("uid","123456789");
          model.addAttribute("name","Jerry");
          return "show";
      }
  }
  ```
  * 在SpringBoot默认的页面路径下创建show.html文件，内容如下
  ```html
  <!DOCTYPE HTML>
  <html xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>SpringBoot模版渲染</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
  </head>
  <body>
      <p th:text="'用户ID：' + ${uid}"/>
      <p th:text="'用户名称：' + ${name}"/>
  </body>
  </html>
  ```
  * 首先引入thymeleaf命名空间约束`xmlns:th="http://www.thymeleaf.org"`
  * 可以看到在p标签中有`th:text`属性，这个就是thymeleaf的语法，它表示显示一个普通的文本信息。
## thymeleaf语法

1. th属性
   * `th:text`：文本替换；
   * `th:utext`：支持html的文本替换。
   * `th:value`：属性赋值
   * `th:each`：遍历循环元素
   * `th:if`：判断条件，类似的还有`th:unless，th:switch，th:case`
   * `th:insert`：代码块引入，类似的还有`th:replace，th:include`，常用于公共代码块提取的场景
   * `th:fragment`：定义代码块，方便被th:insert引用
   * `th:object`：声明变量，一般和`*{}`一起配合使用，达到偷懒的效果。
   * `th:attr`：设置标签属性，多个属性可以用逗号分隔
2. 标准表达式语法
   * `${...}` 变量表达式，Variable Expressions
     * 常用的内置对象
       * `ctx` ：上下文对象
       * `vars` ：上下文变量
       * `locale`：上下文的语言环境
       * `request`：（仅在web上下文）的 HttpServletRequest 对象
       * `response`：（仅在web上下文）的 HttpServletResponse 对象
       * `session`：（仅在web上下文）的 HttpSession 对象
       * `servletContext`：（仅在web上下文）的 ServletContext 对象
     * 常用的内置方法
       * `strings`：字符串格式化方法，常用的Java方法它都有，比如：`equals，equalsIgnoreCase，length，trim，toUpperCase，toLowerCase，indexOf，substring，replace，startsWith，endsWith，contains，containsIgnoreCase`等
       * `numbers`：数值格式化方法，常用的方法有：formatDecimal等
       * `bools`：布尔方法，常用的方法有：isTrue，isFalse等
       * `arrays`：数组方法，常用的方法有：toArray，length，isEmpty，contains，containsAll等
       * `lists`,`sets`：集合方法，常用的方法有：toList，size，isEmpty，contains，containsAll，sort等
       * `maps`：对象方法，常用的方法有：size，isEmpty，containsKey，containsValue等
       * `dates`：日期方法，常用的方法有：format，year，month，hour，createNow等
3. `@{...}` 链接表达式，Link URL Expressions
4. `#{...}` 消息表达式，Message Expressions
5. `~{...}` 代码块表达式，Fragment Expressions
6. `*{...}` 选择变量表达式，Selection Variable Expressions

