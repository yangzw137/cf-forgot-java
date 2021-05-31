package org.geekbang.thinking.in.spring.i18n;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/30
 * @since todo
 */
@EnableAutoConfiguration
public class CustomizeMessageSourceBeanDemo {

    @Bean
    public MessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext =
                //primary configuration source(class)
                new SpringApplicationBuilder(CustomizeMessageSourceBeanDemo.class)
                        .web(WebApplicationType.NONE).run(args);

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if (beanFactory.containsBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)) {
            System.out.println(beanFactory.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME));
            BeanDefinition bd = beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME);
            System.out.println(bd);

            MessageSource ms = beanFactory.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME,
                    MessageSource.class);
            System.out.println(ms);
        }

        //close application context.
        applicationContext.close();

    }
}
