package org.cf.forgot.jdk.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author 杨志伟
 * @date 2020/7/24
 */
public class RunnableDemo implements Runnable {
    private Lock lock;
    public RunnableDemo(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.tryLock(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("11``````");
    }

}
