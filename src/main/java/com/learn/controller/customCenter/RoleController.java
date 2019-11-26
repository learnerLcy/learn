package com.learn.controller.customCenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    public ModelAndView showRolePage(ModelAndView mv){
        mv.setViewName("/customCenter/showRolePage");
        return mv;
    }
}
