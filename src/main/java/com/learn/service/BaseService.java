package com.learn.service;

import com.learn.PoJo.Result;
import tk.mybatis.mapper.entity.Condition;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:BaseService
 * @Description: service公共类，公共方法
 * @Author:lvchunyang
 * @Date:17:24
 **/
public interface BaseService<T,E extends Serializable> {
    //通用增删改查
    List<T> select(T t);
    T selectByPrimaryKey(E id);

    int deleteByPrimaryKey(E id);
    int deleteByExample(T t);
    int batchDeleteByPrimaryKey(E[] ids);

    int insert(T t);
    int insertSelective(T t);

    int updateByPrimaryKeySelective(T t);
    int updateByPrimaryKey(T t);

    //通用分页查询返回结果集
    public Result show(T t, int page, int limit);
    public Result show(T t, int page, int limit, Condition condition);
    public Result show(List<T> tList, int page, int limit);
    //通用增删改回结果集
    public Result defaultOperate(int result, String msg);
    public Result defaultOperate(boolean boo, String msg,List<T> list);
    public String getList(T t, int page, int limit);
}
