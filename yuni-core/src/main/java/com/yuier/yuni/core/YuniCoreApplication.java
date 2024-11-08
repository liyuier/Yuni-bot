package com.yuier.yuni.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.yuier.yuni.core.mapper")
public class YuniCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuniCoreApplication.class, args);
    }

}
