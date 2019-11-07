package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName:JavaProxyFactory
 * @Description:
 * @Author:lvchunyang
 * @Date:16:47
 **/
public class JavaProxyFactory implements InvocationHandler {
    private Object target;

    public JavaProxyFactory(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前");
        method.invoke(target,args);
        System.out.println("代理之后");
        return null;
    }
}
