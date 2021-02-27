package org.cf.forgot.jdk.myservlet.http.server;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpHandler;
import org.cf.forgot.jdk.myservlet.http.server.resolvers.BaseResolver;

import java.util.Set;

/**
 * Description: todo
 * <p>
 *
 *
 *
 * @date 2020/10/23
 * @since todo
 */
public class HttpContextDescriptor {
    private String url;
    private Set<String> supportMethods;
    private Filter filter;
    private BaseResolver resolver;

    public void addMethod(String method) {
        //todo lower/upper case
        supportMethods.add(method);
    }

    public boolean isSupportMethod(String method) {
        //todo lower/upper case
        return supportMethods.contains(method);
    }

    public String getUrl() {
        return url;
    }

    public HttpContextDescriptor setUrl(String url) {
        this.url = url;
        return this;
    }

    public BaseResolver getResolver() {
        return resolver;
    }

    public HttpContextDescriptor setResolver(BaseResolver resolver) {
        this.resolver = resolver;
        return this;
    }

    public Filter getFilter() {
        return filter;
    }

    public HttpContextDescriptor setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpContextDescriptor that = (HttpContextDescriptor) o;
        if (!(url == null ? that.url == null : url.equals(that.url))) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = url == null ? 0 : url.hashCode();
        return result;
    }
}
