package org.geekbang.thinking.in.spring.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * Description: 依赖来源示例
 * <p>
 * @date 2020/10/25
 * @since todo
 */
public class DependencySourceDemo {
    //依赖注入在 postProcessProperties， 早于 @postConstruct 和 setter注入
    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void initByInjection() {
        System.out.println();
        System.out.println();
        System.out.println("beanFactory == applicationContext: " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getParentBeanFactory(): " + (beanFactory == applicationContext.getParentBeanFactory()));
        System.out.println("beanFactory == applicationContext.getAutowireCapableBeanFactory(): " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext: " + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext: " + (applicationEventPublisher == applicationContext));
    }

    @PostConstruct
    public void initLookup() {
        System.out.println();
        System.out.println();
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(BeanFactory.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("can not find object of the type [" + beanType.getCanonicalName() + "] by beanfactory.");
        }
        return null;

    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);

        applicationContext.refresh();

        DependencySourceDemo dependencySourceDemo = applicationContext.getBean(DependencySourceDemo.class);
        System.out.println();
        System.out.println();
        System.out.println("beanFactory: " + dependencySourceDemo.beanFactory);
        System.out.println("applicationContext: " + dependencySourceDemo.applicationContext);
        System.out.println("resourceLoader: " + dependencySourceDemo.resourceLoader);
        System.out.println("applicationEventPublisher: " + dependencySourceDemo.applicationEventPublisher);
        System.out.println("applicationEventMulticaster: " + dependencySourceDemo.applicationEventMulticaster);

        applicationContext.close();

    }
}
