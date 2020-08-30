package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cf
 * @date 2020/8/29
 */
public class SingletoneBeanRegistrationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.refresh();

        UserFactory userFactory = new DefaultUserFactory();
        applicationContext.getBeanFactory().registerSingleton("sigletonUserFactory", userFactory);

        UserFactory userFactoryLookup = applicationContext.getBean("sigletonUserFactory", UserFactory.class);
        System.out.println("userFactory == userFactoryLookup: " + (userFactory == userFactoryLookup));

        applicationContext.close();

    }
}
