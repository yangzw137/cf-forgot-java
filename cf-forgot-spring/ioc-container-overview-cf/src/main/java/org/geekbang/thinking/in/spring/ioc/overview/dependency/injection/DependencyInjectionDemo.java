package org.geekbang.thinking.in.spring.ioc.overview.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 学习要点： spring ioc 依赖注入
 *
 * @author cf
 * @date 2020/8/15
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory applicationContext = new
                ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");

        System.out.println(userRepository);

        //-- 依赖来源： spring 容器内建依赖
        System.out.println("get the injected bean factory: " + userRepository.getBeanFactory());
        System.out.println("the injected bean factory equse out side bean factory: "
                + (applicationContext == userRepository.getBeanFactory()));

        //-- 依赖来源： 自定义 bean
        System.out.println(userRepository.getObjectFactory());
        System.out.println(userRepository.getObjectFactory().getObject());

        //--
        System.out.println(userRepository.getApplicationContextObjectFactory());
        System.out.println(userRepository.getApplicationContextObjectFactory().getObject() == applicationContext);

        //--
        System.out.println(userRepository.getApplicationContext());
        System.out.println(userRepository.getApplicationContext() == applicationContext);

        //-- 依赖来源：内建 bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("environment: " + environment);
    }
}
