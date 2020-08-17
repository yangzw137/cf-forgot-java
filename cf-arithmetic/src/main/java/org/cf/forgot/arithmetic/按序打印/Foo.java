package org.cf.forgot.arithmetic.按序打印;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 杨志伟
 * @date 2020/8/10
 */
class Foo {

    private byte[] flag = new byte[3];
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
        }finally {
            flag[0] = 1;
            if(condition1 != null) {
                condition1.signal();
            }
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            if(flag[0] == 0) {
                condition1 = lock.newCondition();
                condition1.await();
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
        } finally {
            flag[1] = 1;
            if(condition2 != null) {
                condition2.signal();
            }
            lock.unlock();
        }

    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            if(flag[1] == 0) {
                condition2 = lock.newCondition();
                condition2.await();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();

        }finally {
            flag[2] = 1;
            lock.unlock();
        }
    }

}