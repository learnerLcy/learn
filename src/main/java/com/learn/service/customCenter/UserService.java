package com.learn.service.customCenter;

import com.learn.PoJo.Result;
import com.learn.PoJo.customCenter.User;
import com.learn.PoJo.customCenter.UserRole;
import com.learn.service.BaseService;

import java.util.List;

/**
 * @ClassName:UserService
 * @Description:
 * @Author:lvchunyang
 * @Date:11:05
 **/
public interface UserService extends BaseService<User,String> {
    Result addUser(User user,List<String> roleIds);
    Result updateUser(User user,List<String> roleIds);
    Result deleteUser(User user, UserRole userRole);
}
