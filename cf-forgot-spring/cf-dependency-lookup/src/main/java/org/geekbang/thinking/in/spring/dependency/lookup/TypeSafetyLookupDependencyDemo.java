package org.geekbang.thinking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cf
 * @date 2020/8/30
 */
public class TypeSafetyLookupDependencyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyLookupDependencyDemo.class);
        applicationContext.refresh();
        //--
        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectFactoryGetIfAvailable(applicationContext);

        //--
        diaplayListableBeanFactoryGetBeansOfType((DefaultListableBeanFactory) applicationContext.getBeanFactory());
        displayObjectProviderGetByStream(applicationContext);

        //--
        applicationContext.close();
    }

    private static void displayObjectProviderGetByStream(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printException("displayObjectProviderGetByStream", () ->
                objectProvider.stream().forEach(u -> {
                    System.out.println(u);
                })
        );
    }

    private static void diaplayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printException("diaplayListableDefaultListableBeanFactoryGetBeansOfType",
                () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectFactoryGetIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printException("displayObjectFactoryGetIfAvailable", () -> objectProvider.getIfAvailable());
    }

    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
        BeanFactory beanFactory = applicationContext.getBeanFactory();
        printException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printException("displayObjectFactoryGetObject", () -> objectProvider.getObject());
    }

    private static void printException(String source, Runnable runnable) {
        System.err.println("source from: " + source);
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }

}
