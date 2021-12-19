package com.happy233.service;

import com.happy233.exception.mapper.UserNoFoundException;
import com.happy233.mapper.UserMapper;
import com.happy233.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author zy136
 * @date 2021/12/19 15:03
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(Integer id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new UserNoFoundException("无对应id的用户");
        }
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(User user) {
        user.setBirthday(new Date());
        int insert = userMapper.insert(user);
        if (insert != 1) {
            throw new UserNoFoundException("插入失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer id) {
        int count = userMapper.deleteById(id);
        if (count != 1) {
            throw new UserNoFoundException("删除失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) {
        int count = userMapper.updateById(user);//根据id更新
        if (count != 1) {
            throw new UserNoFoundException("更新失败");
        }
    }
}
