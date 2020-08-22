package org.cf.forgot.jdk.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author 杨志伟
 * @date 2020/7/24
 */
public class SyncLock {
    byte[] bs = new byte[0];

    public static void main(String[] args) {
        SyncLock syncLock = new SyncLock();
        Thread thread = new Thread(() -> {
//            synchronized (SyncLock.class) {
//                syncLock.rc("aaaaa");
//            }
            syncLock.rc("aaaaa");
        });
        thread.start();

        new Thread(() -> {
            System.out.println("thry lock.........");
            synchronized (SyncLock.class) {
                System.out.println("I get the sync lock, you had release lock.............");
            }
        }).start();

        synchronized (syncLock) {
            try {
                syncLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void rc(String str) {
        synchronized (SyncLock.class) {
            sleep(100);
            rc("aaaaa");
        }
//        sleep(100000);
//        rc(str);
    }

    void sleep(long n) {
        try {
            TimeUnit.MICROSECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
