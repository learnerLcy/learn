package com.learn.PoJo.customCenter;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @ClassName:role
 * @Description: 角色
 * @Author:lvchunyang
 * @Date:10:41
 **/
@Data
public class Role {
    @Id
    private String roleId;
    private String roleName;
    private String roleDescription;
    private String roleType;

    @Transient
    private String roleTypeName;
}
