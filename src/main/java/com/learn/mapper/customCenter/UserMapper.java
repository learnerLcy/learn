package com.learn.mapper.customCenter;

import com.learn.PoJo.customCenter.User;
import com.learn.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName:UserMapper
 * @Description:
 * @Author:lvchunyang
 * @Date:11:20
 **/
@Mapper
public interface UserMapper extends BaseMapper<User,String> {
}
