package org.geekbang.thinking.in.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * Description: 注入 ResourceLoader {@link ResourceLoader} 示例.
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/5
 * @since todo
 *
 * @see ResourceLoader
 * @see Resource
 * @see AbstractApplicationContext
 */
public class InjectionResourceLoaderDemo implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;//方法1

    @Autowired
    private ResourceLoader autowiredResourceLoader;//方法2
    @Autowired
    private AbstractApplicationContext applicationContext;//方法3

    @PostConstruct
    private void init() {
        System.out.printf("resourceLoader == autowiredResourceLoader: [%b]\n", resourceLoader == autowiredResourceLoader);
        System.out.printf("resourceLoader == applicationContext: [%b]\n", resourceLoader == applicationContext);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectionResourceLoaderDemo.class);
        context.refresh();
        context.close();
    }
}
