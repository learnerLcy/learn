package com.learn.config.shiro;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName:WebMvcConfigurerAdapter
 * @Description:
 * @Author:lvchunyang
 * @Date:11:53
 **/
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
