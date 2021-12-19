package com.happy233.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.happy233.pojo.User;
import org.apache.ibatis.annotations.Mapper;

//@Mapper//告诉springboot这是一个Mapper
public interface UserMapper extends BaseMapper<User> {//继承 BaseMapper 指定泛型 告诉MybatisPlus当前接口操作的是哪个Pojo
}
