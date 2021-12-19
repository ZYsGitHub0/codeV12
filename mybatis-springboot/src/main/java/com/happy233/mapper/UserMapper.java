package com.happy233.mapper;

import com.happy233.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {

    public List<User> findAll();
}
