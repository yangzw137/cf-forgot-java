package org.cf.forgot.jdk.thread.interrupt;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 杨志伟
 * @date 2020/8/9
 */
public class CfExample2 extends Thread {
    Semaphore semaphore = new Semaphore(1);
    volatile boolean acquired = false;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public static void main(String args[]) throws Exception {
        Example2 thread = new Example2();
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop...");
        // 设置中断信号量
        thread.stop = true;
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }

    @Override
    public void run() {
//        try {
//            if (!acquired) {
//                semaphore.acquire();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return;
//        }
        // 每隔一秒检测一下中断信号量
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Thread is running...");
            long time = System.currentTimeMillis();
            /*
             * 使用while循环模拟 sleep 方法，这里不要使用sleep，否则在阻塞时会 抛
             * InterruptedException异常而退出循环，这样while检测stop条件就不会执行，
             * 失去了意义。
             */
            while ((System.currentTimeMillis() - time < 1000)) {}
        }
        System.out.println("Thread exiting under request...");
    }
}
