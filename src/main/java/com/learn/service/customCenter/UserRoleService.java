package com.learn.service.customCenter;

import com.learn.PoJo.customCenter.UserRole;
import com.learn.service.BaseService;

import java.util.Set;

/**
 * @ClassName:UserRoleService
 * @Description:
 * @Author:lvchunyang
 * @Date:11:05
 **/
public interface UserRoleService extends BaseService<UserRole,String> {
    Set<String> getUserRoles(UserRole userRole);
}
