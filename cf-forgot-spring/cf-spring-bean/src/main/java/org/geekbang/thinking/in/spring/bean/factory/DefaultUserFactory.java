package org.geekbang.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author cf
 * @date 2020/8/29
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct: initialization DefaultUserFactory.");
    }

    public void customInit() {
        System.out.println("customInit: initialization DefaultUserFactory.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet: initialization DefaultUserFactory.");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy: destoring DefaultUserFactory.");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy: destoring DefaultUserFactory.");
    }

    public void customDestory() {
        System.out.println("customDestory: destoring DefaultUserFactory.");
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("DefaultUserFactory.finalizing...");
    }

}
