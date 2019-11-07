package com.learn.mapper.system;

import com.learn.Constants.Constants;
import com.learn.PoJo.system.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu,String> {

    @Select("SELECT id," +
            "menu_name menuName," +
            "menu_pid menuPid," +
            "menu_description menuDescription," +
            "menu_url menuUrl," +
            "menu_icon menuIcon," +
            "menu_flag menuFlag," +
            "menu_num menuNum," +
            "case menu_flag when '1' then '启用' else '禁用' end  menuFlagName FROM menu order by menu_num asc")
    List<Menu> select_Artificial(Menu menu);
}
