package org.geekbang.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;


/**
 * Description: 带编码的 {@link org.springframework.core.io.FileSystemResource} 实例
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/5
 * @since todo
 *
 * @see org.springframework.core.io.FileSystemResource
 * @see org.springframework.core.io.support.EncodedResource
 */
public class EncodedFileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        String currentFilePath = System.getProperty("user.dir") + "/cf-forgot-spring/resource/src/main/java/org/geekbang/thinking/in/spring/resource/EncodedFileSystemResourceDemo.java";
        System.out.printf("currentFilePath: %s\n", currentFilePath);

        Resource resource = new FileSystemResource(currentFilePath);

        EncodedResource encodedResource = new EncodedResource(resource);
        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
