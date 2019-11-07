package com.test.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.LazyLoader;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName:CglibProxyFactory
 * @Description:   代理模式：cglib动态代理
 * @Author:lvchunyang
 * @Date:14:01
 **/
public class CglibProxyFactory implements MethodInterceptor {
    //目标对象
    private Object target;
    //构造对象
    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    //创建代理对象
    public Object getProxyInstance(){
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }
    //创建懒加载方式的代理对象
    public Object getProxyInstanceLazyLoad(){
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(new LazyLoader() {
            @Override
            public Object loadObject() throws Exception {
                System.out.println("cglib start    lazyload");

                System.out.println("strat 执行方法");
                System.out.println("cglib end      lazyload");
                return target;
            }
        });
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib start");

        System.out.println("strat 执行方法");
        Object result = method.invoke(target,objects);
        System.out.println("cglib end");
        return result;
    }
}
