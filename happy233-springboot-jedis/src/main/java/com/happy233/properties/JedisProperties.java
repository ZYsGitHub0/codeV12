package com.happy233.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jedis初始化参数Pojo
 * @author zy136
 * @date 2021/12/18 17:13
 */
@Data
@ConfigurationProperties(prefix = "redis.jedis")
public class JedisProperties {
    private String host = "localhost";
    private int port = 6379;
}
