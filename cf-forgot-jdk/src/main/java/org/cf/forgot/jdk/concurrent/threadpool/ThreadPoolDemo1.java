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
public class ThreadPoolDemo1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new NamedThreadFactory("thread"));

        executorService.execute(new Runnable() {
            int count = 1;
            int sum = 0;

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                        if (count == 101) {
                            break;
                        }
                        if (count == 50) {
                            sum = sum + count;
                            count++;
                            throw new RuntimeException("我是异常");
                        }
                        sum = sum + count;
                        System.out.println("##count:" + count + ",sum:" + sum + "; threadName: " + Thread.currentThread().getName());
                        count++;
                    } catch (Exception e) {
                        e.printStackTrace();
                        //注意这个地方
                        System.out.println("重启一个线程，继续执行");
                        executorService.execute(this);
                    }
                }
            }
        });

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
