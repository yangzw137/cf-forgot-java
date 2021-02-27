package org.cf.forgot.jdk.myservlet.http.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import org.cf.forgot.commons.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description: todo
 * <p>
 *
 * @date 2020/10/23
 * @since todo
 */
public class Container {
    private static final Logger LOGGER = LoggerFactory.getLogger(Container.class);

    private String containerName = "miniContainer.0.0.1";
    //default port: 9898
    private int port = 9898;
    private HttpServer httpServer;
    private Map<String, HttpContextDescriptor> contextDescriptorMap = new ConcurrentHashMap<>();

    public Container(String containerName) {
        this.containerName = containerName;
    }

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20,
            6000, TimeUnit.SECONDS,
            new LinkedBlockingQueue(1000),
            new NamedThreadFactory("minihttpserver"),
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    throw new RejectedExecutionException(" Mini http-container thread pool has bean exhausted.");
                }
            });

    public Container addHttpHandler(HttpContextDescriptor httpContextDescriptor) throws Exception {
        if (httpContextDescriptor == null || httpContextDescriptor.getUrl() == null) {
            throw new Exception("httpHandler or url can not be null");
        }
        if (contextDescriptorMap.containsKey(httpContextDescriptor.getUrl())) {
            throw new Exception("The url [" + httpContextDescriptor.getUrl() + "] has exist.");
        }
        contextDescriptorMap.put(httpContextDescriptor.getUrl(), httpContextDescriptor);
        return this;
    }

    public HttpContextDescriptor removeContextDes(String url) {
        //todo remove http handler
        return null;
    }

    public HttpContextDescriptor getContextDes(String url) {
        //todo
        return null;
    }

    public void initContainer() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        LOGGER.info("init mini http server start");
        httpServer.setExecutor(threadPoolExecutor);

        for (Map.Entry<String, HttpContextDescriptor> entry : contextDescriptorMap.entrySet()) {
            HttpContextDescriptor contextDescriptor = entry.getValue();
//            HttpContext httpContext = httpServer.createContext(entry.getKey(), entry.getValue().getHttpHandler());
            HttpContext httpContext = httpServer.createContext(entry.getKey(), entry.getValue().getResolver());
            if (entry.getValue().getFilter() != null) {
                httpContext.getFilters().add(entry.getValue().getFilter());
            }
        }
        LOGGER.info("init mini http server end");
    }

    public void startContainer() {
        httpServer.start();
        LOGGER.info("mini http server has been started, bind port: {}", port);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    public void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }
}
