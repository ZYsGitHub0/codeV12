package com.happy233.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${USER.NAME1}")
    private String name1;
    @Value("${USER.NAME2}")
    private String name2;

    @GetMapping("/say")
    public String say() {

        return "Hello Spring boot!";
    }

    @GetMapping("/name")
    public String getName() {
        return "name1=" + name1 + "----" + "name2=" + name2;
    }
}
