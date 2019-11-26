package com.learn.PoJo.customCenter;

import lombok.Data;

/**
 * @ClassName:role
 * @Description: 角色
 * @Author:lvchunyang
 * @Date:10:41
 **/
@Data
public class Role {
    private String roleId;
    private String roleName;
    private String roleDescription;
    private String roleType;
}
