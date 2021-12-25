package com.happy233.controller;

import com.happy233.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zy136
 * @date 2021/12/25 15:36
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{id}")
    public User findById(@PathVariable Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        System.out.println(instances.get(0).getUri());//获取Uri
        return restTemplate.getForObject("http://user-service/user/" + id, User.class);//使用这种方法 填写服务名 回自动获取uri填充
    }
}
