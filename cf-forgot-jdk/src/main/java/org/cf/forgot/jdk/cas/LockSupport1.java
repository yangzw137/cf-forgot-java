package org.cf.forgot.jdk.cas;

import java.util.concurrent.locks.LockSupport;

/**
 * @author 杨志伟
 * @date 2020/8/8
 */
public class LockSupport1 {

    public static void main(String[] args) {
        LockSupport.park(LockSupport1.class);
        System.out.println("1111");
    }
}
