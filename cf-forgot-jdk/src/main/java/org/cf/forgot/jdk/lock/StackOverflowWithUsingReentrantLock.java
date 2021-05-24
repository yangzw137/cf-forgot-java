package org.cf.forgot.jdk.lock;

import org.apache.commons.lang3.RandomStringUtils;
import org.cf.forgot.commons.NamedThreadFactory;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/22
 * @since todo
 */
public class StackOverflowWithUsingReentrantLock {

    private static ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10, new NamedThreadFactory("thread"));

    private static ReentrantLock lock = new ReentrantLock();

    private static long counter = 0;

    public static void main(String[] args) {
        while (true) {
            if (poolExecutor.getTaskCount() > 10000) {
                sleep(1000);
                System.out.printf("lock HoldCount: %d \n", lock.getHoldCount());
            }
            try {
                Future<Boolean> future = poolExecutor.submit(createTask());
                if (!future.get()) {
                    break;
                }
            } catch (Throwable e) {
//                e.printStackTrace();
            }

        }
    }

    private static Callable<Boolean> createTask() {
        return new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    return runTask(RandomStringUtils.randomAlphanumeric(1024), 10L);
                } catch (Throwable t) {
                    if (lock.isLocked()) {//抛异常时锁没有释放
                        System.err.printf("can not get lock, threadname: %s\n", Thread.currentThread().getName());
                        t.printStackTrace();
                        throw t;
                    } else {
                        System.err.printf("can not get lock, threadname: %s\n", Thread.currentThread().getName());
                    }
                    return true;
                }
            }
        };
    }

    private static boolean runTask(String s, long... n) {
        lock.lock();
        try {
            runTask(RandomStringUtils.randomAlphanumeric(1024), 1L, 2L, 3L, 1L, 2L, 3L, 1L, 2L, 3L, 1L, 2L, 3L);
            if (counter++ % 10000 == 0) {
                System.out.println("--------------------");
            }
        } finally {
            lock.unlock();//一定会解锁？？？
        }
        return true;
    }

    private static void sleep(int n) {
        try {
            TimeUnit.MILLISECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
