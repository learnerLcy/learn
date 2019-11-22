package com.learn.PoJo.mongo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:Files
 * @Description:
 * @Author:lvchunyang
 * @Date:14:55
 **/
@Data
public class Files{
    private String id;          //文件来源id
    private String type;        //文件来源类型

    private String fileName;    //文件名称
    private String fileType;    //文件类型
    private Long fileSize;    //文件大小
    private String filecontent; //文件内容
    private byte[] fileByte;    //文件二进制码
}
