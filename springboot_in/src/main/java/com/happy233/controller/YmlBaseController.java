package com.happy233.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yml")
public class YmlBaseController {

    @Value("${person.name}")
    private String name;
    @Value("${person.age}")
    private Integer age;
    @Value("${address[2]}")
    private String address;
    @Value("${msg1}")
    private String msg1;
    @Value("${msg2}")
    private String msg2;
    @Value("${name}")
    private String name3;

    @GetMapping("/getPerson")
    public String getPerson() {
        return "name:" + name + "--age=" + age + "--address:" + address + "--msg1:" + msg1 + "--msg2:" + msg2+"--name参数引用:"+name3;
    }


}
