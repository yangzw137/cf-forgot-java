package org.cf.forgot.jdk.concurrent.threadpool;

import org.cf.forgot.commons.NamedThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/19
 * @since todo
 */
public class ThreadPoolDemo3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new NamedThreadFactory("thread"));

        exe(executorService);
        submit(executorService);

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void exe(ExecutorService executorService) {
        for (int i = 0; i < 10; i++) {
            try {
                executorService.execute(() -> {
                    System.out.println("threadName-execute: " + Thread.currentThread().getName());
                    throw new RuntimeException();
                });
            } catch (Exception ex) {
            }
        }
    }

    public static void submit(ExecutorService executorService) {
        for (int i = 0; i < 10; i++) {
            try {
                executorService.submit(() -> {
                    System.out.println("threadName-submit: " + Thread.currentThread().getName());
                    throw new RuntimeException();
                });
            } catch (Exception ex) {
            }
        }
    }
}
