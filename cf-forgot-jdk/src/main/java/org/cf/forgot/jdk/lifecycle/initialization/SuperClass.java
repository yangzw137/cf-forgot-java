package org.cf.forgot.jdk.lifecycle.initialization;

import org.cf.forgot.jdk.lifecycle.StaticBean;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/23
 * @since todo
 */
public class SuperClass {


    protected static long a = StaticBean.getCurrentTimeMillis("SuperClass static variable");;

    protected long b = StaticBean.getCurrentTimeMillis("SuperClass instance variable");

    protected final static long c = 1000;

    {
        System.out.println("SuperClass chunk initialization...");
    }

    static {
        System.out.println("SuperClass static chunk initialization...");
    }

    public SuperClass() {
        System.out.println("SuperClass constructor initialization...");
    }
}
