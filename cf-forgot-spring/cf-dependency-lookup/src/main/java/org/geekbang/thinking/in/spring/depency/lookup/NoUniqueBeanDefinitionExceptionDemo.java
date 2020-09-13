package org.geekbang.thinking.in.spring.depency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Description:
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/9/13
 */
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        applicationContext.refresh();

        try {
            String bean = applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException ex) {
            System.err.printf("there have %d beans, type: %s, error message: %s %n", ex.getNumberOfBeansFound(),
                    String.class.getName(), ex.getMessage());
        }


        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "1";
    }

    @Bean
    public String bean2() {
        return "2";
    }

    @Bean
    public String bean3() {
        return "3";
    }

}
