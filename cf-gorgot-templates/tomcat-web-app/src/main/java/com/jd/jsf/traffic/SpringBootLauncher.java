package com.jd.jsf.traffic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/11
 * @since todo
 */
@SpringBootApplication(scanBasePackages = {"com.jd.jsf.traffic"})
@ImportResource({"classpath:/spring-xxx.xml"})
@Configuration
public class SpringBootLauncher implements ApplicationContextAware {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootLauncher.class);

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        System.setProperty("spring.config.location", "classpath:/config/");
        System.setProperty("spring.config.name", "server");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootLauncher.class, args);

        // 启动本地服务，然后hold住本地服务
        synchronized (SpringBootLauncher.class) {
            while (true) {
                try {
                    SpringBootLauncher.class.wait();
                } catch (InterruptedException e) {

                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
