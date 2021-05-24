package org.cf.forgot.jdk.lifecycle;

/**
 * Description:
 *
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/23
 * @since todo
 */
public class StaticChunkDemo {

    private static final int CONSTANT_1 = 123;

    static {
        System.out.println("StaticChunkDemo static chunk...");
    }

    private static long a = StaticBean.getCurrentTimeMillis("StaticChunkDemo");

    {
        a = 3;
        System.out.printf("CONSTANT_1: %d, a: %d\n", CONSTANT_1, a);
    }

    static {
        System.out.printf("static CONSTANT_1: %d, a: %d\n", CONSTANT_1, a);
    }

    public static void main(String[] args) {
        StaticChunkDemo demo = new StaticChunkDemo();
    }
}
