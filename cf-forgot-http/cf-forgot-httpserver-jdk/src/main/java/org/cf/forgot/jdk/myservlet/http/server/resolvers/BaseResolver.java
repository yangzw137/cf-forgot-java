package org.cf.forgot.jdk.myservlet.http.server.resolvers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.cf.forgot.jdk.myservlet.http.server.HttpRequest;
import org.cf.forgot.jdk.myservlet.http.server.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Description: todo
 * <p>
 * @date 2020/10/26
 * @since todo
 */
public abstract class BaseResolver implements HttpHandler, HttpResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseResolver.class);

    @Override
    public void handle(final HttpExchange httpExchange) throws IOException {
        doService(conver2Request(httpExchange), convert2Response(httpExchange));
    }

    private HttpResponse convert2Response(final HttpExchange httpExchange) {
        return new HttpResponse(httpExchange);
    }

    private HttpRequest conver2Request(final HttpExchange httpExchange) {
        return new HttpRequest(httpExchange);
    }

}
