package org.geekbang.thinking.in.spring.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * @date 2020/9/13
 * @since
 */
public class AnnotationDependencyFieldInjectionDemo {
    @Autowired
    private UserHolder userHolder1;
    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);
        applicationContext.refresh();

        AnnotationDependencyFieldInjectionDemo demo =
                applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);
        System.out.println("userHolder: " + demo.userHolder1);
        System.out.println("userHolder: " + demo.userHolder2);
        System.out.println(demo.userHolder1 == demo.userHolder2);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }
}
