package org.geekbang.thinking.in.spring.dependency.injection;

import org.geekbang.thinking.in.spring.dependency.injection.annotation.UserGroup;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;

/**
 * Description:
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/10/11
 * @since
 */
@Configuration
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User superUser;

    @Autowired
    @Qualifier("user")
    private User user;

    // 整体应用上下文存在 4 个 User 类型的 Bean:
    // superUser
    // user
    // user1 -> @Qualifier
    // user2 -> @Qualifier

    @Autowired
    private Collection<User> allUsers; // 2 Beans = user + superUser

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @UserGroup
    private List<User> gourUsers;

    @Autowired(required = false)
    @Qualifier("BeiJing")
    private Collection<User> qualifierUsersBeiJing;

    @Autowired(required = false)
    @UserGroup("Hangzhou")
    private List<User> gourUsersHangZhou;

    @Bean
    @Qualifier
    public static User user2() {
        return createUser(2);
    }

    @Bean
    @Qualifier
    public static User user3() {
        return createUser(3);
    }

    @Bean
    @UserGroup
    public static User user4() {
        return createUser(4);
    }

    @Bean
    @UserGroup
    public static User user5() {
        return createUser(5);
    }

    @Bean
    @Qualifier("BeiJing")
    public static User user6() {
        return createUser(6);
    }

    @Bean
    @UserGroup("Hangzhou")
    public static User user7() {
        return createUser(7);
    }

    public static User createUser(long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.printf("superUser: %s\n", demo.superUser.toSampleString());
        System.out.printf("user: %s\n", demo.user.toSampleString());
        System.out.printf("all users: %s\n", User.userCollection2String(demo.allUsers));
        System.out.printf("qualifer users: %s\n", User.userCollection2String(demo.qualifierUsers));
        System.out.printf("group users: %s\n", User.userCollection2String(demo.gourUsers));
        System.out.printf("qualifierUsersBeiJing users: %s\n", User.userCollection2String(demo.qualifierUsersBeiJing));
        System.out.printf("gourUsersHangZhou users: %s\n", User.userCollection2String(demo.gourUsersHangZhou));

        applicationContext.close();
    }
}
