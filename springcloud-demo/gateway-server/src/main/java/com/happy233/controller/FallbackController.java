package com.happy233.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zy136
 * @date 2021/12/25 21:20
 */
@RestController
public class FallbackController {

    @RequestMapping("/fallbackTest")
    public Map<String, String> fallbackTest() {
        Map<String, String> response = new HashMap<>();
        response.put("code", "502");
        response.put("msg", "请求超时...");
        return response;
    }

}
