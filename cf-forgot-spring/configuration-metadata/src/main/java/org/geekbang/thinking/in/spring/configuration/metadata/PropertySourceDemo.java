package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/4
 * @since todo
 */
@PropertySource("classpath:/META-INF/users-bean-definitions.properties")
public class PropertySourceDemo {

    @Bean
    public User user(@Value("${user.id}") long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PropertySourceDemo.class);
        Map<String, Object> psMap = new HashMap<>();
        psMap.put("user.name", "codinglife");
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("first-ps", psMap);
        context.getEnvironment().getPropertySources().addFirst(propertySource);
        context.refresh();

        for (Map.Entry<String, User> entry : context.getBeansOfType(User.class).entrySet()) {
            System.out.printf("user entry, name:[%s], value: [%s]\n", entry.getKey(), entry.getValue());
        }
        System.out.println("propertySources: " + context.getEnvironment().getPropertySources());

        context.close();
    }
}
