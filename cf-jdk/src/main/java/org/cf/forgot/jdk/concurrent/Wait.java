package org.cf.forgot.jdk.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 杨志伟
 * @date 2020/7/24
 */
public class Wait {
    public static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {

        try {
            synchronized (Wait.class) {
                Wait.class.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
