package com.itheima.dao;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from tb_user where id = #{id}")
    User findById(Long id);
    @Select("select * from tb_user")
    List<User> findAll();
}
