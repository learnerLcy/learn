package com.learn.mapper.system;

import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:BaseMapper
 * @Description:
 * @Author:lvchunyang
 * @Date:11:11
 **/
public interface BaseMapper<T, E extends Serializable> extends Mapper {

}
