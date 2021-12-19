package com.happy233.controller;

import com.happy233.mapper.UserMapper;
import com.happy233.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zy136
 * @date 2021/12/19 12:12
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/all")
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
