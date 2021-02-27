package org.cf.forgot.jdk.classloader.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/1/17
 * @since todo
 */
public class JdkClassloader {

    private static final Logger LOG = LoggerFactory.getLogger(JdkClassloader.class);

    public static void main(String[] args) {
        LOG.info("String classloader: {}", String.class.getClassLoader());
        LOG.info("JDK classloader: {}", JdkClassloader.class.getClassLoader());

        ClassLoader classLoader = JdkClassloader.class.getClassLoader();
        while (classLoader != null) {
            LOG.info("classloader: {}", classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }
}
