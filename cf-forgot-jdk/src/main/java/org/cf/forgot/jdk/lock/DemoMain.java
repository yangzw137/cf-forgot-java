package org.cf.forgot.jdk.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 杨志伟
 * @date 2020/7/17
 */
public class DemoMain {

    private Lock lock = new ReentrantLock();
    private Thread thread;

    public static void main(String[] args) {
        DemoMain d = new DemoMain();

        d.startNewThread();

        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        d.thread.interrupt();
//        d.thread.suspend();
        d.thread.stop();
//        Thread.yield();

        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int counter = 0;
        for (;;) {
            counter ++;

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                if (counter % 2 == 0) {
                    System.out.println("state: " + d.thread.getState().toString());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter > Integer.MAX_VALUE) {
                System.out.println("break...");
                break;
            }
        }

        synchronized (DemoMain.class) {
            try {
                DemoMain.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end.....");
    }

    private void startNewThread() {
        thread = new Thread() {

            @Override
            public void run() {
                System.out.println("running");
                try {
                    lock.lock();
                    System.out.println("has locked...");
                    try {
                        sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int counter = 0;
                    for (; ; ) {
                        counter++;

                        try {
                            sleep(1000);
                            if (counter % 2 == 0) {
                                System.out.println("on locked...");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (counter > Integer.MAX_VALUE) {
                            System.out.println("break...");
                            break;
                        }
                    }
                }finally {
                    try{
                        System.out.println("on locked...");
                    } finally {
                        lock.unlock();
                        System.out.println("has unlocked...");
                    }
                }
            }
        };

        thread.start();
    }
}
