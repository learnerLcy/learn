package com.learn;

import com.learn.annotation.Log;
import com.learn.config.websocket.MyMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName:TestController
 * @Description:
 * @Author:lvchunyang
 * @Date:10:10
 **/
@Controller
public class TestController {
    @Autowired
    MyMessageHandler myMessageHandler;

    @ResponseBody
    @RequestMapping("/get1")
    public String  send(String name){
        myMessageHandler.sendMessageToUser("zhaoshouyun", "服务端发送的内容:"+name);
        return "success";
    }

    @Log("执行方法一")
    @GetMapping("/one")
    @ResponseBody
    public void methodOne(String name) { }

    @Log("执行方法二")
    @GetMapping("/two")
    @ResponseBody
    public void methodTwo() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Log("执行方法三")
    @GetMapping("/three")
    @ResponseBody
    public void methodThree(String name, String age) { }
}
