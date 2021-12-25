package com.happy233.controller;

import com.happy233.feign.UserClient;
import com.happy233.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zy136
 * @date 2021/12/25 17:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserClient userClient;

    @RequestMapping("/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallBack")//对此方法开启降级服务 并指定回退的逻辑方法
    public User findById(@PathVariable Long id) {
        if (id == 1) {
            throw new RuntimeException("模拟异常");
        }
        return userClient.findById(id);
    }

    //降级逻辑方法
    public User findByIdFallBack(Long id) {//方法名可以变 返回值和参数列表不可变
        User user = new User();
        user.setId(id);
        user.setNote("请稍后再试");
        return user;
    }
}
