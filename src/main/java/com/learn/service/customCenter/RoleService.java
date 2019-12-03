package com.learn.service.customCenter;

import com.learn.PoJo.customCenter.Role;
import com.learn.service.BaseService;

import java.util.List;

/**
 * @ClassName:RoleService
 * @Description:
 * @Author:lvchunyang
 * @Date:11:05
 **/
public interface RoleService extends BaseService<Role,String> {
    List<Role> select_Artificial(Role role);
}
