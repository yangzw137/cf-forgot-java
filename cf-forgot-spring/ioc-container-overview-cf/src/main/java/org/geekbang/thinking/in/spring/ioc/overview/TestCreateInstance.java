package org.geekbang.thinking.in.spring.ioc.overview;

import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 *
 * @author cf
 * @date 2020/8/15
 */
public class TestCreateInstance extends AbstractFactoryBean {

    @Override
    public Class<?> getObjectType() {
        return Object.class;
    }

    @Override
    public Object createInstance() {
        System.out.println("createInstance begin...");
        return new Object();
    }
}
