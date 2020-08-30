package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cf
 * @date 2020/8/29
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        final String location = "classpath:/META-INF/bean-instantiation-context.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(location);

        User userCreateByStaticMethod = (User) applicationContext.getBean("user-create-by-static-method");
        User userCreateByFactory = (User) applicationContext.getBean("user-create-by-factory");
        User userCreateByFactoryBean = (User) applicationContext.getBean("user-create-by-factory-bean");

        System.out.println("userCreateByStaticMethod: " + userCreateByStaticMethod);
        System.out.println("userCreateByFactory: " + userCreateByFactory);
        System.out.println("userCreateByFactoryBean: " + userCreateByFactoryBean);
        System.out.println(userCreateByStaticMethod == userCreateByFactory);
        System.out.println(userCreateByStaticMethod == userCreateByFactoryBean);
    }
}
