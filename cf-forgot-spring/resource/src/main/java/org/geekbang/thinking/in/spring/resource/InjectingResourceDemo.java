package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;

/**
 * Description: 注入 {@link Resource} 对象示例.
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/5
 * @since todo
 *
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 */
public class InjectingResourceDemo {

    @Value("META-INF/default.properties")
    private Resource defaultPropertiesResource;
    @Value("classpath*:META-INF/*.properties")
    private Resource[] propertiesSources;
    @Value("${user.dir}")
    private String currentProjectRootPath;

    @PostConstruct
    public void init() throws IOException {
        System.out.println(ResourceUtil.getFileContent(defaultPropertiesResource, "UTF-8"));
        System.out.println("-----------------");
        Arrays.stream(propertiesSources).map(ResourceUtil::getFileContent).forEach(System.out::println);
        System.out.println("-----------------");
        System.out.println(currentProjectRootPath);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingResourceDemo.class);
        context.refresh();

        context.close();
    }
}
