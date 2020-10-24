package org.cf.forgot.jdk.myservlet.http;

import org.cf.forgot.jdk.myservlet.http.exception.ResolverException;

import java.io.IOException;

/**
 * Description: todo
 * <p>
 *
 *
 *
 * @date 2020/10/22
 * @since todo
 */
public interface RequestDispatcher {

    public static final String ERROR_EXCEPTION = "javax.servlet.error.exception";
    public static final String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME = "javax.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";
    public static final String FORWARD_CONTEXT_PATH = "javax.servlet.forward.context_path";
    public static final String FORWARD_PATH_INFO = "javax.servlet.forward.path_info";
    public static final String FORWARD_QUERY_STRING = "javax.servlet.forward.query_string";
    public static final String FORWARD_REQUEST_URI = "javax.servlet.forward.request_uri";
    public static final String FORWARD_SERVLET_PATH = "javax.servlet.forward.servlet_path";
    public static final String INCLUDE_CONTEXT_PATH = "javax.servlet.include.context_path";
    public static final String INCLUDE_PATH_INFO = "javax.servlet.include.path_info";
    public static final String INCLUDE_QUERY_STRING = "javax.servlet.include.query_string";
    public static final String INCLUDE_REQUEST_URI = "javax.servlet.include.request_uri";
    public static final String INCLUDE_SERVLET_PATH = "javax.servlet.include.servlet_path";

    /**
     * Forwards a request from a servlet to another resource (servlet, JSP file,
     * or HTML file) on the server. This method allows one servlet to do
     * preliminary processing of a request and another resource to generate the
     * response.
     *
     * <p>
     * For a <code>RequestDispatcher</code> obtained via
     * <code>getRequestDispatcher()</code>, the <code>HttpRequest</code>
     * object has its path elements and parameters adjusted to match the path of
     * the target resource.
     *
     * <p>
     * <code>forward</code> should be called before the response has been
     * committed to the client (before response body output has been flushed).
     * If the response already has been committed, this method throws an
     * <code>IllegalStateException</code>. Uncommitted output in the response
     * buffer is automatically cleared before the forward.
     *
     * <p>
     * The request and response parameters must be either the same objects as
     * were passed to the calling servlet's service method or be subclasses of
     * the {@link HttpRequestWrapper} or {@link ResolverResponseWrapper}
     * classes that wrap them.
     *
     *
     * @param request
     *            a {@link HttpResolverRequest} object that represents the request
     *            the client makes of the servlet
     *
     * @param response
     *            a {@link ResolverResponse} object that represents the response
     *            the servlet returns to the client
     *
     * @exception ResolverException
     *                if the target resource throws this exception
     *
     * @exception IOException
     *                if the target resource throws this exception
     *
     * @exception IllegalStateException
     *                if the response was already committed
     */
    public void forward(HttpResolverRequest request, ResolverResponse response)
            throws ResolverException, IOException;

    /**
     * Includes the content of a resource (servlet, JSP page, HTML file) in the
     * response. In essence, this method enables programmatic server-side
     * includes.
     *
     * <p>
     * The {@link ResolverResponse} object has its path elements and parameters
     * remain unchanged from the caller's. The included servlet cannot change
     * the response status code or set headers; any attempt to make a change is
     * ignored.
     *
     * <p>
     * The request and response parameters must be either the same objects as
     * were passed to the calling servlet's service method or be subclasses of
     * the {@link HttpRequestWrapper} or {@link ResolverResponseWrapper}
     * classes that wrap them.
     *
     * @param request
     *            a {@link HttpResolverRequest} object that contains the client's
     *            request
     *
     * @param response
     *            a {@link ResolverResponse} object that contains the servlet's
     *            response
     *
     * @exception ResolverException
     *                if the included resource throws this exception
     *
     * @exception IOException
     *                if the included resource throws this exception
     */
    public void include(HttpResolverRequest request, ResolverResponse response)
            throws ResolverException, IOException;
}
