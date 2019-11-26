package com.learn.controller.customCenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName:UsersController
 * @Description: 网站用户统计
 * @Author:lvchunyang
 * @Date:11:36
 **/
@Controller
@RequestMapping("/users")
public class UsersController {

    public ModelAndView showUsersPage(ModelAndView mv){
        mv.setViewName("/customCenter/showUsersPage");
        return mv;
    }
}
