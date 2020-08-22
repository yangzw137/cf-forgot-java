package org.cf.forgot.jdk.thread.sleep;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨志伟
 * @date 2020/8/9
 */
public class Sleep1 {
    static Thread thread = null;
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2,
                6000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        poolExecutor.execute(() -> {
            thread = Thread.currentThread();
            while(true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    break;
                }
                System.out.println("working....");
            }
        });
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

//        synchronized (Sleep1.class) {
//            try {
//                Sleep1.class.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
