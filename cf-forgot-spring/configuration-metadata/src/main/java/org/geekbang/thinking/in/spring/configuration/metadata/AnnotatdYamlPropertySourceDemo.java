package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.geekbang.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/4
 * @since todo
 */
@PropertySource(name = "yamlPropertySource", value = "META-INF/user.yaml", factory = YamlPropertySourceFactory.class)
public class AnnotatdYamlPropertySourceDemo {

    @Bean
    public User user(@Value("${user.id}") long id,
                     @Value("${user.name}") String name, @Value("${user.city}") City city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatdYamlPropertySourceDemo.class);

        context.refresh();

        System.out.println("user: " + context.getBean("user"));

        context.close();
    }
}
