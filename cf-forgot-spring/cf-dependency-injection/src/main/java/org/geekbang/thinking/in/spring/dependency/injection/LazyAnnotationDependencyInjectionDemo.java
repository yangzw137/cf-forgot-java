package org.geekbang.thinking.in.spring.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * <p>
 * @date 2020/10/11
 * @since
 */
@Configuration
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;
    @Autowired
    private ObjectProvider<User> objectProvider;
    @Autowired
    private ObjectFactory<User> objectFactory;

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.printf("user: %s\n", demo.user.toSampleString());
        System.out.printf("objectProvider getObject: %s\n", demo.objectProvider.getObject().toSampleString());
        System.out.printf("objectFactory getObject: %s\n", demo.objectFactory.getObject().toSampleString());

        demo.objectProvider.forEach(u -> {
            System.out.println(u.toSampleString());
        });

        demo.objectProvider.forEach(System.out::println);

        demo.objectProvider.stream().forEach(System.out::println);

        applicationContext.close();
    }
}
