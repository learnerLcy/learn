package com.learn.mapper.system;

import com.learn.PoJo.system.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface LogMapper{
    void insert(SysLog sysLog);
}