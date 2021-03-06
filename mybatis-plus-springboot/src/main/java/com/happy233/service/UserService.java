package com.happy233.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.happy233.exception.mapper.UserNoFoundException;
import com.happy233.mapper.UserMapper;
import com.happy233.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    public List<User> pageUser(Integer num) {
        Page<User> userPage = new Page<>();
        userPage.setCurrent(num);//设置当前页数
        userPage.setSize(2);//每页显示条数
        userMapper.selectPage(userPage, null);
        return userPage.getRecords();
    }

    public List<User> queryUserByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("username", "张")//链式编程添加多个条件
                .eq("sex", "男")
                .orderByAsc("id");//根据id升序
        return userMapper.selectList(queryWrapper);

    }

    public List<User> queryUserByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("username", "王")
                .or()
                .like("username", "张")
                .between("id", 41, 48);
        return userMapper.selectList(queryWrapper);

    }

    public List<User> lambdaQuery() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        LambdaQueryWrapper<User> objectLambdaQueryWrapper = Wrappers.lambdaQuery();
        queryWrapper.le(User::getId, 45);
        return userMapper.selectList(queryWrapper);
    }
}
