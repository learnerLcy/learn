package com.learn;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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



    @RequestMapping("/main")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("root", request.getContextPath());
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("root", request.getContextPath());
        return "/login/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("root", request.getContextPath());
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        // 3.执行登录方法
        try {
            subject.login(token);
            return "redirect:/main";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            request.setAttribute("msg", "用户名不存在！");
        } catch (IncorrectCredentialsException e) {
            request.setAttribute("msg", "密码错误！");
        }

        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "redirect:/main";
    }

    @RequestMapping("/error/unAuth")
    public String unAuth() {
        return "/error/unAuth";
    }
}
