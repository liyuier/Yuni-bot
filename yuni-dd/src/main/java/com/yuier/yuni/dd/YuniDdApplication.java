package com.yuier.yuni.dd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.yuier.yuni.dd.mapper")
public class YuniDdApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuniDdApplication.class, args);
    }

}
