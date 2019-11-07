package com.test.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.LazyLoader;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName:CglibProxy2
 * @Description:
 * @Author:lvchunyang
 * @Date:17:27
 **/
public class CglibProxy2 implements MethodInterceptor {

    private Object target;

    public CglibProxy2(Object target) {
        this.target = target;
    }

    public Object getCglibProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer;
    }


    public Object getCglibLoadProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(new LazyLoader() {
            @Override
            public Object loadObject() throws Exception {
                return target;
            }
        });
        return enhancer;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return method.invoke(target,objects);
    }
}
