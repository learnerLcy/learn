package com.learn.PoJo.customCenter;

import lombok.Data;

import javax.persistence.Id;

/**
 * @ClassName:User
 * @Description:
 * @Author:lvchunyang
 * @Date:11:16
 **/
@Data
public class User {
    @Id
    private String userId;
    private String loginname;
    private String password;
    private String salt;
    private String sex;
    private String tel;
    private String mail;
    private String username;
}
