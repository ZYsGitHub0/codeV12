package com.happy233.controller;

import com.happy233.pojo.User;
import com.happy233.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zy136
 * @date 2021/12/19 19:53
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/all")
    private List<User> findAll() {
        return testService.list();
    }
}
