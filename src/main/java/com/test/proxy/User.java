package com.test.proxy;

/**
 * @ClassName:User
 * @Description: 目标类
 * @Author:lvchunyang
 * @Date:11:52
 **/
public class User implements IUser {
    private String name = "";


    @Override
    public void insertUser(String name) {
        this.name = name ;
        System.out.println("User 具体实现");
    }
}
