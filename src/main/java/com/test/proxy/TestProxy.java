package com.test.proxy;

import java.lang.reflect.Proxy;

/**
 * @ClassName:TestProxy
 * @Description:代理模式测试
 * @Author:lvchunyang
 * @Date:11:58
 **/
public class TestProxy {
    //jdk动态代理，接口代理：匿名内部类实现拦截器
    public static void main(String[] args){
        User user = new User();
        System.out.println(user.getClass());

        IUser proxy = (IUser) new ProxyFactory(user).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.insertUser("name");
    }

}

//测试cglib动态代理
class TestCglib{
    public static void main(String[] args){
        UserUnImplements target = new UserUnImplements();
        UserUnImplements proxy = (UserUnImplements)new CglibProxyFactory(target).getProxyInstance();
        proxy.insertUser();
        proxy.insertUser();

        System.out.println("___________________________________________________________________________");
        //lazyload
        UserUnImplements proxy2 = (UserUnImplements)new CglibProxyFactory(target).getProxyInstanceLazyLoad();
        proxy2.insertUser();
        proxy2.insertUser();
    }
}

//拦截器
class TestJdkProxy{
    public static void main(String[] args){
        User user = new User();
        JavaProxyFactory javaProxyFactory = new JavaProxyFactory(user);
        IUser iuser = (IUser) Proxy.newProxyInstance(user.getClass().getClassLoader(),user.getClass().getInterfaces(),javaProxyFactory);
        iuser.insertUser(new String("name"));
    }
}
