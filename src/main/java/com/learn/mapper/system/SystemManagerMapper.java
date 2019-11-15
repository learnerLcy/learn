package com.learn.mapper.system;

import com.learn.PoJo.system.System;
import com.learn.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName:SystemManagerMapper
 * @Description:
 * @Author:lvchunyang
 * @Date:10:20
 **/
@Mapper
public interface SystemManagerMapper extends BaseMapper<System,String> {
}
