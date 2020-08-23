package org.geekbang.thinking.in.spring.bean.definition;


import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author cf
 * @date 2020/8/22
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {

        //1、通过 BeanDefinitionBuilder 创建
        BeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(User.class)
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "coding life")
                .getBeanDefinition();
        System.out.println("通过 BeanDefinitionBuilder 创建， beanDefinition：" + beanDefinition);

        //2、通过 GenericBeanDefinition 创建
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues
                .add("id", 1L)
                .add("name", "coding life");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
        System.out.println("通过 GenericBeanDefinition 创建， beanDefinition：" + beanDefinition);
    }
}
