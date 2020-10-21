package org.geekbang.thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description:
 * <p>
 * @date 2020/9/16
 * @since
 */
public class ApiDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApiDependencyConstructorInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);

        BeanDefinition beanDefinition = createBeanDefinition();
        applicationContext.registerBeanDefinition("userHolder", beanDefinition);

        applicationContext.refresh();

        UserHolder userHolder = (UserHolder) applicationContext.getBean("userHolder");
        System.out.println("userHolder: " + userHolder);


        applicationContext.close();
    }

    public static BeanDefinition createBeanDefinition() {
        return BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class)
                .addConstructorArgReference("superUser").getBeanDefinition();
    }
}
