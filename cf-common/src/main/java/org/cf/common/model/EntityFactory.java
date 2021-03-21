package org.cf.common.model;

import java.util.Date;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/17
 * @since todo
 */
public class EntityFactory {

    public static <T> GBean1 createGBean1(T inner) {
        GBean1 gb = new GBean1();
        gb.setName(new Date().toString());
        gb.setInner(inner);
        return gb;
    }

    public static Bean2 createBean2() {
        Bean2 b2 = new Bean2();
        b2.setB((byte) 2);
        b2.setI(222);
        b2.setS("bean2 string");
        b2.setC('C');
        return b2;
    }

    public static Bean1 createBean1() {
        Bean1 b1 = new Bean1();
        b1.setB((byte) 1);
        b1.setI(111);
        b1.setS("bean1 string");
        b1.setC('b');
        b1.setBean2(createBean2());
        return b1;
    }
}

