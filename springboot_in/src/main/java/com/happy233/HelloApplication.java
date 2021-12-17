package com.happy233;

import com.happy233.pojo.Man;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication//标识当前类是springboot启动类/引导类
@EnableConfigurationProperties(Man.class)//高级配置 启用配置属性将Man加入spring容器 或者可以给Man加@Componet注解
public class HelloApplication {
    public static void main(String[] args) {
        //为什么需要启动类.class字节码文件？
        //Because:利用字节码获取包名 那么springBoot的默认作用域则为该包 会默认为该包下的所有被注解或配置的类IOC创建bean对象
        SpringApplication.run(HelloApplication.class, args);
    }
}
