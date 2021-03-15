package org.geekbang.thinking.in.spring.ioc.dependency.scope;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/2/28
 * @since todo
 */
public class BeanScopeDemo implements DisposableBean, BeanFactoryAware {

    @Bean
    private User singleton() {
        return createUser();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    private User prototype() {
        return createUser();
    }

    @Autowired
    @Qualifier("singleton")
    private User singoletonUser0;
    @Autowired
    @Qualifier("singleton")
    private User singoletonUser1;
    @Autowired
    @Qualifier("prototype")
    private User prototypeUser0;
    @Autowired
    @Qualifier("prototype")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototype")
    private User prototypeUser2;
    @Autowired
    private Map<String, User> userMap;

    private ConfigurableListableBeanFactory beanFactory;

    private User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                    return bean;
                }

                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("bean class [%s] name [%s] : postProcessAfterInitialization",
                            bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });

        applicationContext.refresh();

        lookupInjection(applicationContext);

        lookup(applicationContext);

        applicationContext.close();

    }

    private static void lookupInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);

        System.out.println("singoletonUser0: " + demo.singoletonUser0);
        System.out.println("singoletonUser1: " + demo.singoletonUser1);
        System.out.println("prototypeUser0: " + demo.prototypeUser0);
        System.out.println("prototypeUser1: " + demo.prototypeUser1);
        System.out.println("prototypeUser2: " + demo.prototypeUser2);

        System.out.println("userMap: " + demo.userMap);
    }

    private static void lookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            User singleton = applicationContext.getBean("singleton", User.class);
            System.out.println("singleton user.id: " + singleton.getId());

            User prototype = applicationContext.getBean("prototype", User.class);
            System.out.println("prototype user.id: " + prototype.getId());
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public void destroy() throws Exception {
        this.singoletonUser0.destroy();
        this.singoletonUser1.destroy();

        this.prototypeUser0.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();

        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            BeanDefinition bd = beanFactory.getBeanDefinition(entry.getKey());
            if (bd.isPrototype()) {
                entry.getValue().destroy();
            }
        }
    }
}
