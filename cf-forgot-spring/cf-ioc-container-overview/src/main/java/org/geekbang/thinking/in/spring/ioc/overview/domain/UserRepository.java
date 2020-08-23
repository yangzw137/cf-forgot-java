package org.geekbang.thinking.in.spring.ioc.overview.domain;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;

/**
 * @author cf
 * @date 2020/8/15
 */
public class UserRepository {

    private Collection<User> users;//注入：自定义 bean

    private BeanFactory beanFactory;//注入：非 bean 对象（依赖）

    private ObjectFactory<User> objectFactory;//

    private ObjectFactory<ApplicationContext> applicationContextObjectFactory;

    private ClassPathXmlApplicationContext applicationContext;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<User> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<User> objectFactory) {
        this.objectFactory = objectFactory;
    }

    public ObjectFactory<ApplicationContext> getApplicationContextObjectFactory() {
        return applicationContextObjectFactory;
    }

    public void setApplicationContextObjectFactory(ObjectFactory<ApplicationContext> applicationContextObjectFactory) {
        this.applicationContextObjectFactory = applicationContextObjectFactory;
    }

    public ClassPathXmlApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ClassPathXmlApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "userList=" + users +
                '}';
    }
}
