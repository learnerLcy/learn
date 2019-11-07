package com.learn.controller.system;

import com.learn.Constants.Constants;
import com.learn.PoJo.PageData;
import com.learn.PoJo.Result;
import com.learn.PoJo.system.Menu;
import com.learn.service.system.MenuService;
import com.learn.utils.CommonConstant;
import com.learn.utils.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * @ClassName:MenuController
 * @Description: 菜单管理控制类
 * @Author:lvchunyang
 * @Date:10:09
 **/
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
    *@Author:lvchunyang
    *@Description: 菜单列表页面
    *@Date:10:53 2019/7/2
    *@Para:[mv]
    *@Return:org.springframework.web.servlet.ModelAndView
    **/
    @RequestMapping("/showMenuPage")
    public ModelAndView showMenuPage(ModelAndView mv){
        mv.setViewName("/menu/showMenuPage");
        return mv;
    }
    /**
    *@Author:lvchunyang
    *@Description: 获取列表数据信息
    *@Date:16:29 2019/7/2
    *@Para:[pageData]
    *@Return:com.github.pagehelper.PageInfo
    **/
    @RequestMapping("/getMenuList")
    @ResponseBody
    public Result getMenuList(Menu menu,PageData pageData){
        return menuService.show(menuService.select_Artificial(menu),pageData.getPage(), pageData.getLimit());
    }
    /**
    *@Author:lvchunyang
    *@Description: 跳转增加菜单页面
    *@Date:16:05 2019/7/5
    *@Para:[mv]
    *@Return:org.springframework.web.servlet.ModelAndView
    **/
    @RequestMapping("/addMenuPage")
    public ModelAndView addMenuPage(ModelAndView mv){
        mv.setViewName("/menu/addMenuPage");
        return mv;
    }
    @RequestMapping("/editMenuPage")
    public ModelAndView editMenuPage(ModelAndView mv,Menu menu){
        Menu menuInfo = menuService.selectByPrimaryKey(menu.getId());
        mv.addObject("menuInfo",menuInfo);
        mv.setViewName("/menu/editMenuPage");
        return mv;
    }
    /**
    *@Author:lvchunyang
    *@Description: 增加以及编辑操作
    *@Date:13:22 2019/11/6
    *@Para:[menu]
    *@Return:com.learn.PoJo.Result
    **/
    @RequestMapping("/addMenu")
    @ResponseBody
    public Result addMenu(Menu menu){
        menu.setId(CommonUtils.get32Uuid());
        if(StringUtils.isEmpty(menu.getMenuPid())){
            menu.setMenuPid("ROOT");
        }
        return menuService.defaultOperate(menuService.insert(menu), CommonConstant.ADD_CH);
    }
    @RequestMapping("/editMenu")
    @ResponseBody
    public Result editMenu(Menu menu){
        return menuService.defaultOperate(menuService.updateByPrimaryKey(menu), CommonConstant.UPDATE_CH);
    }


    /**
    *@Author:lvchunyang
    *@Description: 获取左侧菜单栏数据
    *@Date:10:48 2019/7/8
    *@Para:[]
    *@Return:com.learn.PoJo.Result
    **/
    @RequestMapping("/getLeftMenuTree")
    @ResponseBody
    public Result getLeftMenuTree(Menu menu) throws Exception{
        return menuService.getLeftMenuTree(menu);
    }
    
    /**
    *@Author:lvchunyang
    *@Description: 删除
    *@Date:13:22 2019/11/6
    *@Para:[menu]
    *@Return:com.learn.PoJo.Result
    **/
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public Result deleteArticle(Menu menu){
        return menuService.defaultOperate(menuService.deleteByPrimaryKey(menu.getId()), CommonConstant.DELETE_CH);
    }
}
