package org.geekbang.thinking.in.spring.ioc.overview.ioccontainer;

import org.geekbang.thinking.in.spring.ioc.overview.domain.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cf
 * @date 2020/8/16
 */
public class WhoIsTheIocContainer {

    public static void main(String[] args) {
        BeanFactory beanFactory = new
                ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        ApplicationContext applicationContext = (ApplicationContext) beanFactory;

        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        BeanFactory injectedBeanFactory = userRepository.getBeanFactory();

        System.out.println("beanFactory: " + beanFactory);
        System.out.println("injectedBeanFactory: " + injectedBeanFactory);

        //--
        System.out.println(applicationContext.getParent());
    }
}
