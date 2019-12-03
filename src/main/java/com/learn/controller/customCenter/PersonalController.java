package com.learn.controller.customCenter;

import com.learn.Constants.Constants;
import com.learn.PoJo.customCenter.Role;
import com.learn.PoJo.customCenter.User;
import com.learn.PoJo.customCenter.UserRole;
import com.learn.PoJo.mongo.Files;
import com.learn.service.customCenter.RoleService;
import com.learn.service.customCenter.UserRoleService;
import com.learn.service.customCenter.UserService;
import com.learn.service.mongo.FilesMongoDB;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @ClassName:PersonalController
 * @Description: 个人中心
 * @Author:lvchunyang
 * @Date:10:24
 **/
@Controller
@RequestMapping("/personal")
public class PersonalController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private FilesMongoDB filesMongoDB;

    //个人信息页面
    @GetMapping("/showPersonalPage")
    public ModelAndView showPersonalPage(ModelAndView mv){
        //获取当前登陆人信息
        User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
        currentUser = userService.selectByPrimaryKey(currentUser.getUserId());
        //获取角色数据
        Role role = new Role();
        role.setRoleType(Constants.STATE_QY);
        List<Role> roleList = roleService.select_Artificial(role);
        //获取人员角色信息
        UserRole userRole = new UserRole();
        userRole.setUserId(currentUser.getUserId());
        List<UserRole> userRoleList = userRoleService.select(userRole);

        mv.addObject("currentUser",currentUser);
        mv.addObject("roleList",roleList);
        mv.addObject("userRoleList",userRoleList);
        mv.addObject("userId",currentUser.getUserId());
        //目前是每个人单角色
        if(userRoleList.size()>0){
            mv.addObject("roleId",userRoleList.get(0).getRoleId());
        }
        mv.setViewName("/customCenter/personal/personal");
        return mv;
    }
    //获取个人头像
    @PostMapping("/getPersonalmg")
    @ResponseBody
    public Files getPersonalmg(){
        User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
        Files imgFile = filesMongoDB.queryById(currentUser.getUserId());
        return imgFile;
    }
}
