package com.taobao.demo;

/**
 * @author cf
 * @date 2020/9/8
 */

import java.util.concurrent.TimeUnit;

/**
 * 报时的钟
 */
public class Clock {

    private static long counter = 0;
    private static long threshold = 10000000000L;

    // 日期格式化
    private final java.text.SimpleDateFormat clockDateFormat
            = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 状态检查
     */
    final void checkState() {
        throw new IllegalStateException("STATE ERROR!");
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    final java.util.Date now() {
        return new java.util.Date();
    }

    /**
     * 报告时间
     *
     * @return 报告时间
     */
    final String report() {
        checkState();
        return clockDateFormat.format(now());
    }

    /**
     * 循环播报时间
     */
    final void loopReport() throws InterruptedException {
        while (true) {
            try {
                long m = threshold*3;
                if (m == 0) {
                    m = 10000000000L;
                }
                if (counter % m == 0) {
                    System.out.println(report());
                }
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
            counter ++;
            if (counter<0) {
                System.out.println("back to zero.");
                counter = 0;
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            long old = 0;
            while (true) {
                try {
                    threshold = counter - old;
                    old = counter;
                    System.out.println("call times: " + threshold);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        new Clock().loopReport();
    }
}
