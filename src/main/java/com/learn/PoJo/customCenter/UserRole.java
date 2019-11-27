package com.learn.PoJo.customCenter;

import lombok.Data;

import javax.persistence.Id;

/**
 * @ClassName:UserRole
 * @Description: 人员—角色
 * @Author:lvchunyang
 * @Date:10:46
 **/
@Data
public class UserRole {
    @Id
    private String userRoleId;
    private String userId;
    private String roleId;
}
