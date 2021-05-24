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
public class SubClass extends SuperClass {

    public static long s = StaticBean.getCurrentTimeMillis("SubClass static variable");

    protected long sub_b = StaticBean.getCurrentTimeMillis("SubClass instance variable");

    {
        System.out.println("SubClass chunk initialization...");
    }

    static {
        System.out.println("SubClass static chunk initialization....");
    }

    public SubClass() {
        System.out.println("SubClass constructor initialization....");
    }
}
