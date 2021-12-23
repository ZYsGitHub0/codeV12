package com.itheima.service;

import com.itheima.dao.UserMapper;
import com.itheima.pojo.User;
import com.itheima.utils.MybatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

public class UserService {
    private UserMapper userMapper;

    public UserService(){
        SqlSession sqlSession = MybatisUtils.getSqlSession(true);
        userMapper = sqlSession.getMapper(UserMapper.class);
    }
    public User findById(Long id){
        return userMapper.findById(id);
    }

    public List<User> findAll(){
        return userMapper.findAll();
    }

    public static void main(String[] args) throws SQLException {
        List<User> list = new UserService().findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
