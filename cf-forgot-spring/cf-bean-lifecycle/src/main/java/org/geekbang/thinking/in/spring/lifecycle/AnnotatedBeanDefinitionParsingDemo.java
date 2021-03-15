package org.geekbang.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/14
 * @since todo
 */
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader definitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCounterBefore = beanFactory.getBeanDefinitionCount();
        definitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCounterAfter = beanFactory.getBeanDefinitionCount();

        System.out.println("count: " + (beanDefinitionCounterAfter - beanDefinitionCounterBefore));

        System.out.println("bean: " + beanFactory.getBean("annotatedBeanDefinitionParsingDemo"));

    }
}
