package com.happy233;

import com.happy233.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SpringBoot整合Junit5单元测试
 *
 * @author zy136
 * @date 2021/12/17 19:23
 */
@SpringBootTest
@DisplayName("测试Junit5")//设置展示的测试名
public class TestJunit5 {

    @Autowired
    private UserServiceImpl userService;

    @Test//此处使用junit5的@Test
    public void testJunit5() {
        System.out.println(userService.getName());
    }

    @BeforeEach//每个测试方法运行前都会执行一次
    public void beforeEach() {
        System.out.println("beforeEach...");
    }

    @BeforeAll//类加载时执行
    public static void beforeAll() {
        System.out.println("beforeAll...");
    }

    //@After.......

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach...");
    }

    @DisplayName("嘿嘿测试")
    @Test
    public void heihei() {
        System.out.println("heihei...");
    }

    @Disabled
    @Test
    @DisplayName("不测试")
    public void dontTest() {
        System.out.println("heihei...");
    }

    @Test
    @DisplayName("循环测试")
    public void roundTest() {
        System.out.println("循环测试");
    }

    @Test
    @DisplayName("测试add 假设 断言")
    public void testAdd() {
        int a = 4;
        int b = 9;
        int c = a + b;
//        assert c == 12;//断言
//        Assumptions.assumeTrue(c == 13, "未通过测试");
        assertTrue(c == 12, "断言错误");//import static org.junit.jupiter.api.Assertions.*;所以不需要写Assertions
    }

//    @RepeatedTest()循环测试
//    @Timeout()超时测试失败


}
