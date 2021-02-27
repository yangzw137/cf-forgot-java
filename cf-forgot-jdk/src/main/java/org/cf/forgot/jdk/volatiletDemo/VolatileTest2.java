package org.cf.forgot.jdk.volatiletDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/11/30
 * @since todo
 */
public class VolatileTest2 {
    static class A {
//        volatile int a = 0;
//        int a = 0;
        AtomicInteger a = new AtomicInteger(0);

        void increase() {
//            a++;
//            a = a + 1;
            a.incrementAndGet();
        }

        int getA() {
//            return a;
            return a.get();
        }
    }

    private static void sleep(int n) {
        try {
            TimeUnit.MILLISECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        A a = new A();
        final int t = 2;
        new Thread(() -> {
            sleep(t);
            for (int i = 0; i < 1000; i++) {
                a.increase();
            }
            System.out.println(a.getA());
        }).start();
        new Thread(() -> {
            sleep(t);
            for (int i = 0; i < 2000; i++) {
                a.increase();
            }
            System.out.println(a.getA());
        }).start();
        new Thread(() -> {
            sleep(t);
            for (int i = 0; i < 3000; i++) {
                a.increase();
            }
            System.out.println(a.getA());
        }).start();
        new Thread(() -> {
            sleep(t);
            for (int i = 0; i < 4000; i++) {
                a.increase();
            }
            System.out.println(a.getA());
        }).start();
        new Thread(() -> {
            sleep(t);
            for (int i = 0; i < 5000; i++) {
                a.increase();
            }
            System.out.println(a.getA());
        }).start();
        sleep(1000);
        System.out.println("a: " + a.getA());
//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
