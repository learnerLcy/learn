package com.learn.service.system.impl;

import com.learn.Constants.Constants;
import com.learn.PoJo.Result;
import com.learn.PoJo.system.Menu;
import com.learn.mapper.BaseMapper;
import com.learn.mapper.system.MenuMapper;
import com.learn.service.BaseServiceImpl;
import com.learn.service.system.MenuService;
import com.learn.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * @ClassName:MenuServiceImpl
 * @Description:
 * @Author:lvchunyang
 * @Date:16:22
 **/
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,String> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public BaseMapper<Menu, String> getMappser() {
        return menuMapper;
    }

    @Override
    public Result getLeftMenuTree(Menu menu) throws Exception{
        //获取所有菜单信息
        Condition condition=new Condition(Menu.class);
        condition.createCriteria().andCondition("menu_flag = '"+Constants.STATE_QY +"' ");
        condition.setOrderByClause("menu_num+0 asc");
        List<Menu> menuList = menuMapper.selectByExample(condition);
        //获取一级菜单
        List<Menu> menuRootTree = TreeUtils.getRootTreeInfo(Menu.class,menuList,"menuPid","ROOT");
        //获取带有层级机构的menu信息
        List<Menu> menuTree = TreeUtils.getAllTreeInfo(Menu.class,menuList,menuRootTree,"id","menuPid");

        return defaultOperate(true,"",menuTree);
    }

    @Override
    public List<Menu> select_Artificial(Menu menu) {
        return menuMapper.select_Artificial(menu);
    }
}
