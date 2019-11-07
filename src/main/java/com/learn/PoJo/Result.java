package com.learn.PoJo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


/**
 * @ClassName:Result
 * @Description: 返回类
 * @Author:lvchunyang
 * @Date:11:35
 **/
@Data
@Accessors(chain = true)
public class Result {
    /**状态信息*/
    private String msg="true";

    /*列表信息返回结果start*/
    //成功码，默认成功
    private  int code = 0;
    /**数据总数*/
    private long count;
    /**页码*/
    private long pageNum;
    /**结果集**/
    private  Object data;
    /*列表信息返回结果end*/

    /*普通增删改查返回结果start*/
    private boolean success = true;
    /*普通增删改查返回结果end*/


    public Result(){

    }
    //table列表数据，返回code以及data等数据信息
    public Result(int code,long count, Object data) {
        this.code = code;
        this.count = count;
        this.data = data;
    }
    public Result(int code,long count,long pageNum, Object data) {
        this.code = code;
        this.count = count;
        this.pageNum=pageNum;
        this.data = data;
    }
    //增删改查，返回boo、以及提示信息
    public Result(boolean success,String msg) {
        this.success = success;
        this.msg = msg;
    }
    public Result(boolean success,String msg,Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }
}
