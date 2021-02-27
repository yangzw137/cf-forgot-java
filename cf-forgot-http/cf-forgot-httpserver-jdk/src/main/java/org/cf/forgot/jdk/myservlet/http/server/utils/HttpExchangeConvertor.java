package org.cf.forgot.jdk.myservlet.http.server.utils;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Description: todo
 * <p>
 * @date 2020/10/29
 * @since todo
 */
public class HttpExchangeConvertor {

    public static String toMethod(final HttpExchange httpExchange) {
        return httpExchange.getRequestMethod();
    }

    public static Headers toHeaders(final HttpExchange httpExchange) {
        //todo
        return httpExchange.getRequestHeaders();
    }

    public static InputStream toInputStream(final HttpExchange httpExchange) {
        return httpExchange.getRequestBody();
    }

    public static OutputStream toOutputStream(final HttpExchange httpExchange) {
        return httpExchange.getResponseBody();
    }


//    public static List<Cookie> toCookies(final HttpExchange httpExchange) {
//        //todo
//        List<String> list = httpExchange.getRequestHeaders().get("Cookie");
//        //domain
//        //name
//        //value
//        //path
//        //maxAge
//        //secure
//        return null;
//    }

}
