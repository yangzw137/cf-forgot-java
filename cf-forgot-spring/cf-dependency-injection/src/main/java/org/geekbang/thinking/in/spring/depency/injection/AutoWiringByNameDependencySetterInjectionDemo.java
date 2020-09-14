package org.geekbang.thinking.in.spring.depency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Description:
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/9/13
 * @since
 */
public class AutoWiringByNameDependencySetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String location = "classpath:/META-INF/autowiring-dependency-setter-injection.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println("userHolder: " + userHolder);
    }
}
