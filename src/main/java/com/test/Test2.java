package com.test;

import com.fasterxml.jackson.databind.ObjectReader;
import com.test.proxy.User;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName:Test
 * @Description:
 * @Author:lvchunyang
 * @Date:16:53
 **/
public class Test2 {

    public static void main(String[] args) throws Exception{
        /*左移乘2，右移除2然后向下取整，无符号右移补0*/
        System.out.println(7>>1);
        System.out.println(Integer.toBinaryString(-7));
        System.out.println(-7>>1);
        System.out.println(Integer.toBinaryString(-7>>1));

        /**获取String对象指定的构造方法(通过方法的参数类型,传递参数的Class对象)*/
        Constructor constructor = String.class.getConstructor(StringBuffer.class);//得到String对象的一个参数是StringBuffer的构造方法
        String str = (String) constructor.newInstance(new StringBuffer("abc"));//生成对象String，当然要传递一个StringBuffer参数
        System.out.println(str);//打印值
        /**总结:这种方法是要传递参数类型和参数的值，getConstructor(...)方法的参数是一个可变参数，因为构造方法可能有多个参数*/

        /**获取String默认的构造方法生成String对象*/
        String str1 = String.class.newInstance();

        /**获取String对象的所有构造方法,并将构造方法的参数类型打印出来*/
        Constructor[] constructors = Class.forName("java.lang.String").getConstructors();
        for(int i=0;i<constructors.length;i++){
            Type[] type = constructors[i].getGenericParameterTypes();
            for(int j=0;j<type.length;j++)
                System.out.print(type[j]+",");
            System.out.println();
        }

        Class userBeanClass = User.class;
        Field[] fields = userBeanClass.getDeclaredFields();
        //注意，打印方法时无法得到局部变量的名称，因为jvm只知道它的类型
        Method[] methods = userBeanClass.getDeclaredMethods();
        for (Method method : methods) {
            //依次获得方法的修饰符，返回类型和名称，外加方法中的参数
            String methodString = Modifier.toString(method.getModifiers()) + " " ; // private static
            methodString += method.getReturnType().getSimpleName() + " "; // void
            methodString += method.getName() + "("; // staticMethod
            Class[] parameters = method.getParameterTypes();
            Parameter[] p = method.getParameters();

            for (Class parameter : parameters) {
                methodString += parameter.getSimpleName() + " " ; // String
            }
            methodString += ")";
            System.out.println(methodString);
        }


        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("泛型测试类型相同");
        }
        Map<String,Object> map = new HashMap<String, Object>();
       /* List list = new ArrayList();
        LinkedList*/
        /*Vector;
        CopyOnWriteArrayList;*/
        /*ObjectOutputStream
        ObjectStreamClass*/
        System.out.println(11 >> 2);
    }
}
