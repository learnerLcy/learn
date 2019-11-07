package com.test.thread;

/**
 * @ClassName:Thread_01
 * @Description: 01:继承thread;02:重写run方法;03:实例化类，执行start方法
 * @Author:lvchunyang
 * @Date:9:36
 **/
public class Thread_01 extends Thread{
    @Override
    public void run() {
        System.out.println("Thread_01");
        for(int i =0;i<100;i++){
            System.out.println("Thread_01@@@@@@@@"+i);
        }
        super.run();
    }

    public static void main(String[] args){
        Thread_01 thread_01 = new Thread_01();
        for(int i =0;i<100;i++){
            System.out.println("main########"+i);
            if(i==10){
                thread_01.start();
            }
            if(i==80){
                try {
                    thread_01.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
