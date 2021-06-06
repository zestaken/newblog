---
title: BeanUtils
date: 2021-01-04 21:36:25
tags: [Java, BeanUtils]
categories: 技术笔记
---

# BeanUtils介绍

* BeanUtils是**Apache commens**组件里面的成员，由Apache提供的一套开源api，用于简化对**javaBean**的操作，能够对基本类型自动转换。
* 功能：封装数据; 
  * 我们知道一个JavaBean通常包含了**大量的属性**，很多情况下，对JavaBean的处理导致了大量的get/set代码堆积，增加了代码长度和阅读代码的难度，而BeanUtils可以对Bean中的属性进行封装。
## JavaBean

* javaBean实质就是**java类**，只不过是遵循了某种规范的java类。
* 遵循的规范：
  1. 类必须用public来修饰;
  2. 必须具有一个无参的构造方法
  3. 属性必须私有化
  4. 私有化的属性必须通过public类型的方法来暴露，也就是说要出现setXXX()、getXXX()或者isXXX()的方法;
* JavaBean示例：
```java
public class Student {
	private String name;
	private String id;
	private int age;
	private String sex;
	private Date d;
	public Student() {
		super();
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", age=" + age
				+ ", sex=" + sex + ", d=" + d + "]";
	}
}
```


## 配置BeanUtils

1. 下载BeanUtils:[官网](http://commons.apache.org/proper/commons-beanutils/download_beanutils.cgi)，下载好组件，再到项目里面引入jar文件。
2. 导入核心包
   * commons-beanutils-1.9.3.jar
   * commons-logging-1.2.jar
   * 注意：当缺少日志jar包，会出现如下的报错情况。java.lang.NoClassDefFoundError: org/apache/commons/logging/LogFactory
   * logging组件的[下载地址](http://commons.apache.org/proper/commons-logging/download_logging.cgi)


# BeanUtils方法

* 把orig中的值copy到dest中:`public void copyProperties(java.lang.Object dest, java.lang.Object orig)`
* 把Bean的属性值放入到一个Map里面:`public java.util.Map describe(java.lang.Objectbean)`
* 把properties里面的值放入bean中:` public void populate(java.lang.Object bean, java.util.Mapproperties)`;
* 设置Bean对象的名称为name的property的值为value:public void setProperty(java.lang.Object bean, java.lang.Stringname,java.lang.Object value);
* 取得bean对象中名为name的属性的值:`public String getProperty(java.lang.Object bean, java.lang.Stringname)`;
* map数据封装到javaBean示例：
  * 注意：要map中的数据封装到JavaBean中去，需要map中的key与JavaBean里面的私有化的属性要相匹配
```java
@Test
	public void test() throws Exception {
		//创建对象
		Student s2=new Student();
		//1.map的数据拷贝到对象中去
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id","12233");
		map.put("name","老王");
		map.put("sex","男");
		BeanUtils.populate(s2, map);
		System.out.println(s2);
	}
```