package com.happy233;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zy136
 * @date 2021/12/19 12:11
 */

@SpringBootApplication
@MapperScan(basePackages = "com.happy233.mapper")
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
