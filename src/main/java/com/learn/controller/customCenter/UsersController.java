package com.learn.controller.customCenter;

import com.learn.Constants.Constants;
import com.learn.PoJo.Result;
import com.learn.PoJo.customCenter.User;
import com.learn.PoJo.customCenter.UserRole;
import com.learn.PoJo.system.Menu;
import com.learn.service.customCenter.UserRoleService;
import com.learn.service.customCenter.UserService;
import com.learn.utils.CommonConstant;
import com.learn.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * @ClassName:UsersController
 * @Description: 网站用户统计
 * @Author:lvchunyang
 * @Date:11:36
 **/
@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/showUsersPage")
    public ModelAndView showUsersPage(ModelAndView mv){
        mv.setViewName("/customCenter/showUsersPage");
        return mv;
    }

    @PostMapping("addUser")
    @ResponseBody
    public Result addUser(User user, @RequestParam(value="roleIds",required=false)List<String> roleIds){
        return userService.addUser(user,roleIds);
    }

    @PostMapping("updateUser")
    @ResponseBody
    public Result updateUser(User user,@RequestParam(value="roleIds",required=false)List<String> roleIds){
        return userService.updateUser(user,roleIds);
    }
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Result deleteUser(User user,UserRole userRole){
        return userService.deleteUser(user,userRole);
    }
}
