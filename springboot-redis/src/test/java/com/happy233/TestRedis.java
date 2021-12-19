package com.happy233;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zy136
 * @date 2021/12/19 12:51
 */

@SpringBootTest
public class TestRedis {

    //@Autowired
    //private StringRedisTemplate redisTemplate;//在 RedisAutoConfiguration 中提供了String序列化Template
    //也可以自定义RedisTemplate 因为在 RedisAutoConfiguration 中它加了@ConditionalOnMisingBean注解 所以我们可以在启动类自定义一个 并设置序列号机制为 StringRedisSerializer

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate() {
        //绑定key
        BoundValueOperations boundKey = redisTemplate.boundValueOps("test:number");
        Object value = boundKey.get();
        System.out.println(value);
    }

    @Test
    public void testDecode() {

    }
}
