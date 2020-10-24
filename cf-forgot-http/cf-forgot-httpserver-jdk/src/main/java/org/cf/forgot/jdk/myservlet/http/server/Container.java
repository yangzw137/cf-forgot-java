package org.cf.forgot.jdk.myservlet.http.server;

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
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
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

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20,
            6000, TimeUnit.SECONDS,
            new LinkedBlockingQueue(1000),
            new NamedThreadFactory("traffic"),
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
        LOGGER.info("init http server");
        for (Map.Entry<String, HttpContextDescriptor> entry : contextDescriptorMap.entrySet()) {
            HttpContextDescriptor contextDescriptor = entry.getValue();
            httpServer.createContext(entry.getKey(), entry.getValue().getHttpHandler());
        }
    }

    public void startContainer() {
        httpServer.start();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    public static void setThreadPoolExecutor(ThreadPoolExecutor threadPoolExecutor) {
        Container.threadPoolExecutor = threadPoolExecutor;
    }
}
