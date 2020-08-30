package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过别名查找bean
 * @author cf
 * @date 2020/8/24
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        String location = "classpath:/META-INF/dependency-lookup-alias.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(location);

        User cfUser = applicationContext.getBean("cfuser", User.class);
        User user = applicationContext.getBean("user", User.class);

        System.out.println("cfUser is user: " + (cfUser == user));
    }
}
