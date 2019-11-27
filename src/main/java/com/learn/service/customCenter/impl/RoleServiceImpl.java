package com.learn.service.customCenter.impl;

import com.learn.PoJo.customCenter.Role;
import com.learn.mapper.BaseMapper;
import com.learn.mapper.customCenter.RoleMapper;
import com.learn.service.BaseServiceImpl;
import com.learn.service.customCenter.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:RoleServiceImpl
 * @Description:
 * @Author:lvchunyang
 * @Date:11:22
 **/
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,String> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseMapper<Role, String> getMappser() {
        return roleMapper;
    }

    @Override
    public List<Role> select_Artificial(Role role) {
        return roleMapper.select_Artificial(role);
    }
}
