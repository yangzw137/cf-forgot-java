package org.cf.forgot.jdk.thread;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 杨志伟
 * @date 2020/7/26
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(1, 1,
                        600, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                        new ThreadFactory() {
                            @Override
                            public Thread newThread(Runnable r) {
                                Thread thread = new Thread(r);
                                thread.setDaemon(true);
                                return thread;
                            }
                        });


        try {
            System.out.println("111111111");
            Future future = threadPoolExecutor.submit(() -> {
                return "a1";
            });
            System.out.println("222222");
            System.out.println("task1: " + future.get());
            future = threadPoolExecutor.submit(() -> {
                return "a2";
            });
            System.out.println("3333333");
            System.out.println("task1: " + future.get());
            System.out.println("4444444");

            threadPoolExecutor.execute(() -> {
                System.out.println("runable....");
            });

            System.in.read();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
