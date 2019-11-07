package com.learn.PoJo;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName:PageData
 * @Description:
 * @Author:lvchunyang
 * @Date:13:37
 **/
@Data
public class PageData {
    private int page;
    private int limit;
}
