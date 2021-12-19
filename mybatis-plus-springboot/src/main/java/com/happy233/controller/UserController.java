package com.happy233.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.happy233.pojo.User;
import com.happy233.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zy136
 * @date 2021/12/19 15:02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @RequestMapping("/add")
    public User addUser(User user) {
        userService.addUser(user);
        return user;//mybatis plus的自动主键回填
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "删除成功";
    }
    /*
    TODO: 根据id批量删除userMapper.deleteBatchIds(id集合);
          根据map集合中的条件删除 key为table表中字段 value为字段的值 (当满足所有 key(表列名)下的value=表中字段值 时删除)
    */

    @RequestMapping("/update/{id}/{username}")
    public String updateUser(@PathVariable Integer id, @PathVariable String username) {
        User user = User.builder().id(id).username(username).build();
        userService.updateUser(user);
        return "更新成功";
    }

    @RequestMapping("/page/{num}")
    public List<User> pageUser(@PathVariable Integer num) {
        List<User> userList = userService.pageUser(num);
        return userList;
    }

    @RequestMapping("/query")
    public List<User> queryWrapper() {
        return userService.queryUserByWrapper();
    }

    @RequestMapping("/query2")
    public List<User> queryWrapper2() {
        return userService.queryUserByWrapper2();
    }

    @RequestMapping("/lambda")
    public List<User> lambdaQueryWrapper() {
        return userService.lambdaQuery();
    }


}
