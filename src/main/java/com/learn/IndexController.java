package com.learn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName:indexController
 * @Description:
 * @Author:lvchunyang
 * @Date:17:08
 **/
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/showIndexPage")
    public String showIndexPage(){
        return "index";
    }

    @RequestMapping("/showHomePage")
    public ModelAndView showHomePage(ModelAndView mv){
        mv.setViewName("/home");
        return mv;
    }
}
