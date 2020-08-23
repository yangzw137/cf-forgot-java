package org.geekbang.thinking.in.spring.ioc.overview.container.annotationdemo;

import org.geekbang.thinking.in.spring.ioc.overview.container.annotationdemo.beans.DogBean;
import org.geekbang.thinking.in.spring.ioc.overview.container.annotationdemo.beans.UserBean;
import org.geekbang.thinking.in.spring.ioc.overview.domain.Dog;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cf
 * @date 2020/8/22
 */
public class AnnotationApplicationContextAsIocContainer {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        applicationContext.register(Launcher.class);
//        applicationContext.refresh();

//        AnnotationConfigApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(AnnotationApplicationContextAsIocContainer.class);

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();

        applicationContext.register(UserBean.class);
        applicationContext.register(DogBean.class);
        applicationContext.refresh();//刷新才能看到，不能刷新两次
        //--
        AnnotationApplicationContextAsIocContainer contextAsIocContainer =
                new AnnotationApplicationContextAsIocContainer();
        contextAsIocContainer.lookupByType(applicationContext);
        //关闭
        applicationContext.close();
    }



    public void lookupByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            System.out.println("lookupByType, User: " + listableBeanFactory.getBeansOfType(User.class));
            System.out.println("lookupByType, Dog: " + listableBeanFactory.getBeansOfType(Dog.class));
        }
    }
}
