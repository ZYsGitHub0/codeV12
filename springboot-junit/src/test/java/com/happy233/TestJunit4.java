package com.happy233;

import com.happy233.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试Spring整合Junint4
 * 约定：该测试类必须在Springboot启动类同级目录或子目录下 否则无法找到启动类
 * 如果要解决需要指定@ContextConfiguration
 *
 * @author zy136
 * @date 2021/12/17 18:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = StartApplication.class)
public class TestJunit4 {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testJunit4() {
        System.out.println(userService.getName());
    }

}
