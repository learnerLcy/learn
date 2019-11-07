package com.learn.service.system;

import com.learn.PoJo.Result;
import com.learn.PoJo.system.Menu;
import com.learn.service.BaseService;

import java.util.List;

/**
 * @ClassName:MenuService
 * @Description:
 * @Author:lvchunyang
 * @Date:16:21
 **/
public interface MenuService extends BaseService<Menu,String> {
    //左侧导航栏数据信息
    Result getLeftMenuTree(Menu menu) throws Exception;
    //通用mapepr不提供联表查询，自己联表查询
    List<Menu> select_Artificial(Menu menu);
}
