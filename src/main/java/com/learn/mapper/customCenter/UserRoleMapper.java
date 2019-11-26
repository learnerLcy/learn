package com.learn.mapper.customCenter;

import com.learn.PoJo.customCenter.UserRole;
import com.learn.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName:UserRoleMapper
 * @Description:
 * @Author:lvchunyang
 * @Date:11:21
 **/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole,String> {
}
