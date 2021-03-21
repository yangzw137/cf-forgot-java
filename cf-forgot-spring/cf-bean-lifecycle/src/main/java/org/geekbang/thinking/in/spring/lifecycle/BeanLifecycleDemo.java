package org.geekbang.thinking.in.spring.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

import java.util.concurrent.TimeUnit;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/21
 * @since todo
 */
public class BeanLifecycleDemo {

    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String[] localtions = {"META-INF/dependency-lookup-context.xml",
                "META-INF/bean-constructor-dependency-injection.xml"};

        xmlBeanDefinitionReader.loadBeanDefinitions(localtions);
        beanFactory.preInstantiateSingletons();

        int count = beanFactory.getBeanDefinitionCount();
        System.out.println("beanDefinition Count: " + count);


        System.out.println("user: " + beanFactory.getBean("user", User.class));
        System.out.println("superUser: " + beanFactory.getBean("superUser", User.class));
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println("userHolder: " + userHolder);

        beanFactory.destroyBean("userHolder", userHolder);

        System.out.println("userHolder: " + userHolder);

        userHolder = null;

        beanFactory.destroySingletons();

        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.gc();

    }
}
