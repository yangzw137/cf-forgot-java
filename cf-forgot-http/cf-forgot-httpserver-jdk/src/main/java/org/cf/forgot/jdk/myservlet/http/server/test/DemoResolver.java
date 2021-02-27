package org.cf.forgot.jdk.myservlet.http.server.test;

import org.apache.commons.io.IOUtils;
import org.cf.forgot.jdk.myservlet.http.server.HttpRequest;
import org.cf.forgot.jdk.myservlet.http.server.HttpResponse;
import org.cf.forgot.jdk.myservlet.http.server.resolvers.BaseResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Description: todo
 * <p>
 * @date 2020/10/26
 * @since todo
 */
public class DemoResolver extends BaseResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoResolver.class);

    @Override
    public void doService(HttpRequest request, HttpResponse response) {
        LOGGER.info("request: {}", request);
        LOGGER.info("request contentType: {}", request.getContentType());
        LOGGER.info("request headers: {}", request.getHeaders());
        LOGGER.info("request method: {}", request.getMethod());
        LOGGER.info("request queryString: {}", request.getQueryString());
        LOGGER.info("request uri: {}", request.getRequestURI());
        LOGGER.info("request url: {}", request.getRequestURL());
        String responseStr = "no response";
        try {
//            String body = IOUtils.toString(request.getInputStream());
//            LOGGER.info("request body: {}", body);
            response.setStatus(200);
            OutputStream outputStream = response.getOutputStream();
            responseStr = "Demo resolver response body, for request: ";
            outputStream.write(responseStr.getBytes());
            outputStream.write(IOUtils.toByteArray(request.getInputStream()));
//            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("response: {}", responseStr);
    }
}
