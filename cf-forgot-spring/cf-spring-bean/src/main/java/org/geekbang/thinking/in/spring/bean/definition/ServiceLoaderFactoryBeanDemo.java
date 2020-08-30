package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

/**
 * @author cf
 * @date 2020/8/29
 */
public class ServiceLoaderFactoryBeanDemo {

    public static void main(String[] args) {
        String localtion = "META-INF/special-bean-instantiation-context.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(localtion);

        jdkServiceLoader();

        ServiceLoader<UserFactory> serviceLoader = (ServiceLoader<UserFactory>) applicationContext.getBean(
                "userFactoryServiceLoader");
        displayServiceLoader(serviceLoader);

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser(2L, "coding life"));
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while(iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }

    public static void jdkServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = load(UserFactory.class,
                Thread.currentThread().getContextClassLoader());
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        for (Iterator<UserFactory> it = iterator; it.hasNext(); ) {
            UserFactory factory = it.next();
            System.out.println(factory.createUser());
        }
    }
}
