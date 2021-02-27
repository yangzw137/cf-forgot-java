package org.cf.forgot.jdk.myservlet.http.server.filters;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Description: todo
 * <p>
 * @date 2020/10/26
 * @since todo
 */
public class DemoFilter extends Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoFilter.class);

    @Override
    public void doFilter(HttpExchange httpExchange, Chain chain) throws IOException {
        LOGGER.info("httpExchange: {}, chain: {}", httpExchange, chain);
        chain.doFilter(httpExchange);
    }

    @Override
    public String description() {
        return "demoFilter";
    }
}
