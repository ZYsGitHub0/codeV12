package com.happy233.config;


import com.happy233.properties.JedisProperties;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;

/**
 * 创建一个jedis给Spring容器
 *
 * @author zy136
 * @date 2021/12/18 17:13
 */
@SpringBootConfiguration(proxyBeanMethods = false)//禁止spring自动代理该类下的@Bean方法
@ConditionalOnClass(Jedis.class)//当有Jedis这个类时才加载
@EnableConfigurationProperties(JedisProperties.class)//开启配置属性加载JedisProperties
public class JedisConfig {

    private JedisProperties jedisProperties;//自定义的一个jedis初始化参数pojo

    public JedisConfig(JedisProperties jedisProperties) {
        this.jedisProperties = jedisProperties;//构造参数注入
    }

    @Bean
    @ConditionalOnMissingBean//如果没有jedis对象才创建
    public Jedis jedis() {
        return new Jedis(jedisProperties.getHost(), jedisProperties.getPort());//将pojo参数填充并创建Jedis对象
    }
}
