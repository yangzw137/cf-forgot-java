package org.cf.forgot.jdk.myservlet.http.resolver;

import org.cf.forgot.jdk.myservlet.http.HttpResolverRequest;
import org.cf.forgot.jdk.myservlet.http.HttpResolverResponse;
import org.cf.forgot.jdk.myservlet.http.HttpResolverResponseWrapper;
import org.cf.forgot.jdk.myservlet.http.ResolverRequest;
import org.cf.forgot.jdk.myservlet.http.ResolverResponse;
import org.cf.forgot.jdk.myservlet.http.exception.ResolverException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/10/22
 * @since todo
 */
public class HttpResolver extends GenericResolver {


    private static final long serialVersionUID = 1L;

    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_HEAD = "HEAD";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_TRACE = "TRACE";

    private static final String HEADER_IFMODSINCE = "If-Modified-Since";
    private static final String HEADER_LASTMOD = "Last-Modified";

    private static final String LSTRING_FILE =
            "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings =
            ResourceBundle.getBundle(LSTRING_FILE);


    /**
     * Does nothing, because this is an abstract class.
     */
    public HttpResolver() {
        // NOOP
    }


    /**
     * Called by the server (via the <code>service</code> method) to
     * allow a servlet to handle a GET request.
     *
     * <p>Overriding this method to support a GET request also
     * automatically supports an HTTP HEAD request. A HEAD
     * request is a GET request that returns no body in the
     * response, only the request header fields.
     *
     * <p>When overriding this method, read the request data,
     * write the response headers, get the response's writer or
     * output stream object, and finally, write the response data.
     * It's best to include content type and encoding. When using
     * a <code>PrintWriter</code> object to return the response,
     * set the content type before accessing the
     * <code>PrintWriter</code> object.
     *
     * <p>The servlet container must write the headers before
     * committing the response, because in HTTP the headers must be sent
     * before the response body.
     *
     * <p>Where possible, set the Content-Length header (with the
     * {@link ResolverResponse#setContentLength} method),
     * to allow the servlet container to use a persistent connection
     * to return its response to the client, improving performance.
     * The content length is automatically set if the entire response fits
     * inside the response buffer.
     *
     * <p>When using HTTP 1.1 chunked encoding (which means that the response
     * has a Transfer-Encoding header), do not set the Content-Length header.
     *
     * <p>The GET method should be safe, that is, without
     * any side effects for which users are held responsible.
     * For example, most form queries have no side effects.
     * If a client request is intended to change stored data,
     * the request should use some other HTTP method.
     *
     * <p>The GET method should also be idempotent, meaning
     * that it can be safely repeated. Sometimes making a
     * method safe also makes it idempotent. For example,
     * repeating queries is both safe and idempotent, but
     * buying a product online or modifying data is neither
     * safe nor idempotent.
     *
     * <p>If the request is incorrectly formatted, <code>doGet</code>
     * returns an HTTP "Bad Request" message.
     *
     * @param req   an {@link HttpResolverRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param resp  an {@link HttpResolverResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @exception IOException   if an input or output error is
     *                              detected when the servlet handles
     *                              the GET request
     *
     * @exception ResolverException  if the request for the GET
     *                                  could not be handled
     *
     */
    protected void doGet(HttpResolverRequest req, HttpResolverResponse resp)
            throws ResolverException, IOException
    {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_get_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(HttpResolverResponse.SC_METHOD_NOT_ALLOWED, msg);
        } else {
            resp.sendError(HttpResolverResponse.SC_BAD_REQUEST, msg);
        }
    }


    /**
     * Returns the time the <code>HttpResolverRequest</code>
     * object was last modified,
     * in milliseconds since midnight January 1, 1970 GMT.
     * If the time is unknown, this method returns a negative
     * number (the default).
     *
     * <p>Resolvers that support HTTP GET requests and can quickly determine
     * their last modification time should override this method.
     * This makes browser and proxy caches work more effectively,
     * reducing the load on server and network resources.
     *
     * @param req   the <code>HttpResolverRequest</code>
     *                  object that is sent to the servlet
     *
     * @return  a <code>long</code> integer specifying
     *              the time the <code>HttpResolverRequest</code>
     *              object was last modified, in milliseconds
     *              since midnight, January 1, 1970 GMT, or
     *              -1 if the time is not known
     */
    protected long getLastModified(HttpResolverRequest req) {
        return -1;
    }


    /**
     * <p>Receives an HTTP HEAD request from the protected
     * <code>service</code> method and handles the
     * request.
     * The client sends a HEAD request when it wants
     * to see only the headers of a response, such as
     * Content-Type or Content-Length. The HTTP HEAD
     * method counts the output bytes in the response
     * to set the Content-Length header accurately.
     *
     * <p>If you override this method, you can avoid computing
     * the response body and just set the response headers
     * directly to improve performance. Make sure that the
     * <code>doHead</code> method you write is both safe
     * and idempotent (that is, protects itself from being
     * called multiple times for one HTTP HEAD request).
     *
     * <p>If the HTTP HEAD request is incorrectly formatted,
     * <code>doHead</code> returns an HTTP "Bad Request"
     * message.
     *
     * @param req   the request object that is passed to the servlet
     *
     * @param resp  the response object that the servlet
     *                  uses to return the headers to the client
     *
     * @exception IOException   if an input or output error occurs
     *
     * @exception ResolverException  if the request for the HEAD
     *                                  could not be handled
     */
    protected void doHead(HttpResolverRequest req, HttpResolverResponse resp)
            throws ResolverException, IOException {

        NoBodyResponse response = new NoBodyResponse(resp);

        doGet(req, response);
        response.setContentLength();
    }


    /**
     * Called by the server (via the <code>service</code> method)
     * to allow a servlet to handle a POST request.
     *
     * The HTTP POST method allows the client to send
     * data of unlimited length to the Web server a single time
     * and is useful when posting information such as
     * credit card numbers.
     *
     * <p>When overriding this method, read the request data,
     * write the response headers, get the response's writer or output
     * stream object, and finally, write the response data. It's best
     * to include content type and encoding. When using a
     * <code>PrintWriter</code> object to return the response, set the
     * content type before accessing the <code>PrintWriter</code> object.
     *
     * <p>The servlet container must write the headers before committing the
     * response, because in HTTP the headers must be sent before the
     * response body.
     *
     * <p>Where possible, set the Content-Length header (with the
     * {@link ResolverResponse#setContentLength} method),
     * to allow the servlet container to use a persistent connection
     * to return its response to the client, improving performance.
     * The content length is automatically set if the entire response fits
     * inside the response buffer.
     *
     * <p>When using HTTP 1.1 chunked encoding (which means that the response
     * has a Transfer-Encoding header), do not set the Content-Length header.
     *
     * <p>This method does not need to be either safe or idempotent.
     * Operations requested through POST can have side effects for
     * which the user can be held accountable, for example,
     * updating stored data or buying items online.
     *
     * <p>If the HTTP POST request is incorrectly formatted,
     * <code>doPost</code> returns an HTTP "Bad Request" message.
     *
     *
     * @param req   an {@link HttpResolverRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param resp  an {@link HttpResolverResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @exception IOException   if an input or output error is
     *                              detected when the servlet handles
     *                              the request
     *
     * @exception ResolverException  if the request for the POST
     *                                  could not be handled
     *
     * @see OutputStream
     * @see ResolverResponse#setContentType
     */
    protected void doPost(HttpResolverRequest req, HttpResolverResponse resp)
            throws ResolverException, IOException {

        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_post_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(HttpResolverResponse.SC_METHOD_NOT_ALLOWED, msg);
        } else {
            resp.sendError(HttpResolverResponse.SC_BAD_REQUEST, msg);
        }
    }


    /**
     * Called by the server (via the <code>service</code> method)
     * to allow a servlet to handle a PUT request.
     *
     * The PUT operation allows a client to
     * place a file on the server and is similar to
     * sending a file by FTP.
     *
     * <p>When overriding this method, leave intact
     * any content headers sent with the request (including
     * Content-Length, Content-Type, Content-Transfer-Encoding,
     * Content-Encoding, Content-Base, Content-Language, Content-Location,
     * Content-MD5, and Content-Range). If your method cannot
     * handle a content header, it must issue an error message
     * (HTTP 501 - Not Implemented) and discard the request.
     * For more information on HTTP 1.1, see RFC 2616
     * <a href="http://www.ietf.org/rfc/rfc2616.txt"></a>.
     *
     * <p>This method does not need to be either safe or idempotent.
     * Operations that <code>doPut</code> performs can have side
     * effects for which the user can be held accountable. When using
     * this method, it may be useful to save a copy of the
     * affected URL in temporary storage.
     *
     * <p>If the HTTP PUT request is incorrectly formatted,
     * <code>doPut</code> returns an HTTP "Bad Request" message.
     *
     * @param req   the {@link HttpResolverRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param resp  the {@link HttpResolverResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     * @exception IOException   if an input or output error occurs
     *                              while the servlet is handling the
     *                              PUT request
     *
     * @exception ResolverException  if the request for the PUT
     *                                  cannot be handled
     */
    protected void doPut(HttpResolverRequest req, HttpResolverResponse resp)
            throws ResolverException, IOException {

        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_put_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(HttpResolverResponse.SC_METHOD_NOT_ALLOWED, msg);
        } else {
            resp.sendError(HttpResolverResponse.SC_BAD_REQUEST, msg);
        }
    }


    /**
     * Called by the server (via the <code>service</code> method)
     * to allow a servlet to handle a DELETE request.
     *
     * The DELETE operation allows a client to remove a document
     * or Web page from the server.
     *
     * <p>This method does not need to be either safe
     * or idempotent. Operations requested through
     * DELETE can have side effects for which users
     * can be held accountable. When using
     * this method, it may be useful to save a copy of the
     * affected URL in temporary storage.
     *
     * <p>If the HTTP DELETE request is incorrectly formatted,
     * <code>doDelete</code> returns an HTTP "Bad Request"
     * message.
     *
     * @param req   the {@link HttpResolverRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     *
     * @param resp  the {@link HttpResolverResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     * @exception IOException   if an input or output error occurs
     *                              while the servlet is handling the
     *                              DELETE request
     *
     * @exception ResolverException  if the request for the
     *                                  DELETE cannot be handled
     */
    protected void doDelete(HttpResolverRequest req,
                            HttpResolverResponse resp)
            throws ResolverException, IOException {

        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_delete_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(HttpResolverResponse.SC_METHOD_NOT_ALLOWED, msg);
        } else {
            resp.sendError(HttpResolverResponse.SC_BAD_REQUEST, msg);
        }
    }


    private static Method[] getAllDeclaredMethods(Class<?> c) {

        if (c.equals(HttpResolver.class)) {
            return null;
        }

        Method[] parentMethods = getAllDeclaredMethods(c.getSuperclass());
        Method[] thisMethods = c.getDeclaredMethods();

        if ((parentMethods != null) && (parentMethods.length > 0)) {
            Method[] allMethods =
                    new Method[parentMethods.length + thisMethods.length];
            System.arraycopy(parentMethods, 0, allMethods, 0,
                    parentMethods.length);
            System.arraycopy(thisMethods, 0, allMethods, parentMethods.length,
                    thisMethods.length);

            thisMethods = allMethods;
        }

        return thisMethods;
    }


    /**
     * Called by the server (via the <code>service</code> method)
     * to allow a servlet to handle a OPTIONS request.
     *
     * The OPTIONS request determines which HTTP methods
     * the server supports and
     * returns an appropriate header. For example, if a servlet
     * overrides <code>doGet</code>, this method returns the
     * following header:
     *
     * <p><code>Allow: GET, HEAD, TRACE, OPTIONS</code>
     *
     * <p>There's no need to override this method unless the
     * servlet implements new HTTP methods, beyond those
     * implemented by HTTP 1.1.
     *
     * @param req   the {@link HttpResolverRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param resp  the {@link HttpResolverResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     * @exception IOException   if an input or output error occurs
     *                              while the servlet is handling the
     *                              OPTIONS request
     *
     * @exception ResolverException  if the request for the
     *                                  OPTIONS cannot be handled
     */
    protected void doOptions(HttpResolverRequest req,
                             HttpResolverResponse resp)
            throws ResolverException, IOException {

        Method[] methods = getAllDeclaredMethods(this.getClass());

        boolean ALLOW_GET = false;
        boolean ALLOW_HEAD = false;
        boolean ALLOW_POST = false;
        boolean ALLOW_PUT = false;
        boolean ALLOW_DELETE = false;
        boolean ALLOW_TRACE = true;
        boolean ALLOW_OPTIONS = true;

        for (int i=0; i<methods.length; i++) {
            Method m = methods[i];

            if (m.getName().equals("doGet")) {
                ALLOW_GET = true;
                ALLOW_HEAD = true;
            }
            if (m.getName().equals("doPost"))
                ALLOW_POST = true;
            if (m.getName().equals("doPut"))
                ALLOW_PUT = true;
            if (m.getName().equals("doDelete"))
                ALLOW_DELETE = true;
        }

        String allow = null;
        if (ALLOW_GET)
            allow=METHOD_GET;
        if (ALLOW_HEAD)
            if (allow==null) allow=METHOD_HEAD;
            else allow += ", " + METHOD_HEAD;
        if (ALLOW_POST)
            if (allow==null) allow=METHOD_POST;
            else allow += ", " + METHOD_POST;
        if (ALLOW_PUT)
            if (allow==null) allow=METHOD_PUT;
            else allow += ", " + METHOD_PUT;
        if (ALLOW_DELETE)
            if (allow==null) allow=METHOD_DELETE;
            else allow += ", " + METHOD_DELETE;
        if (ALLOW_TRACE)
            if (allow==null) allow=METHOD_TRACE;
            else allow += ", " + METHOD_TRACE;
        if (ALLOW_OPTIONS)
            if (allow==null) allow=METHOD_OPTIONS;
            else allow += ", " + METHOD_OPTIONS;

        resp.setHeader("Allow", allow);
    }


    /**
     * Called by the server (via the <code>service</code> method)
     * to allow a servlet to handle a TRACE request.
     *
     * A TRACE returns the headers sent with the TRACE
     * request to the client, so that they can be used in
     * debugging. There's no need to override this method.
     *
     * @param req   the {@link HttpResolverRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param resp  the {@link HttpResolverResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     * @exception IOException   if an input or output error occurs
     *                              while the servlet is handling the
     *                              TRACE request
     *
     * @exception ResolverException  if the request for the
     *                                  TRACE cannot be handled
     */
    protected void doTrace(HttpResolverRequest req, HttpResolverResponse resp)
            throws ResolverException, IOException
    {

        int responseLength;

        String CRLF = "\r\n";
        StringBuilder buffer = new StringBuilder("TRACE ").append(req.getRequestURI())
                .append(" ").append(req.getProtocol());

        Enumeration<String> reqHeaderEnum = req.getHeaderNames();

        while( reqHeaderEnum.hasMoreElements() ) {
            String headerName = reqHeaderEnum.nextElement();
            buffer.append(CRLF).append(headerName).append(": ")
                    .append(req.getHeader(headerName));
        }

        buffer.append(CRLF);

        responseLength = buffer.length();

        resp.setContentType("message/http");
        resp.setContentLength(responseLength);
        OutputStream out = resp.getOutputStream();
        out.write(buffer.toString().getBytes());
        out.close();
        return;
    }


    /**
     * Receives standard HTTP requests from the public
     * <code>service</code> method and dispatches
     * them to the <code>do</code><i>XXX</i> methods defined in
     * this class. This method is an HTTP-specific version of the
     * {@link Resolver#service} method. There's no
     * need to override this method.
     *
     * @param req   the {@link HttpResolverRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param resp  the {@link HttpResolverResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     * @exception IOException   if an input or output error occurs
     *                              while the servlet is handling the
     *                              HTTP request
     *
     * @exception ResolverException  if the HTTP request
     *                                  cannot be handled
     *
     * @see Resolver#service
     */
    protected void service(HttpResolverRequest req, HttpResolverResponse resp)
            throws ResolverException, IOException {

        String method = req.getMethod();

        if (method.equals(METHOD_GET)) {
            long lastModified = getLastModified(req);
            if (lastModified == -1) {
                // servlet doesn't support if-modified-since, no reason
                // to go through further expensive logic
                doGet(req, resp);
            } else {
                long ifModifiedSince = req.getDateHeader(HEADER_IFMODSINCE);
                if (ifModifiedSince < (lastModified / 1000 * 1000)) {
                    // If the servlet mod time is later, call doGet()
                    // Round down to the nearest second for a proper compare
                    // A ifModifiedSince of -1 will always be less
                    maybeSetLastModified(resp, lastModified);
                    doGet(req, resp);
                } else {
                    resp.setStatus(HttpResolverResponse.SC_NOT_MODIFIED);
                }
            }

        } else if (method.equals(METHOD_HEAD)) {
            long lastModified = getLastModified(req);
            maybeSetLastModified(resp, lastModified);
            doHead(req, resp);

        } else if (method.equals(METHOD_POST)) {
            doPost(req, resp);

        } else if (method.equals(METHOD_PUT)) {
            doPut(req, resp);

        } else if (method.equals(METHOD_DELETE)) {
            doDelete(req, resp);

        } else if (method.equals(METHOD_OPTIONS)) {
            doOptions(req,resp);

        } else if (method.equals(METHOD_TRACE)) {
            doTrace(req,resp);

        } else {
            //
            // Note that this means NO servlet supports whatever
            // method was requested, anywhere on this server.
            //

            String errMsg = lStrings.getString("http.method_not_implemented");
            Object[] errArgs = new Object[1];
            errArgs[0] = method;
            errMsg = MessageFormat.format(errMsg, errArgs);

            resp.sendError(HttpResolverResponse.SC_NOT_IMPLEMENTED, errMsg);
        }
    }


    /*
     * Sets the Last-Modified entity header field, if it has not
     * already been set and if the value is meaningful.  Called before
     * doGet, to ensure that headers are set before response data is
     * written.  A subclass might have set this header already, so we
     * check.
     */
    private void maybeSetLastModified(HttpResolverResponse resp,
                                      long lastModified) {
        if (resp.containsHeader(HEADER_LASTMOD))
            return;
        if (lastModified >= 0)
            resp.setDateHeader(HEADER_LASTMOD, lastModified);
    }


    /**
     * Dispatches client requests to the protected
     * <code>service</code> method. There's no need to
     * override this method.
     *
     * @param req   the {@link HttpResolverRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param res   the {@link HttpResolverResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     * @exception IOException   if an input or output error occurs
     *                              while the servlet is handling the
     *                              HTTP request
     *
     * @exception ResolverException  if the HTTP request cannot
     *                                  be handled
     *
     * @see Resolver#service
     */
    @Override
    public void service(ResolverRequest req, ResolverResponse res)
            throws ResolverException, IOException {

        HttpResolverRequest  request;
        HttpResolverResponse response;

        try {
            request = (HttpResolverRequest) req;
            response = (HttpResolverResponse) res;
        } catch (ClassCastException e) {
            throw new ResolverException("non-HTTP request or response");
        }
        service(request, response);
    }
}


/*
 * A response wrapper for use in (dumb) "HEAD" support.
 * This just swallows that body, counting the bytes in order to set
 * the content length appropriately.  All other methods delegate to the
 * wrapped HTTP Resolver Response object.
 */
// file private
class NoBodyResponse extends HttpResolverResponseWrapper {
    private NoBodyOutputStream                noBody;
    private PrintWriter writer;
    private boolean                        didSetContentLength;

    // file private
    NoBodyResponse(HttpResolverResponse r) {
        super(r);
        noBody = new NoBodyOutputStream();
    }

    // file private
    void setContentLength() {
        if (!didSetContentLength)
            super.setContentLength(noBody.getContentLength());
    }


    // SERVLET RESPONSE interface methods

    @Override
    public void setContentLength(int len) {
        super.setContentLength(len);
        didSetContentLength = true;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return noBody;
    }

    @Override
    public PrintWriter getWriter() throws UnsupportedEncodingException {

        if (writer == null) {
            OutputStreamWriter w;

            w = new OutputStreamWriter(noBody, getCharacterEncoding());
            writer = new PrintWriter(w);
        }
        return writer;
    }
}


/*
 * Resolver output stream that gobbles up all its data.
 */

// file private
class NoBodyOutputStream extends OutputStream {

    private static final String LSTRING_FILE =
            "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings =
            ResourceBundle.getBundle(LSTRING_FILE);

    private int                contentLength = 0;

    // file private
    NoBodyOutputStream() {
        // NOOP
    }

    // file private
    int getContentLength() {
        return contentLength;
    }

    @Override
    public void write(int b) {
        contentLength++;
    }

    @Override
    public void write(byte buf[], int offset, int len)
            throws IOException
    {
        if (len >= 0) {
            contentLength += len;
        } else {
            // XXX
            // isn't this really an IllegalArgumentException?

            String msg = lStrings.getString("err.io.negativelength");
            throw new IOException(msg);
        }
    }
}
