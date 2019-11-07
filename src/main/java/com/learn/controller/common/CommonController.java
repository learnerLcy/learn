package com.learn.controller.common;

import com.learn.Constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName:CommonController
 * @Description:
 * @Author:lvchunyang
 * @Date:10:30
 **/
@Controller
@RequestMapping("/common")
public class CommonController {
    
    /**
    *@Author:lvchunyang
    *@Description:  type:类型(必传);id:不能选自己作为上级
    *@Date:10:32 2019/11/6
    *@Para:[mv, type, pid]
    *@Return:org.springframework.web.servlet.ModelAndView
    **/
    @RequestMapping("/selectResourcePage")
    public ModelAndView goSelectResourcePage(ModelAndView mv,String type,String id){
        if(Constants.MENU.equals(type)){
            initMenu(mv);
        }else if (Constants.ARTICLECATEGORY.equals(type)){
            initArticleCategory(mv);
        }
        mv.addObject("id",id);
        mv.setViewName("/common/selectResourcePage");
        return mv;
    }

    //选择菜单页面初始化信息
    public ModelAndView initMenu(ModelAndView mv){
        //idKey,pIdKey,name：ztree显示需要的信息;url：ztree数据查询url
        String idKey ="",pIdKey="",name="",url="";
        //回填信息时页面对应的id
        String show_idKey="",show_name="";

        idKey = "id";
        pIdKey = "menuPid";
        name = "menuName";
        url = "/menu/getMenuList";

        show_idKey = "menuPid";
        show_name = "menuPName";

        mv.addObject("idKey",idKey);
        mv.addObject("pIdKey",pIdKey);
        mv.addObject("name",name);
        mv.addObject("url",url);


        mv.addObject("show_idKey",show_idKey);
        mv.addObject("show_name",show_name);
        return mv;
    }

    //选择文章类型初始化信息
    public ModelAndView initArticleCategory(ModelAndView mv){
        //idKey,pIdKey,name：ztree显示需要的信息;url：ztree数据查询url
        String idKey ="",pIdKey="",name="",url="";
        //回填信息时页面对应的id
        String show_idKey="",show_name="";

        idKey = "id";
        pIdKey = "category_pid";
        name = "category";
        url = "/article/category/getCategoryList";

        show_idKey = "articleTypeId";
        show_name = "articleTypeName";

        mv.addObject("idKey",idKey);
        mv.addObject("pIdKey",pIdKey);
        mv.addObject("name",name);
        mv.addObject("url",url);


        mv.addObject("show_idKey",show_idKey);
        mv.addObject("show_name",show_name);
        return mv;
    }
}
