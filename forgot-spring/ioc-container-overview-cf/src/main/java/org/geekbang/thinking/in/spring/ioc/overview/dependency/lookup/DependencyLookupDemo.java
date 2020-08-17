package org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 学习要点： spring ioc 依赖查找
 * 1、根据 bean 名称查找
 * 1.1、实时查找；
 * 1.2、延迟查找；
 *
 * 2、根据 bean 类型查找
 * 2.1、单个 bean 查找
 * 2.2、集合 bean 查找
 *
 * 3、根据 bean 名称 + 类型查找
 *
 * 4、根据 annotation 查找
 *
 *
 *
 * @author cf
 * @date 2020/8/15
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new
                ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupInTime(beanFactory);
        lookupInLazy(beanFactory);

        lookupByType(beanFactory);

        lookupCollectionByType(beanFactory);

        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);

            System.out.println("lookup collection by annotation, map: " + userMap);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);

            System.out.println("lookup collection by type, map: " + userMap);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("lookup user by type, user: " + user);
    }

    private static void lookupInTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("lookup user in time: " + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("lookup user in lazy: " + user);
    }
}
