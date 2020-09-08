package org.geekbang.thinking.in.spring.depency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author cf
 * @date 2020/8/30
 */
@Configuration
public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();

        lookupBean(applicationContext);
        lookupStringBeanLazy(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStream(applicationContext);

        applicationContext.close();
    }

    private static void lookupByStream(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        objectProvider.stream().forEach(System.out::println);
    }

    @Bean
    public Date date() {
        return new Date();
    }

    @Bean
    @Primary
    public String helloWorld() {
        return "hello world.";
    }

    @Bean
    public String message() {
        return "Message";
    }

    public static void lookupIfAvailable(ApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        System.out.println(objectProvider.getIfAvailable(() -> User.create(11L, "xxxxx")));
    }

    public static void lookupStringBeanLazy(ApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }

    public static void lookupBean(ApplicationContext applicationContext) {
        ObjectProvider<Date> objectProvider = applicationContext.getBeanProvider(Date.class);
        System.out.println(objectProvider.getObject());
    }

    public static void sleep(long n) {
        try {
            TimeUnit.MILLISECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
