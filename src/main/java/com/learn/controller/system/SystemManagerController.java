package com.learn.controller.system;

import com.learn.PoJo.Result;
import com.learn.PoJo.system.System;
import com.learn.service.system.SystemManagerService;
import com.learn.utils.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName:SystemManager
 * @Description:
 * @Author:lvchunyang
 * @Date:16:33
 **/
@Controller
@RequestMapping("/systemManager")
public class SystemManagerController {
    @Autowired
    private SystemManagerService systemManagerService;

    @RequestMapping("/goSystemManager")
    public ModelAndView goSystemManager(ModelAndView mv){
        List<System> systemList = systemManagerService.select(null);
        System systemInfo = new System();
        if(systemList.size()>0){
            systemInfo = systemList.get(0);
        }
        mv.addObject("systemInfo",systemInfo);
        mv.setViewName("/systemManager/systemManeger");
        return mv;
    }

    @PostMapping("/saveSystemManager")
    @ResponseBody
    public Result saveSystemManager(System system){
        systemManagerService.deleteByExample(null);
        return systemManagerService.defaultOperate(systemManagerService.insert(system), CommonConstant.ADD_CH);
    }
}
