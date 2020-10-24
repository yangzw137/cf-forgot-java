package org.cf.forgot.jdk.myservlet.http.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.cf.forgot.commons.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description: todo
 * <p>
 *
 *
 *
 * @date 2020/10/20
 * @since 1.0.8
 */
public class MiniHttpServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MiniHttpServer.class);

    private volatile static MiniHttpServer instance;

    public static void main(String[] args) {
        MiniHttpServer.createHttpServer();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("end...");
    }

    public static MiniHttpServer createHttpServer() {
        if (instance == null) {
            instance = new MiniHttpServer();
        }
        return instance;
    }

    private MiniHttpServer() {
        startServer();
    }

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20,
            6000, TimeUnit.SECONDS,
            new LinkedBlockingQueue(1000),
            new NamedThreadFactory("traffic"),
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    throw new RejectedExecutionException("Traffic mirror thread pool has bean exhausted.");
                }
            });

    private void startServer() {
        LOGGER.info("init http server");
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8012), 0);
            httpServer.createContext("/pfinder/traffic/common", new CommonHandler());

            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class CommonHandler implements HttpHandler {

        @Override
        public void handle(final HttpExchange httpExchange) throws IOException {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("httpExchange: " + httpExchange);
                    InputStream inputStream = httpExchange.getRequestBody();
                    try {
                        final String queryString = IOUtils.toString(inputStream);
                        Map<String, String> queryStringMap = formData(queryString, "UTF-8");
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

    private Map<String, String> formData(final String queryString, final String charset) {
        Map<String, String> resultMap = new HashMap<String, String>();
        if (StringUtils.isEmpty(queryString)) {
            return resultMap;
        }
        final String[] strs = queryString.trim().split("&");
        for (final String s : strs) {
            final String[] kv = s.split("=");
            if (kv.length < 2) {
                continue;
            }
            try {
                final String k = URLDecoder.decode(kv[0], charset);
                final String v = URLDecoder.decode(kv[1], charset);
                resultMap.put(k, v);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }
}

