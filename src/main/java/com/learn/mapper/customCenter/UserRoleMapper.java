package com.learn.mapper.customCenter;

import com.learn.PoJo.customCenter.UserRole;
import com.learn.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @ClassName:UserRoleMapper
 * @Description:
 * @Author:lvchunyang
 * @Date:11:21
 **/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole,String> {
    @Select("SELECT a.user_role_id userRoleId, " +
            "a.user_id userId, " +
            "a.role_id roleId " +
            "FROM user_role a "+
            "where 1=1 and userId={userRole.userId}"
    )
    Set<String> getUserRoles(UserRole userRole);
}
