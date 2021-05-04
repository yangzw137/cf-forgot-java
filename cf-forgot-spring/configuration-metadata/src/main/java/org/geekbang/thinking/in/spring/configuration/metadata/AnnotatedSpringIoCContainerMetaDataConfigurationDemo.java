package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/3
 * @since todo
 */
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@PropertySource("META-INF/users-bean-definitions.properties")
@Import(User.class)
public class AnnotatedSpringIoCContainerMetaDataConfigurationDemo {

    @Bean
    private User configedUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedSpringIoCContainerMetaDataConfigurationDemo.class);
        context.registerBean(User.class, null);
        context.refresh();
        Map<String, User> usersMap = context.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : usersMap.entrySet()) {
            System.out.printf("user bean name: [%s], content: %s \n", entry.getKey(), entry.getValue());
        }
        System.out.println("get configed user: " + context.getBean(AnnotatedSpringIoCContainerMetaDataConfigurationDemo.class).configedUser(1L,"1111"));

        System.out.println("bean size: " + usersMap.size());
        context.close();
    }
}
