package com.test;

import org.apache.tomcat.util.security.MD5Encoder;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @ClassName:Sxhs
 * @Description:
 * @Author:lvchunyang
 * @Date:16:38
 **/
public class Sxhs {


    public static void main(String[] args)throws Exception{
        String flower = "";
        int a,b,c;
        for(int i=100;i<=999;i++){
            a = i%10;
            b = (i/10)%10;
            c = (i/100)%10;
            if(i==a*a*a+b*b*b+c*c*c){
                flower = flower + i +",";
            }
        }
        if("".equals(flower)){
            System.out.println("100~999之间不存在水仙花数");
        }else{
            System.out.println("100~999之间的在水仙花数有"+flower.substring(0,flower.length()-1));

            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update("sa".getBytes());
            System.out.println(new BigInteger(1, md.digest()).toString(16));
        }
    }
}
