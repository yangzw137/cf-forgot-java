package org.cf.forgot.jdk.myservlet.http.server;

import com.sun.net.httpserver.HttpExchange;
import org.cf.forgot.jdk.myservlet.http.server.utils.HttpExchangeConvertor;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Description: todo
 * <p>
 * @date 2020/10/26
 * @since todo
 */
public class HttpResponse {
    private HttpExchange httpExchange;
    private OutputStream outputStream;

    public HttpResponse(final HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        init(httpExchange);
    }

    private void init(final HttpExchange httpExchange) {
        setOutputStream(HttpExchangeConvertor.toOutputStream(httpExchange));
    }

    public OutputStream getOutputStream() throws IOException {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setStatus(int code) throws IOException {
        httpExchange.sendResponseHeaders(code, 0);
    }
}
