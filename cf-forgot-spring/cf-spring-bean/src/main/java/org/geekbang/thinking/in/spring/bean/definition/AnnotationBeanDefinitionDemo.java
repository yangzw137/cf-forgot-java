package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * bean定义
 * 一、xml方式（省略）
 *
 * 二、注解方式
 * 1、 @Import 方式
 * 2、 @Component 方式
 * 3、@Bean 方式
 *
 * 三、Api方式
 * 1、命名方式
 * 2、非命名方式
 * 3、Application.register 方式
 *
 * @author cf
 * @date 2020/8/29
 */
//1 导入类的方式
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        //2 通过api 注册类的方式定义bean
//        applicationContext.register(AnnotationBeanDefinitionDemo.Config.class);
        applicationContext.refresh();

        //api + 命名方式注册/声明 bean
        registerBeanDefinition(applicationContext, User.class, "cf-api-bean");
        //api + 非命名方式注册/声明 bean
        registerBeanDefinition(applicationContext, User.class, null);

        System.out.println("AnnotationBeanDefinitionDemo beans: " + applicationContext.getBeansOfType(AnnotationBeanDefinitionDemo.class));
        System.out.println("Config beans: " + applicationContext.getBeansOfType(Config.class));
        System.out.println("User beans: " + applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry, Class<?> clazz, String beanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        //简略：直接当成User处理
        builder.addPropertyValue("id", 1L).addPropertyValue("name", "coding life");

        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }

    // @Component方式定义 bean
    @Component
    class Config {

        @Bean// @Bean方式定义bean
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("coding life");
            return user;
        }
    }
}
