package com.zestaken.malldemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//mapperscan扫描指定包生成对应的bean，用于自动注入
@MapperScan(value = {"com.zestaken.malldemo.service"})
public class MallDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallDemoApplication.class, args);
    }

}
