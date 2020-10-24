package org.cf.forgot.jdk.myservlet.http.server.test;

import org.cf.forgot.jdk.myservlet.http.server.Container;
import org.cf.forgot.jdk.myservlet.http.server.HttpContextDescriptor;

import java.io.IOException;

/**
 * Description: todo
 * <p>
 *
 *
 *
 * @date 2020/10/24
 * @since todo
 */
public class AppMain {

    public static void main(String[] args) throws InterruptedException {
        HttpContextDescriptor httpContextDescriptorCommon = new HttpContextDescriptor();
        httpContextDescriptorCommon.setUrl("/common/demo");
        httpContextDescriptorCommon.setHandlerClazz("org.cf.forgot.jdk.myservlet.http.server.handlers.DemoHandler");

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
