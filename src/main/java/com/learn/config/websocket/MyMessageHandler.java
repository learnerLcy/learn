package com.learn.config.websocket;

import com.github.pagehelper.util.StringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName:MyMessageHandler
 * @Description:
 * @Author:lvchunyang
 * @Date:9:50
 **/
@Configuration
public class MyMessageHandler implements WebSocketHandler{
    //用户key
    public static final String USER_KEY = "current_user";

    /**
     * userMap:存储用户连接webscoket信息
     * @since JDK 1.7
     */
    private final static Map<String, WebSocketSession> userMap;
    static {
        userMap = new ConcurrentHashMap<String,WebSocketSession>(30);
    }


    /**
     * 关闭websocket时调用该方法
     * @see org.springframework.web.socket.WebSocketHandler#afterConnectionClosed(org.springframework.web.socket.WebSocketSession, org.springframework.web.socket.CloseStatus)
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = this.getUserId(session);
        if(StringUtil.isNotEmpty(userId)){
            userMap.remove(userId);
            System.err.println("该" + userId +"用户已成功关闭");
        }else{
            System.err.println("关闭时，获取用户id为空");
        }

    }

    /**
     * 建立websocket连接时调用该方法
     * @see org.springframework.web.socket.WebSocketHandler#afterConnectionEstablished(org.springframework.web.socket.WebSocketSession)
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = this.getUserId(session);
        if(StringUtil.isNotEmpty(userId)){
            userMap.put(userId, session);
            session.sendMessage(new TextMessage("建立WebSocket连接成功！"));
        }

    }

    /**
     * 客户端调用websocket.send时候，会调用该方法,进行数据通信
     * @see org.springframework.web.socket.WebSocketHandler#handleMessage(org.springframework.web.socket.WebSocketSession, org.springframework.web.socket.WebSocketMessage)
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String msg = message.toString();
        String userId = this.getUserId(session);
        System.err.println("该"+userId+"用户发送的消息是："+msg);
        message = new TextMessage("服务端已经接收到消息，msg="+msg);
        session.sendMessage(message);

    }

    /**
     * 传输过程出现异常时，调用该方法
     * @see org.springframework.web.socket.WebSocketHandler#handleTransportError(org.springframework.web.socket.WebSocketSession, java.lang.Throwable)
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
        WebSocketMessage<String> message = new TextMessage("异常信息："+e.getMessage());
        session.sendMessage(message);
    }

    /**
     *
     * @see org.springframework.web.socket.WebSocketHandler#supportsPartialMessages()
     */
    @Override
    public boolean supportsPartialMessages() {

        return false;
    }

    /**
     * sendMessageToUser:发给指定用户
     * @author zhaoshouyun
     * @param userId
     * @param contents
     * @since JDK 1.7
     */
    public void sendMessageToUser(String userId,String contents) {
        WebSocketSession session = userMap.get(userId);
        if(session !=null && session.isOpen()) {
            try {
                TextMessage message = new TextMessage(contents);
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * sendMessageToAllUsers:发给所有的用户
     * @author zhaoshouyun
     * @param contents
     * @since JDK 1.7
     */
    public void sendMessageToAllUsers(String contents) {
        Set<String> userIds = userMap.keySet();
        for(String userId: userIds) {
            this.sendMessageToUser(userId, contents);
        }
    }

    /**
     * getUserId:获取用户id
     * @author zhaoshouyun
     * @param session
     * @return
     * @since JDK 1.7
     */
    private String  getUserId(WebSocketSession session){
        try {
            String userId = (String)session.getAttributes().get(USER_KEY);
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
