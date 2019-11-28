package com.learn.controller.customCenter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName:PersonalController
 * @Description: 个人中心
 * @Author:lvchunyang
 * @Date:10:24
 **/
@Controller
@RequestMapping("/personal")
public class PersonalController {

    @GetMapping("/showPersonalPage")
    public ModelAndView showPersonalPage(ModelAndView mv){
        mv.setViewName("/customCenter/personal/personal");
        return mv;
    }
}
