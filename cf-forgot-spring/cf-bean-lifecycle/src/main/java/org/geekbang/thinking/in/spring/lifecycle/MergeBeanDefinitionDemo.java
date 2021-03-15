package org.geekbang.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/14
 * @since todo
 */
public class MergeBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);


        String localtion = "META-INF/dependency-lookup-context.xml";
        Resource resource = new ClassPathResource(localtion);
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");

        xmlBeanDefinitionReader.loadBeanDefinitions(encodedResource);

        int count = beanFactory.getBeanDefinitionCount();
        System.out.println("beanDefinition Count: " + count);

        System.out.println("user: " + beanFactory.getBean("user"));
        System.out.println("superUser: " + beanFactory.getBean("superUser"));
    }
}
