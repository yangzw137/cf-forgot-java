package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cf
 * @date 2020/8/30
 */
public class HierarchicalBeanFactoryDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();

        //HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory localBeanFactory = applicationContext.getBeanFactory();

        ConfigurableListableBeanFactory parentBeanFactory = createParentBeanFactory();

        localBeanFactory.setParentBeanFactory(parentBeanFactory);

        displayContainLocalBean(localBeanFactory, "user");
        displayContainLocalBean(parentBeanFactory, "user");

        displayContainBean(localBeanFactory, "user");
        displayContainBean(parentBeanFactory, "user");

        applicationContext.close();
    }

    public static void displayContainBean(HierarchicalBeanFactory beanFactory, String name) {
        System.out.printf("beanFactory:[%s] is contain local beanName:[%s]: %s \n", beanFactory, name,
                containsBean(beanFactory, name));
    }

    public static void displayContainLocalBean(HierarchicalBeanFactory beanFactory, String name) {
        System.out.printf("beanFactory:[%s] is contain local beanName:[%s]: %s \n", beanFactory, name,
                containsLocalBean(beanFactory, name));
    }

    public static boolean containsBean(HierarchicalBeanFactory beanFactory, String name) {
        BeanFactory parent = beanFactory.getParentBeanFactory();
        if (parent instanceof HierarchicalBeanFactory) {
            if (containsBean((HierarchicalBeanFactory) parent, name)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(name);
    }

    public static boolean containsLocalBean(HierarchicalBeanFactory beanFactory, String name) {
        return beanFactory.containsLocalBean(name);
    }

    public static ConfigurableListableBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }
}
