package org.cf.forgot.jdk.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author yanghua.yi
 * @date 23/7/2020
 *
 */
public class StackOverFlow {
    private int i;
    private ReentrantLock lock = new ReentrantLock();
    private long counter = 0;

    public void plus(String str) {
        try {
//            lock.lock();
            lock.lockInterruptibly();
        }
//        catch (StackOverflowError error) {
////            error.printStackTrace();
//            return;
//        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
//            add(str);
            plus(str);
        }  finally {
            try {
                lock.unlock();
            } catch (Throwable throwable){
//                throwable.printStackTrace();
                lock.unlock();
                return;
            }
        }
    }

    public void add (String str){
        lock.lock();
//        System.out.println(Thread.currentThread().getName() + ": " + Thread.currentThread().getState().toString());
        lock.unlock();

//        try {
//            TimeUnit.MILLISECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        try{
//            counter ++;
//            if (counter % 10000 == 0) {
//                System.out.println(Thread.currentThread().getName() + ": " + Thread.currentThread().getState().toString());
//            }
//        } catch (Throwable ex) {
//            System.out.println("qqqqqqqqqqqq: " + Thread.currentThread().getName());
//            ex.printStackTrace();
//        } finally {
//            lock.unlock();
//        }

    }

    public ReentrantLock getLockS() {
        return lock;
    }

    public static void main(String[] args) throws InterruptedException {
        final StackOverFlow stackOverFlow = new StackOverFlow();
        Thread t1 = new Thread("aaa"){
            public void run() {
                try {
                    stackOverFlow.plus("aaa");
                } catch (Error e) {
                    System.out.println("Error:stack length:" + stackOverFlow.i);
                    e.printStackTrace();
                }
            }

        };
        t1.start();


        Thread t2 = new Thread("bbb"){
            public void run() {
                long counter = 0;
                while (true){
                    counter ++;
                    if (counter % 10 == 0) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("thread1 status: " + t1.getState());
                        System.out.println("thread2 status: " + Thread.currentThread().getState());
                        System.out.println(stackOverFlow.getLockS().toString());
                    }
//                    stackOverFlow.getLockS().lock();
//                    if (counter <= 2) {
//                        System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuu");
//                    }
//                    stackOverFlow.add("bbb");
                }
            }
        };
        t2.start();

        Thread.currentThread().join();
    }
}