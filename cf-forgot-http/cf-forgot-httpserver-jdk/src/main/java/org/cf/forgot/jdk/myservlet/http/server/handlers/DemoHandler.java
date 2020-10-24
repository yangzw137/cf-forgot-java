package org.cf.forgot.jdk.myservlet.http.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;
import org.cf.forgot.jdk.myservlet.http.server.Container;
import org.cf.forgot.jdk.myservlet.http.server.utils.QueryURIUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/10/24
 * @since todo
 */
public class DemoHandler implements HttpHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoHandler.class);

    @Override
    public void handle(final HttpExchange httpExchange) throws IOException {
        Container.getThreadPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("httpExchange: " + httpExchange);
                InputStream inputStream = httpExchange.getRequestBody();
                try {
                    final String queryString = IOUtils.toString(inputStream);
                    Map<String, String> queryStringMap = QueryURIUtil.formData(queryString, "UTF-8");
                    LOGGER.info("queryStringMap: {}", queryStringMap);
                    httpExchange.sendResponseHeaders(200, 0);
                    OutputStream outputStream = httpExchange.getResponseBody();

                    final String responseString = "my http server: " + queryString + "; " + new Date();
                    outputStream.write(responseString.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    LOGGER.info("response body: {}", responseString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
