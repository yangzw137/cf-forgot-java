package org.cf.forgot.jdk.concurrent.threadpool;

import org.cf.forgot.commons.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * Description: 线程池执行任务，当任务抛异常后，是否还可以继续执行新任务
 * <p>
 *     1 通过 {@link ThreadPoolExecutor#ThreadPoolExecutor } 创建的线程池；
 * </p>
 * <p>
 *     2 通过 {@link Executors } 创建的线程池
 * </p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/22
 * @since todo
 */
public class PoolThrowsExceptionDemo {

    private static ThreadPoolExecutor poolExecutor1 = new ThreadPoolExecutor(1, 1, 0,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new NamedThreadFactory("threadPool", true, true));

    private static ExecutorService poolExecutor2 = Executors.newFixedThreadPool(1);
    private static ExecutorService poolExecutor3 = Executors.newSingleThreadExecutor();

    private static ExecutorService poolExecutor4 = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue(10)){
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            System.out.println("afterExecute里面获取到异常信息"+t.getMessage());
        }
    };

    public static void main(String[] args) {

//        exeDemo(poolExecutor1);
//        exeDemo(poolExecutor2);

//        submitDemo(poolExecutor1);
//        submitDemo(poolExecutor2);

        exeDemo(poolExecutor4);
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void exeDemo(ExecutorService executorService) {
        try {
            executorService.execute(() -> {
                System.out.printf("----------execute 1/0, threadName: %s\n", Thread.currentThread().getName());
                int n = 1/0;
            });
            executorService.execute(() -> {
                System.out.printf("----------execute print, threadName: %s\n", Thread.currentThread().getName());
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void submitDemo(ExecutorService executorService) {
        try {
            executorService.submit(() -> {
                System.out.printf("----------execute 1/0, threadName: %s\n", Thread.currentThread().getName());
                int n = 1/0;
            });
            executorService.submit(() -> {
                System.out.printf("----------execute print, threadName: %s\n", Thread.currentThread().getName());
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
