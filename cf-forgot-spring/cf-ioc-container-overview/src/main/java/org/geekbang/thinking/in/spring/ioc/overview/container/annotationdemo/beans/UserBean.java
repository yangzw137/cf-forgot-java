package org.geekbang.thinking.in.spring.ioc.overview.container.annotationdemo.beans;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cf
 * @date 2020/8/22
 */
//@Configuration
public class UserBean {

    @Bean
    public User createDefUser() {
        return new User(){
            {
                setId(1L);
                setName("coding life");
            }
        };
    }

}
