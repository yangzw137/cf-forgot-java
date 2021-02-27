package org.cf.forgot.jdk.myservlet.http.server.test;

import org.cf.forgot.jdk.myservlet.http.server.Container;
import org.cf.forgot.jdk.myservlet.http.server.HttpContextDescriptor;
import org.cf.forgot.jdk.myservlet.http.server.filters.DemoFilter;
import org.cf.forgot.jdk.myservlet.http.server.resolvers.BaseResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Description: todo
 * <p>
 *
 * @date 2020/10/24
 * @since todo
 */
public class AppMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppMain.class);

    public static void main(String[] args) throws InterruptedException {
        HttpContextDescriptor httpContextDescriptorCommon = new HttpContextDescriptor();
        httpContextDescriptorCommon.setUrl("/common/demo");
        httpContextDescriptorCommon.setResolver(new DemoResolver());
//        httpContextDescriptorCommon.setFilter(new DemoFilter());

        Container container = new Container("mini-http-container");
        container.setPort(8989);
        try {
            container.addHttpHandler(httpContextDescriptorCommon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            container.initContainer();
            container.startContainer();

            Thread.currentThread().join();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
