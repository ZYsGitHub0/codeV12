package com.happy233;

import com.happy233.pojo.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.Map;

/**
 * @author zy136
 * @date 2021/12/18 14:49
 */
@SpringBootApplication
//注册Bean的方式
//1，直接导入Bean的class 使用这种方法导入bean的name是类的全限定类名
@Import(Account.class)
public class StartApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(StartApplication.class, args);
        Map<String, Account> account = applicationContext.getBeansOfType(Account.class);
        for (String beanName : account.keySet()) {
            System.out.println(beanName);//com.happy233.pojo.Account
        }

    }
}
