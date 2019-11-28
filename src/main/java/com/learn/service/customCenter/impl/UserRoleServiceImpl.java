package com.learn.service.customCenter.impl;

import com.learn.PoJo.customCenter.UserRole;
import com.learn.mapper.BaseMapper;
import com.learn.mapper.customCenter.UserRoleMapper;
import com.learn.service.BaseServiceImpl;
import com.learn.service.customCenter.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @ClassName:UserRoleServiceImpl
 * @Description:
 * @Author:lvchunyang
 * @Date:11:24
 **/
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole,String> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public BaseMapper<UserRole, String> getMappser() {
        return userRoleMapper;
    }

    @Override
    public Set<String> getUserRoles(UserRole userRole) {
        return userRoleMapper.getUserRoles(userRole);
    }
}
