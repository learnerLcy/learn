package com.learn.PoJo.system;

import lombok.Data;

import java.sql.Blob;

/**
 * @ClassName:System
 * @Description:
 * @Author:lvchunyang
 * @Date:17:29
 **/
@Data
public class System {
    private String sitename;
    private String domain;
    private String cache;
    private String filesize;
    private String filetype;
    private String title;
    private String copyright;
    private Blob iamge;
}
