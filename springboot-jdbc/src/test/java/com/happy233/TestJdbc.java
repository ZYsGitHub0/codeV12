package com.happy233;

import com.happy233.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * TODO
 *
 * @author zy136
 * @date 2021/12/17 20:05
 */
@SpringBootTest
public class TestJdbc {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbc() {
        User user = jdbcTemplate.queryForObject("select * from user where id=?",new BeanPropertyRowMapper<>(User.class), 49);
        System.out.println(user);
    }
}
