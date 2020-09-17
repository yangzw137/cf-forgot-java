package org.geekbang.thinking.in.spring.depency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Description:
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/9/16
 * @since
 */
public class AutoWiringByConstructorDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String location = "classpath:/META-INF/autowiring-dependency-constructor-injection.xml";
        reader.loadBeanDefinitions(location);

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println("userHolder: " + userHolder);
    }
}
