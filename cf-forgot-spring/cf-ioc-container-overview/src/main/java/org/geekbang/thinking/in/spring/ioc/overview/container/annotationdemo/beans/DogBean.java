package org.geekbang.thinking.in.spring.ioc.overview.container.annotationdemo.beans;

import org.geekbang.thinking.in.spring.ioc.overview.domain.Dog;
import org.springframework.context.annotation.Bean;

/**
 * @author cf
 * @date 2020/8/22
 */
public class DogBean {

    @Bean
    public Dog createDog() {
        return new Dog() {
            {
                setId(100L);
                setColor(20);
            }
        };
    }
}
