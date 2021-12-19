package com.happy233.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy233.mapper.UserMapper;
import com.happy233.pojo.User;
import com.happy233.service.TestService;
import org.springframework.stereotype.Service;

/**
 * 继承ServiceImpl<Mapper,Pojo> ServiceImpl继承了IService 这样就获得了mybatis plus 封装好的方法
 * 再实现我们自己的接口 可以实现自己扩展
 *
 * @author zy136
 * @date 2021/12/19 19:50
 */
@Service
public class TestServiceImpl extends ServiceImpl<UserMapper, User> implements TestService {
}
