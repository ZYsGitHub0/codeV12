package com.happy233.controller;

import com.happy233.pojo.User;
import com.happy233.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zy136
 * @date 2021/12/25 15:18
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

}
