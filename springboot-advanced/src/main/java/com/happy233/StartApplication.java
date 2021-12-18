package com.happy233;

import com.happy233.config.BeanConfig;
import com.happy233.pojo.Account;
import com.happy233.register.BeanRegister;
import com.happy233.selector.BeanSelector;
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
//@Import(Account.class)
//2.导入配置类
//@Import(BeanConfig.class)
//3.导入import选择器
//@Import(BeanSelector.class)
//4.导入Bean注册
@Import(BeanRegister.class)
public class StartApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(StartApplication.class, args);
        Map<String, Account> account = applicationContext.getBeansOfType(Account.class);
        for (String beanName : account.keySet()) {
            System.out.println(beanName);
        }
    }
}
