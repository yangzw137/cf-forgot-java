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
public class ThreadPoolDemo2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new NamedThreadFactory("thread"));
        TaskInner taskInner = new TaskInner(executorService);
        System.out.println("1==>" + taskInner.getClass());
        executorService.execute(taskInner);


        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class TaskInner implements Runnable {
        private ExecutorService es;

        public TaskInner(ExecutorService es) {
            this.es = es;
        }

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
                    System.out.println("2==>" + this.getClass());
                    System.out.println("重启一个线程，继续执行");
                    //es.execute(this);
                }
            }
        }
    }
}
