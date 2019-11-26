package com.learn.service.customCenter.impl;

import com.learn.PoJo.customCenter.User;
import com.learn.mapper.BaseMapper;
import com.learn.mapper.customCenter.UserMapper;
import com.learn.service.BaseServiceImpl;
import com.learn.service.customCenter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:UserServiceImpl
 * @Description:
 * @Author:lvchunyang
 * @Date:11:17
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<User,String> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseMapper<User, String> getMappser() {
        return userMapper;
    }
}
