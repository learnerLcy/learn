package com.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName:Thread_03_Callable
 * @Description:
 * @Author:lvchunyang
 * @Date:10:00
 **/
public class Thread_03_Callable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("Thread_03_Callable");
        return null;
    }
    
    public static void main(String[] args){
        Thread_03_Callable thread_03_Callable = new Thread_03_Callable();
        FutureTask futureTask = new FutureTask(thread_03_Callable);
        Thread thread = new Thread(futureTask);
        thread.start();
    }
}
