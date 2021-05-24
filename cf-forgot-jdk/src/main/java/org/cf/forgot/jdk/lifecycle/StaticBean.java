package org.cf.forgot.jdk.lifecycle;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/23
 * @since todo
 */
public class StaticBean {

    public static final int A = 1;

    public static long getCurrentTimeMillis(String msg) {
        System.out.printf("getCurrentTimeMillis, msg: %s\n", msg);
        return System.currentTimeMillis();
    }
}
