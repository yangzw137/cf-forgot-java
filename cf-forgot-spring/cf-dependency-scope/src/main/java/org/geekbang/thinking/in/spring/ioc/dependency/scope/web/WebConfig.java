package org.geekbang.thinking.in.spring.ioc.dependency.scope.web;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/14
 * @since todo
 */
@Configuration
@EnableWebMvc
public class WebConfig {

    @Bean
//    @Scope(WebApplicationContext.SCOPE_REQUEST)
    @RequestScope
    public User user() {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setName("zhangsan");
        return user;
    }
}
