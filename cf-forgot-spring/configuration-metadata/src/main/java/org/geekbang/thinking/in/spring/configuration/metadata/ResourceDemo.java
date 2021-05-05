package org.geekbang.thinking.in.spring.configuration.metadata;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/5
 * @since todo
 */
public class ResourceDemo {

    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("/");

        System.out.printf("classLoader: %s\n", classPathResource.getClassLoader());
        System.out.printf("classPathResource.getURL(): %s\n", classPathResource.getURL());


    }
}
