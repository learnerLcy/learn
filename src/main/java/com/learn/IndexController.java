package com.learn;

import com.learn.Constants.Constants;
import com.learn.PoJo.Result;
import com.learn.PoJo.customCenter.User;
import com.learn.service.customCenter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@Slf4j
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private UserService userService;


    @GetMapping("/showIndexPage")
    public ModelAndView showIndexPage(ModelAndView mv){
        mv.addObject(Constants.SESSION_LOGINUSER,(User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_LOGINUSER));
        mv.setViewName("/index");
        return mv;
    }

    @RequestMapping("/showHomePage")
    public ModelAndView showHomePage(ModelAndView mv){
        mv.setViewName("/home");
        return mv;
    }

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("root", request.getContextPath());
        return "/login/login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result login(User user, HttpServletRequest request,HttpServletResponse response) {
        String loginname = user.getLoginname();
        String password = user.getPassword();

        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(loginname, password);
        // 3.执行登录方法
        boolean flag = true;
        String msg = "";
        try {
            subject.login(token);
            //return "redirect:/home";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            flag = false;
            msg = Constants.unKnownLoginname;
        } catch (IncorrectCredentialsException e) {
            flag = false;
            msg = Constants.incorrectCredentials;
        }
        return new Result(flag,msg);
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "/login/login";
    }

    @RequestMapping("/error/unAuth")
    public String unAuth() {
        return "/error/unAuth";
    }
}
