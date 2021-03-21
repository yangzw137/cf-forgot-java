package org.geekbang.thinking.in.spring.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/18
 * @since todo
 */
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String[] localtions = {"META-INF/dependency-lookup-context.xml",
                "META-INF/bean-constructor-dependency-injection.xml"};

//        Resource resource = new ClassPathResource(localtion);
//        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");

        xmlBeanDefinitionReader.loadBeanDefinitions(localtions);
        beanFactory.preInstantiateSingletons();

        int count = beanFactory.getBeanDefinitionCount();
        System.out.println("beanDefinition Count: " + count);


        System.out.println("user: " + beanFactory.getBean("user", User.class));
        System.out.println("superUser: " + beanFactory.getBean("superUser", User.class));
        System.out.println("userHolder: " + beanFactory.getBean("userHolder", UserHolder.class));
    }

}
