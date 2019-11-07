package com.test.thread;

/**
 * @ClassName:Thread_02_Runable
 * @Description: 实现Runnable接口
 * @Author:lvchunyang
 * @Date:9:57
 **/
public class Thread_02_Runable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread_02_Runable");
    }

    public static void main(String[] args){
        Thread_02_Runable thread_02_runable = new Thread_02_Runable();
        Thread t = new Thread(thread_02_runable);
        t.start();
    }
}
