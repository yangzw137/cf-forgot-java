package org.geekbang.thinking.in.spring.ioc.dependency.scope;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/14
 * @since todo
 */
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.NAME)
    public User user() {
        return createUser();
    }

    private User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ThreadLocalScopeDemo.class);

        context.getBeanFactory().registerScope(ThreadLocalScope.NAME, new ThreadLocalScope());

        context.refresh();

        startOneThread(context);
        startOneThread(context);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.close();
    }

    private static void startOneThread(AnnotationConfigApplicationContext applicationContext) {
        new Thread(() -> {
            lookup(applicationContext);
        }).start();
    }

    private static void lookup(AnnotationConfigApplicationContext applicationContext) {

        for (int i = 0; i < 3; i++) {
            User user = applicationContext.getBean(User.class);
            System.out.println("user.id: " + user.getId());
        }
    }
}
