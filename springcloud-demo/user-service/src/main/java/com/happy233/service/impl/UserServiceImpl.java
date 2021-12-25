package com.happy233.service.impl;

import com.happy233.pojo.User;
import com.happy233.mapper.UserMapper;
import com.happy233.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zy136
 * @date 2021/12/25 15:17
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }
}
