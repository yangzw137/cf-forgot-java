package org.cf.forgot.netty.util;

import io.netty.util.internal.ThreadLocalRandom;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/15
 * @since todo
 */
public class Random {

    public static void main(String[] args) {
        for (int i=0;i<100;i++) {
            System.out.println(randomInt(30000));
        }
    }

    private static int randomInt(int seek) {
        return ThreadLocalRandom.current().nextInt(seek);
    }
}
