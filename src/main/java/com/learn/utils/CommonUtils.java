package com.learn.utils;

import java.util.UUID;

/**
 * @ClassName:CommonUtils
 * @Description:
 * @Author:lvchunyang
 * @Date:15:19
 **/
public class CommonUtils {

    /**
     *@Author:lvchunyang
     *@Description: 获取不带“-”的32位uuid
     *@Date:11:04 2019/7/17
     *@Para:[]
     *@Return:java.lang.String
     **/
    public static  String get32Uuid(){
        return UUID.randomUUID().toString().trim().replace("-","");//.toLowerCase()
    }
}
