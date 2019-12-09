package com.learn.Constants;

/**
 * @ClassName:Constants
 * @Description: 常用常量
 * @Author:lvchunyang
 * @Date:17:15
 **/
public class Constants {

    public static String ROOT = "ROOT";
    /*登陆提示信息*/
    public static String unKnownLoginname = "用户不存在";
    public static String incorrectCredentials = "密码错误";
    //状态码：启用、禁用
    public static String STATE_QY = "1";
    public static String STATE_JY = "0";


    //选择弹窗类型
    public static String MENU = "menu";                                                   //选择上级菜单页面
    public static String ARTICLECATEGORY = "articleCategory";                             //文章管理--选择文章类型页面
    public static String CATEGORY = "category";                                           //文章类型管理--选择上级类型页面



    //mongoDb
    /*file type值*/
    public static String IMG_SY = "img_sy";                                                      //首页背景图
    public static String IMG_PERSONAL = "img_personal";                                     //个人头像
}
