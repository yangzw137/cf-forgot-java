package org.geekbang.thinking.in.spring.ioc.overview.dependency.createInstance;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cf
 * @date 2020/8/15
 */
public class CreateInstanceDemo {

    public static void main(String[] args) {
        String path = "classpath:/META-INF/dependency-lookup-context-createInstance.xml";
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);

        Object object = beanFactory.getBean("testCreateInstance");
        System.out.println("object: " + object);
    }
}
