package com.learn.mapper.customCenter;

import com.learn.PoJo.customCenter.Role;
import com.learn.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName:RoleMapper
 * @Description:
 * @Author:lvchunyang
 * @Date:11:20
 **/
@Mapper
public interface RoleMapper extends BaseMapper<Role,String> {
    @Select("SELECT a.role_id roleId," +
            "a.role_name roleName," +
            "a.role_description roleDescription," +
            "a.role_type roleType," +
            "case role_type when '1' then '启用' else '禁用' end roleTypeName "+
            "FROM role a "+
            "where 1=1 "
    )
    List<Role> select_Artificial(Role role);
}
