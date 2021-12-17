package com.happy233.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zy136
 * @date 2021/12/17 17:42
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/interceptor")
    public String testInterceptor() {
        return "测试拦截器";
    }
}
