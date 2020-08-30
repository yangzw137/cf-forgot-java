package org.geekbang.thinking.in.spring.depency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        sleep(1000);
        lookupBean(applicationContext);
        sleep(1000);
        lookupBean(applicationContext);

        applicationContext.close();
    }

    @Bean
    public Date date() {
        return new Date();
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
