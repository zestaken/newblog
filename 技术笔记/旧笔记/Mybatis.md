---
title: Mybatis
date: 2021-02-08 22:23:17
tags: [数据库, Mybatis]
categories: 技术笔记
---

# Mybatis简介

* MyBatis 是一款优秀的**持久层框架**。
  * 持久化：将程序的数据永久存储到硬盘上；
  * 持久层：完成持久化工作的代码块，即三层架构中的**DAO层**。
* MyBatis 避免了几乎所有的JDBC代码和手动设置参数以及获取结果集。
* MyBatis 可以使用简单的 **XML 或注解来配置和映射原生信息**，将接口和 Java 的 POJOs(Plain Ordinary Java Object,普通的 Java对象)映射成数据库中的记录。
* Mybatis资料：
  * [官方文档](https://mybatis.org/mybatis-3/zh/index.html)
  * [下载地址](https://github.com/mybatis/mybatis-3/releases)
  * maven坐标：
```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.6</version>
</dependency>
```
* 优点：
  * 简单易学：本身就很小且简单。没有任何第三方依赖，最简单安装只要两个jar文件+配置几个sql映射文件。
  * 灵活：mybatis不会对应用程序或者数据库的现有设计强加任何影响。 **sql写在xml里**，便于统一管理和优化。通过sql语句可以满足操作数据库的所有需求。
  * 解除sql与程序代码的耦合：通过提供DAO层，将业务逻辑和数据访问逻辑分离。
  * 提供xml标签，支持编写动态sql。

# 快速入门

## 一：环境搭建

1. 通过maven导入三个jar包：
   1. mysql驱动包：
   2. junit包；
   3. mybatisjar包
```xml
    <dependencies>
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.23</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13</version>
            <scope>test</scope>
        </dependency>>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.6</version>
        </dependency>
    </dependencies>
```

## 二：编写核心配置文件

* `mybatis-config.xml`,maven项目放在resources目录下：
```xml
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <!--配置数据源-->
            <dataSource type="POOLED">
                <!--注册驱动，同jdbc-->
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <!--数据库连接路径-->
                <property name="url" value="jdbc:mysql://localhost:3306/youth_study"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
    <!--每一个Mapper.xml文件都需要在这个mybatis核心配置文件中注册-->
    <mappers>
        <mapper resource="org/mybatis/example/BlogMapper.xml"/>
    </mappers>
</configuration>
```

## 三：编写Mybatis工具类

 * 编写一个用来获取SQLSession实例的工具类：
```java
package com.zestaken.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //1.加载核心配置文件
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //2.根据xml核心配置文件获取对应的SqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2.从SqlSessionFactory实例中获得 SqlSession 的实例。
    // SqlSession 提供了在数据库执行 SQL 命令所需的所有方法。
    // 你可以通过 SqlSession 实例来直接执行已映射的 SQL 语句。
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

}
```

## 四：编写操作表的接口并实现

* 接口：T_collegeMapper
```java
package com.zestaken.dao;

import com.zestaken.Pojo.T_college;

import java.util.List;

public interface T_collegeMapper {
    List<T_college> getT_collegeList();
}
```
* 实现：Mapper.xml实现了接口实现类的功能
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace绑定一个Mapper接口-->
<mapper namespace="com.zestaken.dao.T_collegeMapper">
    <!--id是需要实现的接口中的方法名-->
    <!--resultType是返回结果的类型，如果返回的是结果集，则用resultMap-->
    <!--select是要执行sql查询语句-->
    <select id="getUserList" resultType="com.zestaken.Pojo.T_college" >
    select * from youth_study.t_college;
  </select>
</mapper>
```
* 注：Mapper.xml文件需要在mybatis-config.xml文件中注册：
```xml
    <!--每一个Mapper.xml文件都需要在这个mybatis核心配置文件中注册-->
    <mappers>
        <mapper resource="org/mybatis/example/BlogMapper.xml"/>
    </mappers>
```

## 五：编写junit测试类

* 编写一个可以查询表的测试类：
```java
import org.junit.Test;

import java.util.List;

public class T_collegeMapperTest {
    @Test
    public void test(){
        //1.获取SqlSession实例
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //2.获取Mapper接口的实现类
        T_collegeMapper t_collegeMapper = sqlSession.getMapper(T_collegeMapper.class);
        //3.执行sql
        List<T_college> t_collegeList = t_collegeMapper.getT_collegeList();

        for(T_college college : t_collegeList){
            System.out.println(college);
        }

        //4.释放sqlSession
        sqlSession.close();
    }
}
```

## 六：常见错误

1. Mapper.xml文件没有在mybatis-config.xml文件中注册；
2. 没有配置maven，使maven可以导入java包下的配置文件。（因为maven默认所有配置文件都是放在resources目录下的）
   * 在pom.xml中配置，使maven可以导入java包下的配置文件：
```xml

    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
```

# Mybatis的CRUD操作

* 在mapper接口中增加对应操作的方法，在mapper.xml中增加对应的sql语句。
* 取参数：
  * **表对象传递的属性可以通过`#{}`取出使用，但是属性名，数据项的名字，以及sql中参数的名字需要一致**
  * **Map对象传递的参数也可以通过`#{}`取出使用，只需要参数名和map中的key名相同即可**。
* **增删改操作必须提交事务才能生效**。
* 增删改操作的返回值默认为int，这个类型不需要在`resultType`中设置，如果操作成功则返回1，操作失败返回0；
* 参数可以用`#{参数名}`取出来
* parameterType：参数类型
* resultType:返回值类型
* id：namespace中接口中的方法名
* 查询操作用`<select></select>`
* 添加操作用`<insert></insert>`
* 修改操作用`<update></update>`
* 删除操作用`<delete></delete>`
* 示例：
  1. mapper接口
```java

import com.zestaken.pojo.T_college;

import java.util.List;

public interface T_collegeMapper {
    //查询表中的所有值
    List<T_college> getT_collegeList();
    //根据id查询表中的值
    T_college getT_collegeByID(int id);
    //向表中插入新的数据
    int insertT_collegeList(T_college t_college);
    //修改表中某项的数据
    int updateT_collegeList(T_college t_college);
    //删除表中指定id的项
    int deleteT_collegeList(int id);

}
```
   2. mapper.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace绑定一个Mapper接口-->
<mapper namespace="com.zestaken.dao.T_collegeMapper">
    <!--id是需要实现的接口中的方法名-->
    <!--resultType是返回结果的类型，如果返回的是结果集，则用resultMap-->
    <!--select是要执行sql查询语句-->
    <select id="getT_collegeList" resultType="com.zestaken.pojo.T_college" >
    select * from youth_study.t_college;
  </select>
<!--    参数用#{}取出来-->
    <select id="getT_collegeByID" parameterType="int" resultType="com.zestaken.pojo.T_college">
        select * from youth_study.t_college where id = #{id};
    </select>
    <!--表对象中的属性可以直接取出来   -->
    <insert id="insertT_collegeList" parameterType="com.zestaken.pojo.T_college" >
-- 虽然参数是表对象，但是表对象中的属性可以直接取出来，就相当于传递的参数是一系列的属性值
        insert into youth_study.t_college(id,name) values(#{id},#{name});
    </insert>

    <update id="updateT_collegeList" parameterType="com.zestaken.pojo.T_college">
        update youth_study.t_college set name=#{name} where id = #{id};
    </update>
    <delete id="deleteT_collegeList" parameterType="int">
        delete from youth_study.t_college where id = #{id};
    </delete>
</mapper>
```
  3. 测试类
```java
package com.zestaken.dao;

import com.zestaken.pojo.T_college;
import com.zestaken.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class T_collegeMapperTest {
    @Test
    public void get(){
        //1.获取SqlSession实例
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //2.获取Mapper接口的实现类
        T_collegeMapper t_collegeMapper = sqlSession.getMapper(T_collegeMapper.class);
        //3.执行sql
        List<T_college> t_collegeList = t_collegeMapper.getT_collegeList();

        for(T_college college : t_collegeList){
            System.out.println(college);
        }

        //4.释放sqlSession
        sqlSession.close();
    }

    @Test
    public void getByID(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        T_collegeMapper mapper = sqlSession.getMapper(T_collegeMapper.class);

        T_college college = mapper.getT_collegeByID(100);
        System.out.println(college);


        sqlSession.close();
    }

    @Test
    public void insert(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        T_collegeMapper mapper = sqlSession.getMapper(T_collegeMapper.class);

        T_college t_college = new T_college();
        t_college.setId(100);
        t_college.setName("zhangjie");
        mapper.insertT_collegeList(t_college);

        //增删改操作必须提交事务才能生效
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void updateT_collegeList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        T_collegeMapper mapper = sqlSession.getMapper(T_collegeMapper.class);

        T_college t_college = new T_college();
        t_college.setId(100);
        t_college.setName("zhangsi");
        mapper.updateT_collegeList(t_college);

        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void deleteT_collegeList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        T_collegeMapper mapper = sqlSession.getMapper(T_collegeMapper.class);

        mapper.deleteT_collegeList(100);

        sqlSession.commit();

        sqlSession.close();
    }
}
```

# 配置解析

## 核心配置文件

* 核心配置文件（即mybatis-config.xml）.
* MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。 配置文档的顶层结构如下：
* configuration（配置）
  1. properties（属性）
  2. settings（设置）
  3. typeAliases（类型别名）
  4. typeHandlers（类型处理器）
  5. objectFactory（对象工厂）
  6. plugins（插件）
  7. environments（环境配置）
  8. environment（环境变量）
  9. transactionManager（事务管理器）
  10. dataSource（数据源）
  11. databaseIdProvider（数据库厂商标识）
  12. mappers（映射器）

## 环境配置（environments）

* MyBatis 可以配置成**适应多种环境**，这种机制有助于将 SQL 映射应用于多种数据库之中,现实情况下有多种理由需要这么做。例如，开发、测试和生产环境需要有不同的配置。
* 尽管可以配置多个环境，但**每个 SqlSessionFactory 实例只能选择一种环境**
* 配置详情：
  * 默认使用的环境默认ID（比如：default="development"）。
    * 默认环境和环境ID顾名思义。 环境可以随意命名，但务必保证默认的环境ID要匹配其中一个环境ID。。
  * 每个environment元素定义的环境ID（比如：id="development"）。
  * 事务管理器的配置（比如：type="JDBC"）。
    * Mybatis有两种事务管理器：JDBC、MANGED；
    * Spring+Mybatis的搭配没有必要设置事务管理器，因为会被Spring的配置覆盖。
  * 数据源的配置（比如：type="POOLED"）。
    * Mybatis有三种内建的数据源类型：
      * POOLED：这种数据源的实现利用“池”的概念将 JDBC 连接对象组织起来（主要使用POOLED）
      * UNPOOLED：这个数据源的实现会每次请求时打开和关闭连接。
      * JNDI：这个数据源实现是为了能在如 EJB 或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个 JNDI 上下文的数据源引用。
* 示例：
```xml
<environments default="development">
  <environment id="development">
    <transactionManager type="JDBC">
      <property name="..." value="..."/>
    </transactionManager>
    <dataSource type="POOLED">
      <property name="driver" value="${driver}"/>
      <property name="url" value="${url}"/>
      <property name="username" value="${username}"/>
      <property name="password" value="${password}"/>
    </dataSource>
  </environment>
</environments>
```

## 属性（properties）

* 通过properties**实现引用配置文件**；
* 示例：
  1. 创建db.properties文件
```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/youth_study
username=root
```
   2. 在mybatis-config.xml文件中导入配置文件
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- 核心配置文件 -->
<configuration>

    <properties resource="db.properties">
        <property name="password" value="root"/>
    </properties>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <!--配置数据源-->
            <dataSource type="POOLED">
                <!--注册驱动，同jdbc-->
                <property name="driver" value="${driver}"/>
                <!--数据库连接路径-->
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <!--每一个Mapper.xml文件都需要在这个mybatis核心配置文件中注册-->
    <mappers>
        <mapper resource="com/zestaken/dao/T_collegeMapper.xml"/>
    </mappers>
</configuration>
```
* properties配置**必须放在`<configuration></configuration>`标签内的最开始位置。
* properties属性可以直接引入外部的配置文件；
* 可以在properties标签中通过property标签动态设置属性
* 如果在property标签中动态配置的属性与外部配置文件有冲突，则**优先使用外部配置文件的配置**。

## 类型别名（typeAliases）

* 类型别名可为 Java 类型设置一个缩写名字。 
* 它仅用于 XML 配置，意在降低冗余的全限定类名书写。
* typeAliases配置需要写在配置的第三位，即properties之后。
* 三种配置别名的方法：
  1. 完全限定名配置：
```xml
<typeAliases>
  <typeAlias alias="Author" type="domain.blog.Author"/>
</typeAliases>
```
  2. 包名配置：
     1. Mybaits会在指定的包名下搜索需要的javabean
     2. 扫描实体类的包，它的默认名就为**这个类的类名，并且首字母小写**。
```xml
<typeAliases>
  <package name="domain.blog"/>
</typeAliases>
```
  3. 注解配置，需要结合包名配置：
     1. 每一个在包 domain.blog 中的 Java Bean，在没有注解的情况下，会使用 Bean 的首字母小写的非限定类名来作为它的别名。
     2. 若有注解，则别名为其注解值。
```xml
@Alias("author")
public class Author {
    ...
}
```

## 设置（settings）

* 常用设置：
  * cacheEnabled：全局性地开启或关闭所有映射器配置文件中已配置的任何缓存。
  * lazyLoadingEnabled：延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置 fetchType 属性来覆盖该项的开关状态。
  * logImpl：指定 MyBatis 所用日志的具体实现，未指定时将自动查找。
* 示例：
```xml
<settings>
  <setting name="cacheEnabled" value="true"/>
  <setting name="lazyLoadingEnabled" value="true"/>
  <setting name="multipleResultSetsEnabled" value="true"/>
  <setting name="useColumnLabel" value="true"/>
  <setting name="useGeneratedKeys" value="false"/>
  <setting name="autoMappingBehavior" value="PARTIAL"/>
  <setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>
  <setting name="defaultExecutorType" value="SIMPLE"/>
  <setting name="defaultStatementTimeout" value="25"/>
  <setting name="defaultFetchSize" value="100"/>
  <setting name="safeRowBoundsEnabled" value="false"/>
  <setting name="mapUnderscoreToCamelCase" value="false"/>
  <setting name="localCacheScope" value="SESSION"/>
  <setting name="jdbcTypeForNull" value="OTHER"/>
  <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
</settings>
```

## 插件（plugins）

* 可以使用一些mybaits插件来简化代码的书写。
* mybatis常用插件：
  1. mybatis-generator-core
  2. mybatis-plus
  3. 通用mapper

## 映射器（mappers）

* mapper.xml文件在使用之前必须注册，注册有三种方式。
1. 方式一：通过xml文件的完全限定名来注册（推荐使用）
```xml
<!-- 使用相对于类路径的资源引用 -->
<mappers>
  <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
  <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
  <mapper resource="org/mybatis/builder/PostMapper.xml"/>
</mappers>
```
2. 方式二：使用接口名来注册
```xml
<mappers>
  <mapper class="org.mybatis.builder.AuthorMapper"/>
  <mapper class="org.mybatis.builder.BlogMapper"/>
  <mapper class="org.mybatis.builder.PostMapper"/>
</mappers>
```
   * **接口和mapper.xml文件必须同名**
   * **接口和mapper配置文件必须再同一个包下**。
3. 方式三:使用包名来注册
```xml
<mappers>
  <package name="org.mybatis.builder"/>
</mappers>
```
   * **接口和mapper.xml文件必须同名**
   * **接口和mapper配置文件必须再同一个包下**。


# Mybatis注解开发

* 不使用mapper.xml文件来实现Mapper接口，而使用注解的方式来实现接口。
* 示例：
```java
package org.mybatis.example;
public interface BlogMapper {
  @Select("SELECT * FROM blog WHERE id = #{id}")
  Blog selectBlog(int id);
}
```
* 同时还需要在mybatis-config.xml文件中绑定接口：
```xml
<mappers>
  <mapper class="org.mybatis.example.BlogMapper"/>
</mappers>
```

## 注解实现CRUD

1. 在编写工具类的时候实现自动提交事务：
```java
    public static SqlSession getSqlSession(){
      //只需要传递一个boolean参数即可
        return sqlSessionFactory.openSession(true);
    }
```
2. 编写接口增加注解：
   1. 增：`@insert()`
   2. 删：`@delete()`
   3. 改：`@update()`
   4. 查：`@select()`
3. `@param()`参数设置：
   1. 基本类型的参数或者String类型的参数，需要加上；
   2. 引用类型的参数不用加；
   3. 如果只有一个基本类型的话，可以忽略，但是建议加上；
   4. 在sql中引用`@param()`设置的属性名
   5. 如果`@param()`括号中没有内容，则属性名和参数名默认相同。
   6. 示例：
```java
@delete(delete from youth_study.t_college where id = #{id} and name = #{name})
int delete(@param("id") int id, @param("name") String name);
```

# Mybatis日志

* 操作出现了异常的时候，日志是很好的排错工具；
* settings中的logImpl可以设置日志的实现方式，共有以下几种：
  1. SLF4J 
  2. LOG4J 
  3. LOG4J2 
  4. JDK_LOGGING 
  5. COMMONS_LOGGING 
  6. STDOUT_LOGGING：标准日志输出，一旦在settins中启用，便无需其他的配置 
  7. NO_LOGGING
* 配置STDOUT_LOGGING:
```xml
<settings>
  <setting name = "logImpl" value = "STDOUT_LOGGING"/>
</settings>
```

