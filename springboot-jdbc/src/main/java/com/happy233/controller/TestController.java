package com.happy233.controller;

import com.happy233.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author zy136
 * @date 2021/12/17 20:46
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/all")
    public User getAll() {
        return jdbcTemplate.queryForObject("select * from user where id=?", new BeanPropertyRowMapper<>(User.class), 49);
    }
}
