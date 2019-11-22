package com.learn.service.system.impl;

import com.learn.PoJo.system.System;
import com.learn.mapper.BaseMapper;
import com.learn.mapper.system.SystemManagerMapper;
import com.learn.service.BaseServiceImpl;
import com.learn.service.system.SystemManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName:SystemManagerServiceImpl
 * @Description:
 * @Author:lvchunyang
 * @Date:14:37
 **/
@Service
public class SystemManagerServiceImpl  extends BaseServiceImpl<System,String> implements SystemManagerService {
    @Autowired
    private SystemManagerMapper systemManagerMapper;

    @Override
    public BaseMapper<System, String> getMappser() {
        return systemManagerMapper;
    }

}
