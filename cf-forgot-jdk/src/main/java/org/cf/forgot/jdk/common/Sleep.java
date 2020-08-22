package org.cf.forgot.jdk.common;

import java.util.concurrent.TimeUnit;

/**
 * @author 杨志伟
 * @date 2020/7/26
 */
public class Sleep {

    public static void sleepEnough(long elapse) {
        try {
            TimeUnit.MILLISECONDS.sleep(elapse);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
