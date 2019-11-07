package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName:ProxyFactory
 * @Description: 代理模式：jdk动态代理
 * @Author:lvchunyang
 * @Date:11:52
 **/
public class ProxyFactory {
    //目标对象
    private Object target;
    //构造方法
    public ProxyFactory(Object target) {
        this.target = target;
    }
    //生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("执行代理过程start");
                        //执行目标对象方法
                        Object result = method.invoke(target,args);
                        System.out.println("执行代理过程end");
                        return result;
                    }
                });
    }
}
