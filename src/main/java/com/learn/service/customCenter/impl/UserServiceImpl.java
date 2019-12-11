package com.learn.service.customCenter.impl;

import com.learn.Constants.Constants;
import com.learn.PoJo.Result;
import com.learn.PoJo.customCenter.User;
import com.learn.PoJo.customCenter.UserRole;
import com.learn.mapper.BaseMapper;
import com.learn.mapper.customCenter.UserMapper;
import com.learn.mapper.customCenter.UserRoleMapper;
import com.learn.service.BaseServiceImpl;
import com.learn.service.customCenter.UserService;
import com.learn.utils.CommonConstant;
import com.learn.utils.CommonUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

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
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public BaseMapper<User, String> getMappser() {
        return userMapper;
    }


    @Override
    public Result addUser(User user, List<String> roleIds) {
        user.setUserId(CommonUtils.get32Uuid());

        UserRole userRole = null;
        for(String roleId : roleIds){
            userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(roleId);
            userRole.setUserRoleId(CommonUtils.get32Uuid());
            userRoleMapper.insert(userRole);
        }
        return defaultOperate(insertSelective(user), CommonConstant.ADD_CH);
    }

    @Override
    public Result updateUser(User user, List<String> roleIds) {
        Condition condition=new Condition(UserRole.class);
        condition.createCriteria().andCondition("user_id = '"+user.getUserId() +"' ");
        userRoleMapper.deleteByExample(condition);

        UserRole userRole = null;
        for(String roleId : roleIds){
            userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(roleId);
            userRole.setUserRoleId(CommonUtils.get32Uuid());
            userRoleMapper.insert(userRole);
        }
        Result result = defaultOperate(updateByPrimaryKeySelective(user), CommonConstant.UPDATE_CH);
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_LOGINUSER,selectByPrimaryKey(user.getUserId()));
        return result;
    }

    @Override
    public Result deleteUser(User user, UserRole userRole) {
        Condition condition=new Condition(UserRole.class);
        condition.createCriteria().andCondition("user_id = '"+user.getUserId() +"' ");
        userRoleMapper.deleteByExample(condition);

        return defaultOperate(deleteByPrimaryKey(user.getUserId()), CommonConstant.DELETE_CH);
    }
}
