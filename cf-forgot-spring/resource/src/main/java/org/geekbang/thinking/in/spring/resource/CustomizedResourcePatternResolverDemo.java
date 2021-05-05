package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.util.ResourceUtil;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 * Description: 自定义资源统配器示例 {@link ResourcePatternResolver}
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/5
 *
 * @see ResourcePatternResolver
 * @see PathMatchingResourcePatternResolver
 * @see PathMatcher
 * @since todo
 */
public class CustomizedResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
        String currentFilePath = "/" + System.getProperty("user.dir") + "/cf-forgot-spring/resource/src/main/java/org/geekbang/thinking/in/spring/resource/";
        currentFilePath +="*.java";
        System.out.printf("currentFilePath: %s\n", currentFilePath);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        resolver.setPathMatcher(new JavaFileMatcher());
        Resource[] resources = resolver.getResources(currentFilePath);
        Arrays.stream(resources).map(ResourceUtil::getFileContent)
                .forEach(System.out::println);
    }

    private static class JavaFileMatcher implements PathMatcher {

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
