package com.happy233.config;

import com.happy233.pojo.Account;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zy136
 * @date 2021/12/18 14:57
 */

@Configuration(proxyBeanMethods = true)//如果是true则为代理所有方法 Full模式 单例模式 如果是False则不代理 Lite模式 多例模式
@ConditionalOnClass(Account.class)//Account类存在才会加载这个配置类 还有很多OnXXX 具体自己查看..
public class BeanConfig {

    @Bean//默认beanName为方法名
    public Account account() {
        return Account.builder().username("zhangsan").age(18).id(1).build();
    }
}
