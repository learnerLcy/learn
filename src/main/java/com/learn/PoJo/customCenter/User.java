package com.learn.PoJo.customCenter;

import lombok.Data;

/**
 * @ClassName:User
 * @Description:
 * @Author:lvchunyang
 * @Date:11:16
 **/
@Data
public class User {
    private String userId;
    private String loginName;
    private String passWord;
    private String salt;
    private String sex;
    private String tel;
    private String mail;
}
