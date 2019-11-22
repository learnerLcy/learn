package com.learn.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.PoJo.Result;
import com.learn.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import tk.mybatis.mapper.entity.Condition;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName:BaseServiceImpl
 * @Description: 通用baseService实现类
 * @Author:lvchunyang
 * @Date:10:11
 **/
@Slf4j
public abstract class BaseServiceImpl<T,E extends Serializable> implements BaseService<T,E> {

    public abstract BaseMapper<T, E> getMappser();

    @Override
    public List<T> select(T t) {
        return getMappser().select(t);
    }

    @Override
    public T selectByPrimaryKey(E id) {
        return (T) getMappser().selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(E id) {
        return getMappser().deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(T t) {
        return getMappser().deleteByExample(t);
    }

    @Override
    public int batchDeleteByPrimaryKey(E[] ids) {
        int result = 1;
        for(E id : ids){
            result = deleteByPrimaryKey(id);
            if(result==0){
                return result;
            }
        }
        return result;
    }

    @Override
    public int insert(T t) {
        return getMappser().insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return getMappser().insertSelective(t);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        return getMappser().updateByPrimaryKeySelective(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return getMappser().updateByPrimaryKey(t);
    }

    @Override
    public Result show(T t, int page, int limit) {
        int code = 0;
        List<T> tList = null;
        Page<T> tPage = PageHelper.startPage(page, limit);
        try {
            tList = getMappser().select(t);
        } catch (Exception e) {
            /*log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());*/
            code = 1;
            e.printStackTrace();
        }
        return new Result(code,tPage.getTotal(), tList);
    }

    @Override
    public Result show(T t, int page, int limit, Condition condition) {
        int code = 0;
        List<T> tList = null;
        Page<T> tPage = PageHelper.startPage(page, limit);
        try {
            tList = getMappser().selectByExample(condition);
        } catch (Exception e) {
            /*log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());*/
            code = 1;
            e.printStackTrace();
        }
        return new Result(code,tPage.getTotal(), tList);
    }

    @Override
    public Result show(List<T> tList, int page, int limit) {
        int code = 0;
        Page<T> tPage = PageHelper.startPage(page, limit);
        return new Result(code,tPage.getTotal(), tList);
    }

    @Override
    public Result defaultOperate(int result, String type) {
        boolean success = false;
        String msg = type;
        if(result>0){
            success = true;
            msg += "成功";
        }else{
            msg += "失败";
        }
        return new Result(success,msg);
    }

    @Override
    public Result defaultOperate(boolean success, String msg , List<T> list) {
        return new Result(success,msg,list);
    }
    @Override
    public String getList(T t, int page, int limit) {
        List<T> tList = null;
        try {
            tList = getMappser().select(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(tList);
    }
}
