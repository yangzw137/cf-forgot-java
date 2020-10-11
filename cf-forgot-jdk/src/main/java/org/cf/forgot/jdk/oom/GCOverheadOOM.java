package org.cf.forgot.jdk.oom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * JDK1.6之后新增了一个错误类型，如果堆内存太小的时候会报这个错误。
 * 如果98%的GC的时候回收不到2%的时候会报这个错误，也就是最小最大内存出现了问题的时候会报这个错误。
 * -Xms10M -Xmx10M
 *
 * <p>
 * @date 2020/10/10
 * @since
 */
public class GCOverheadOOM {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        while (true) {
            executorService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
