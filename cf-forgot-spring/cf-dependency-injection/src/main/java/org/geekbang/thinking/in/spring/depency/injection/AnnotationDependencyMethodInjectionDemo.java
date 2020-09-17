package org.geekbang.thinking.in.spring.depency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/9/13
 * @since
 */
public class AnnotationDependencyMethodInjectionDemo {
    private UserHolder userHolder1;
    private UserHolder userHolder2;

    @Autowired
    private void init1(UserHolder userHolder) {
        this.userHolder1 = userHolder;
    }

    @Resource
    private void init2(UserHolder userHolder) {
        this.userHolder2 = userHolder;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(location);

        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);
        applicationContext.refresh();

        AnnotationDependencyMethodInjectionDemo demo =
                applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);
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
