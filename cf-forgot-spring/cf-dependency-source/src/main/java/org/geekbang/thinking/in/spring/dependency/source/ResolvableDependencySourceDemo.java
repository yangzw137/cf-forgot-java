package org.geekbang.thinking.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/2/27
 * @since todo
 */
public class ResolvableDependencySourceDemo {
    @Autowired
    private String value;

    @PostConstruct
    public void print() {
        System.out.println("autowried value: " + value);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencySourceDemo.class);

//        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "hello world.");
        });

        applicationContext.refresh();

        applicationContext.close();
    }
}
