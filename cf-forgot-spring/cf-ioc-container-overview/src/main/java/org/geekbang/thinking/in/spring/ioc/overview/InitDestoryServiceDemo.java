package org.geekbang.thinking.in.spring.ioc.overview;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Description:
 * <p>
 * @date 2020/10/18
 * @since
 */
public class InitDestoryServiceDemo {

    @PostConstruct
    public void initMethod1() {
        System.out.println("invoke InitDestoryServiceDemo.initMethod1");
    }

    @PostConstruct
    public void initMethod2() {
        System.out.println("invoke InitDestoryServiceDemo.initMethod2");
    }

    @PreDestroy
    public void destoryMethod1() {
        System.out.println("invoke InitDestoryServiceDemo.destoryMethod1");
    }

    @PreDestroy
    public void destoryMethod2() {
        System.out.println("invoke InitDestoryServiceDemo.destoryMethod2");
    }
}
