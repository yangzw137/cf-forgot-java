package org.cf.forgot.jdk.myservlet.http.server.resolvers;

import org.cf.forgot.jdk.myservlet.http.server.HttpRequest;
import org.cf.forgot.jdk.myservlet.http.server.HttpResponse;

/**
 * Description: todo
 * <p>
 * @date 2020/10/26
 * @since todo
 */
public interface HttpResolver {
    void doService(HttpRequest request, HttpResponse response);
}
