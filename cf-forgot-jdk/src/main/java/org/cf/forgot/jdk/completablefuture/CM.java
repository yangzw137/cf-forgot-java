package org.cf.forgot.jdk.completablefuture;

/**
 * @author 杨志伟
 * @date 2020/7/24
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author 杨志伟
 * @date 2020/7/24
 */
public class CM {

    public static void main(String[] args) {
        Supplier<String> supplier = () -> {
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "aaa";
        };
        CompletableFuture
                .supplyAsync(supplier)
                .whenComplete((t, u) -> {
            t = t + 1;
            t = t + 1;
            t = t + 1;
        });

        try {
            synchronized (CM.class) {
                CM.class.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
