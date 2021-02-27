package org.geekbang.thinking.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/2/27
 * @since todo
 */
@Configuration
@PropertySource(value = {"classpath:META-INF/default.properties"}, encoding = "utf-8")
public class ExternalDependencySourceDemo {

    @Value("${user.id: -1}")
    private int id;
    @Value("${usr.name}")
    private String name;
    @Value("${user.resource}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalDependencySourceDemo.class);
        applicationContext.refresh();

        ExternalDependencySourceDemo demo = applicationContext.getBean(ExternalDependencySourceDemo.class);
        System.out.println("user.id: " + demo.id);
        System.out.println("user.name: " + demo.name);
        System.out.println("user.resource: " + demo.resource);
        applicationContext.close();
    }
}
