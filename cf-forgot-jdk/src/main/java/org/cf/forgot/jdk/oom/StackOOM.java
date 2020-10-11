package org.cf.forgot.jdk.oom;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/10/10
 * @since
 */
public class StackOOM {

    public static void main(String[] args) {
        while (true) {
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }
}
