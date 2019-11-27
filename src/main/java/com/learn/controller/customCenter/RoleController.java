package com.learn.controller.customCenter;

import com.learn.PoJo.PageData;
import com.learn.PoJo.Result;
import com.learn.PoJo.customCenter.Role;
import com.learn.service.customCenter.RoleService;
import com.learn.utils.CommonConstant;
import com.learn.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName:RoleController
 * @Description: 角色管理
 * @Author:lvchunyang
 * @Date:11:35
 **/
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/showRolePage")
    public ModelAndView showRolePage(ModelAndView mv){
        mv.setViewName("/customCenter/role/showRolePage");
        return mv;
    }
    @RequestMapping("/getRoleList")
    @ResponseBody
    public Result getCategoryList(Role role, PageData pageData){
        return roleService.show(roleService.select_Artificial(role),pageData.getPage(), pageData.getLimit());
    }

    @GetMapping("/addRolePage")
    public ModelAndView addRolePage(ModelAndView mv){
        mv.setViewName("/customCenter/role/addRolePage");
        return mv;
    }
    @GetMapping("/editRolePage")
    public ModelAndView editRolePage(ModelAndView mv,Role role){
        Role roleInfo = roleService.selectByPrimaryKey(role.getRoleId());
        mv.addObject("roleInfo",roleInfo);
        mv.setViewName("/customCenter/role/editRolePage");
        return mv;
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public Result addCategory(Role role){
        role.setRoleId(CommonUtils.get32Uuid());
        return roleService.defaultOperate(roleService.insert(role), CommonConstant.ADD_CH);
    }
    @RequestMapping("/editRole")
    @ResponseBody
    public Result editCategory(Role role){
        return roleService.defaultOperate(roleService.updateByPrimaryKey(role), CommonConstant.UPDATE_CH);
    }
    @RequestMapping("/deleteRole")
    @ResponseBody
    public Result deleteRole(Role role){
        return roleService.defaultOperate(roleService.deleteByPrimaryKey(role.getRoleId()), CommonConstant.DELETE_CH);
    }
}
