package com.learn.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
/**
 * @ClassName:WebSocketConfig
 * @Description:
 * @Author:lvchunyang
 * @Date:10:01
 **/
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    /**
     * @see org.springframework.web.socket.config.annotation.WebSocketConfigurer#registerWebSocketHandlers(org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry)
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler( new MyMessageHandler(), "/testHandler").addInterceptors(new WebSocketInterceptor());
        registry.addHandler( new MyMessageHandler(), "/socketJs/testHandler").addInterceptors(new WebSocketInterceptor()).withSockJS();

    }

    /*@Bean
    public WebSocketHandler myMessageHandler(){
        return new MyMessageHandler();
    }*/
}
