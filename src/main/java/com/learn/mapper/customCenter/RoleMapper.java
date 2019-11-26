package com.learn.mapper.customCenter;

import com.learn.PoJo.customCenter.Role;
import com.learn.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName:RoleMapper
 * @Description:
 * @Author:lvchunyang
 * @Date:11:20
 **/
@Mapper
public interface RoleMapper extends BaseMapper<Role,String> {
}
