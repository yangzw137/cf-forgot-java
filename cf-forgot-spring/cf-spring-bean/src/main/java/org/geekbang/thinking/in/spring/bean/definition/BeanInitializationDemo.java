package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author cf
 * @date 2020/8/29
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        System.out.println("spring context has initialized.");
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println("userFactory: " + userFactory);
        System.out.println("before closing context...");
        applicationContext.close();
        System.out.println("after closed context...");

    }

    @Bean(initMethod = "customInit", destroyMethod = "customDestory")
//    @Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
