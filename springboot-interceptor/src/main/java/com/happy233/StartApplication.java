package com.happy233;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * TODO
 *
 * @author zy136
 * @date 2021/12/17 17:37
 */
@SpringBootApplication
@ServletComponentScan("com.happy233.web")//可以扫描指定servlet filter listener的包配置它们
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
