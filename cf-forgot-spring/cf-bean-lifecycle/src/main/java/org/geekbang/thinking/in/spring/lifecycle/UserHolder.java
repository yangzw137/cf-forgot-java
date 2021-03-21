package org.geekbang.thinking.in.spring.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/20
 * @since todo
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware,
        InitializingBean, SmartInitializingSingleton, DisposableBean {

    private final User user;
    private int number;
    private String description;

    private ClassLoader classLoader;
    private String beanName;
    private BeanFactory beanFactory;
    private Environment environment;

    public int getNumber() {
        return number;
    }

    @PostConstruct
    public void initPostConstruct() {
        System.out.println("initPostConstruct() before: " + this.description);
        this.description = "The user holder v4";
        System.out.println("initPostConstruct() after: " + this.description);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.description = "The user holder v5";
        System.out.println("afterPropertiesSet(): " + this.description);
    }

    public void init() {
        this.description = "The user holder v6";
        System.out.println("init(): " + this.description);
    }

    @PreDestroy
    public void preDestroy() {
        this.description = "The user holder V10";
        System.out.println("preDestroy(): " + this.description);
    }

    @Override
    public void destroy() throws Exception {
        this.description = "The user holder V11";
        System.out.println("Disposable.destroy(): " + this.description);
    }

    public void doDestroy() {
        this.description = "The user holder V12";
        System.out.println("doDestroy(): " + this.description);
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserHolder(User user) {
        this.user = user;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.description = "The user holder v8";
        System.out.println("afterSingletonsInstantiated(): " + this.description);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("The UserHolder is finalized.");
    }
}
