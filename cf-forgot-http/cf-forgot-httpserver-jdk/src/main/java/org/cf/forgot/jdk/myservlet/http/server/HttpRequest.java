package org.cf.forgot.jdk.myservlet.http.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.cf.forgot.jdk.myservlet.http.Cookie;
import org.cf.forgot.jdk.myservlet.http.server.utils.HttpExchangeConvertor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: todo
 * <p>
 * @date 2020/10/26
 * @since todo
 */
public class HttpRequest {

    private final HttpExchange httpExchange;
    private String method;
    private Headers headers;
    private String queryString;
    private String requestURI;
    private String requestURL;
    private String contentType;
    private InputStream inputStream;


    public HttpRequest(final HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        init();
    }

    private void init() {
        //method
        method = HttpExchangeConvertor.toMethod(httpExchange);
        //header
        headers = HttpExchangeConvertor.toHeaders(httpExchange);
        //body
        inputStream = HttpExchangeConvertor.toInputStream(httpExchange);
    }

    /**
     * Returns the name of the HTTP method with which this request was made, for
     * example, GET, POST, or PUT. Same as the value of the CGI variable
     * REQUEST_METHOD.
     *
     * @return a <code>String</code> specifying the name of the method with
     *         which this request was made
     */
    public String getMethod() {
        return this.method;
    }

    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * Returns the value of the specified request header as a
     * <code>String</code>. If the request did not include a header of the
     * specified name, this method returns <code>null</code>. If there are
     * multiple headers with the same name, this method returns the first head
     * in the request. The header name is case insensitive. You can use this
     * method with any request header.
     *
     * @param name
     *            a <code>String</code> specifying the header name
     * @return a <code>String</code> containing the value of the requested
     *         header, or <code>null</code> if the request does not have a
     *         header of that name
     */
    public String getHeader(String name) {
        return this.headers.getFirst(name);
    }

    public Headers getHeaders() {
        return this.headers;
    }

    public void setHeaders(final Headers headers) {
        if (this.headers == null) {
            this.headers = new Headers();
        }
        this.headers.clear();
        this.headers.putAll(headers);
    }

    /**
     * Returns the query string that is contained in the request URL after the
     * path. This method returns <code>null</code> if the URL does not have a
     * query string. Same as the value of the CGI variable QUERY_STRING.
     *
     * @return a <code>String</code> containing the query string or
     *         <code>null</code> if the URL contains no query string. The value
     *         is not decoded by the container.
     */
    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    /**
     * Returns the part of this request's URL from the protocol name up to the
     * query string in the first line of the HTTP request. The web container
     * does not decode this String. For example:
     * <table summary="Examples of Returned Values">
     * <tr align=left>
     * <th>First line of HTTP request</th>
     * <th>Returned Value</th>
     * <tr>
     * <td>POST /some/path.html HTTP/1.1
     * <td>
     * <td>/some/path.html
     * <tr>
     * <td>GET http://foo.bar/a.html HTTP/1.0
     * <td>
     * <td>/a.html
     * <tr>
     * <td>HEAD /xyz?a=b HTTP/1.1
     * <td>
     * <td>/xyz
     * </table>
     * <p>
     * To reconstruct an URL with a scheme and host, use
     * {@link #getRequestURL}.
     *
     * @return a <code>String</code> containing the part of the URL from the
     *         protocol name up to the query string
     * @see #getRequestURL
     */
    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    /**
     * Reconstructs the URL the client used to make the request. The returned
     * URL contains a protocol, server name, port number, and server path, but
     * it does not include query string parameters.
     * <p>
     * Because this method returns a <code>StringBuffer</code>, not a string,
     * you can modify the URL easily, for example, to append query parameters.
     * <p>
     * This method is useful for creating redirect messages and for reporting
     * errors.
     *
     * @return a <code>StringBuffer</code> object containing the reconstructed
     *         URL
     */
    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL() {
        this.requestURL = requestURL;
    }

    /**
     * Returns the MIME type of the body of the request, or <code>null</code> if
     * the type is not known. For HTTP servlets, same as the value of the CGI
     * variable CONTENT_TYPE.
     *
     * @return a <code>String</code> containing the name of the MIME type of the
     *         request, or null if the type is not known
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Retrieves the body of the request as binary data using a
     * {@link ServletInputStream}. Either this method or {@link #getReader} may
     * be called to read the body, not both.
     *
     * @return a {@link ServletInputStream} object containing the body of the
     *         request
     * @exception IllegalStateException
     *                if the {@link #getReader} method has already been called
     *                for this request
     * @exception IOException
     *                if an input or output exception occurred
     */
    public InputStream getInputStream() throws IOException {
        return this.inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
