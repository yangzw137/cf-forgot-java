package org.cf.forgot.jdk.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 杨志伟
 * @date 2020/7/17
 */
public class ReentrantLockDemo {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        demo.startNewThread(1);
        demo.startNewThread(2);
        demo.startNewThread(3);
        demo.startNewThread(4);


    }


    private void startNewThread(int n) {
        CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread() {

            @Override
            public void run() {
                System.out.println("running" + n);
                if (false) {
                    lock.lock();
                }
                System.out.println("has locked..." + n);

                if (true){
                    try {
                        lock.unlock();
                    } catch (IllegalMonitorStateException e) {

                    }
                    System.out.println("has unlocked...");
                }
                latch.countDown();
            }
        };

        thread.start();

        try {
            latch.await();
            System.out.println("after latch: thread state: " + thread.getState().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
