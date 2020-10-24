package org.cf.forgot.jdk.myservlet.http.server;

import com.sun.net.httpserver.HttpHandler;
import java.util.Set;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/10/23
 * @since todo
 */
public class HttpContextDescriptor {
    private String url;
    private String handlerClazz;
    private Set<String> supportMethods;
    private HttpHandler httpHandler;

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

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHandlerClazz() {
        return handlerClazz;
    }

    public void setHandlerClazz(String handlerClazz) {
        this.handlerClazz = handlerClazz;
        try {
            httpHandler = (HttpHandler) Class.forName(handlerClazz).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HttpHandler getHttpHandler() {
        return httpHandler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpContextDescriptor that = (HttpContextDescriptor) o;
        if (!(url == null ? that.url == null : url.equals(that.url))) return false;
        if (!(handlerClazz == null ? that.handlerClazz == null : handlerClazz.equals(that.handlerClazz))) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = url == null ? 0 : url.hashCode();
        result = result * 31 + (handlerClazz == null ? 0 : handlerClazz.hashCode());
        return result;
    }
}
