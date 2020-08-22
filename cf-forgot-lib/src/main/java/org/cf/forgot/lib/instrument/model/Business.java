package org.cf.forgot.lib.instrument.model;

/**
 * @author cf
 * @date 2020/8/21
 */
public class Business {
    public boolean doSomeThing() {
        System.out.println("执行业务逻辑");
        return true;

    }


    public void doSomeThing2() {
        String s = "执行业务逻辑2";
        System.out.println(s);
    }
}
