package org.cf.forgot.jdk.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author 杨志伟
 * @date 2020/7/26
 */
public class ThreadstateDemo {
    public static void main(String[] args) {
        ThreadstateDemo demo = new ThreadstateDemo();
        Thread thread = new Thread(()->{
            System.out.println("thread run end...");
        });
        thread.start();
        demo.sleep(1000);
        System.out.println("after run1, thread state: " + thread.getState());
        thread.start();
        demo.sleep(1000);
        System.out.println("after run2, thread state: " + thread.getState());
    }

    public void sleep(int n) {
        try {
            TimeUnit.MILLISECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
