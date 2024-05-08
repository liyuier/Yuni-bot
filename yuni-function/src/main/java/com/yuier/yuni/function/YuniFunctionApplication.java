package com.yuier.yuni.function;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.yuier.yuni.function.mapper")
public class YuniFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuniFunctionApplication.class, args);
    }

}
