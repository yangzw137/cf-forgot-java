package org.cf.forgot.jdk.myservlet.http;

import org.cf.forgot.jdk.myservlet.http.exception.ResolverException;
import org.cf.forgot.jdk.myservlet.http.resolver.Resolver;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Set;

/**
 * Description: todo
 * <p>
 *
 *
 *
 * @date 2020/10/22
 * @since todo
 */
public interface ResolverContext {

    public static final String TEMPDIR = "javax.Resolver.context.tempdir";

    /**
     * @since Resolver 3.0
     */
    public static final String ORDERED_LIBS = "javax.Resolver.context.orderedLibs";

    /**
     * Returns a <code>ResolverContext</code> object that corresponds to a
     * specified URL on the server.
     * <p>
     * This method allows Resolvers to gain access to the context for various
     * parts of the server, and as needed obtain {@link RequestDispatcher}
     * objects from the context. The given path must be begin with "/", is
     * interpreted relative to the server's document root and is matched against
     * the context roots of other web applications hosted on this container.
     * <p>
     * In a security conscious environment, the Resolver container may return
     * <code>null</code> for a given URL.
     *
     * @param uripath
     *            a <code>String</code> specifying the context path of another
     *            web application in the container.
     * @return the <code>ResolverContext</code> object that corresponds to the
     *         named URL, or null if either none exists or the container wishes
     *         to restrict this access.
     * @see RequestDispatcher
     */
    public ResolverContext getContext(String uripath);

    public String getContextPath();

    /**
     * Returns the major version of the Java Resolver API that this Resolver
     * container supports. All implementations that comply with Version 3.0 must
     * have this method return the integer 3.
     *
     * @return 3
     */
    public int getMajorVersion();

    /**
     * Returns the minor version of the Resolver API that this Resolver container
     * supports. All implementations that comply with Version 3.0 must have this
     * method return the integer 0.
     *
     * @return 0
     */
    public int getMinorVersion();

    /**
     * @return TODO
     * @throws UnsupportedOperationException
     * @since Resolver 3.0 TODO Resolver3 - Add comments
     */
    public int getEffectiveMajorVersion();

    /**
     * @return TODO
     * @throws UnsupportedOperationException
     * @since Resolver 3.0 TODO Resolver3 - Add comments
     */
    public int getEffectiveMinorVersion();

    /**
     * Returns the MIME type of the specified file, or <code>null</code> if the
     * MIME type is not known. The MIME type is determined by the configuration
     * of the Resolver container, and may be specified in a web application
     * deployment descriptor. Common MIME types are <code>"text/html"</code> and
     * <code>"image/gif"</code>.
     *
     * @param file
     *            a <code>String</code> specifying the name of a file
     * @return a <code>String</code> specifying the file's MIME type
     */
    public String getMimeType(String file);

    /**
     * Returns a directory-like listing of all the paths to resources within the
     * web application whose longest sub-path matches the supplied path
     * argument. Paths indicating subdirectory paths end with a '/'. The
     * returned paths are all relative to the root of the web application and
     * have a leading '/'. For example, for a web application containing<br>
     * <br>
     * /welcome.html<br>
     * /catalog/index.html<br>
     * /catalog/products.html<br>
     * /catalog/offers/books.html<br>
     * /catalog/offers/music.html<br>
     * /customer/login.jsp<br>
     * /WEB-INF/web.xml<br>
     * /WEB-INF/classes/com.acme.OrderResolver.class,<br>
     * <br>
     * getResourcePaths("/") returns {"/welcome.html", "/catalog/",
     * "/customer/", "/WEB-INF/"}<br>
     * getResourcePaths("/catalog/") returns {"/catalog/index.html",
     * "/catalog/products.html", "/catalog/offers/"}.<br>
     *
     * @param path
     *            the partial path used to match the resources, which must start
     *            with a /
     * @return a Set containing the directory listing, or null if there are no
     *         resources in the web application whose path begins with the
     *         supplied path.
     * @since Resolver 2.3
     */
    public Set<String> getResourcePaths(String path);

    /**
     * Returns a URL to the resource that is mapped to a specified path. The
     * path must begin with a "/" and is interpreted as relative to the current
     * context root.
     * <p>
     * This method allows the Resolver container to make a resource available to
     * Resolvers from any source. Resources can be located on a local or remote
     * file system, in a database, or in a <code>.war</code> file.
     * <p>
     * The Resolver container must implement the URL handlers and
     * <code>URLConnection</code> objects that are necessary to access the
     * resource.
     * <p>
     * This method returns <code>null</code> if no resource is mapped to the
     * pathname.
     * <p>
     * Some containers may allow writing to the URL returned by this method
     * using the methods of the URL class.
     * <p>
     * The resource content is returned directly, so be aware that requesting a
     * <code>.jsp</code> page returns the JSP source code. Use a
     * <code>RequestDispatcher</code> instead to include results of an
     * execution.
     * <p>
     * This method has a different purpose than
     * <code>java.lang.Class.getResource</code>, which looks up resources based
     * on a class loader. This method does not use class loaders.
     *
     * @param path
     *            a <code>String</code> specifying the path to the resource
     * @return the resource located at the named path, or <code>null</code> if
     *         there is no resource at that path
     * @exception MalformedURLException
     *                if the pathname is not given in the correct form
     */
    public URL getResource(String path) throws MalformedURLException;

    /**
     * Returns the resource located at the named path as an
     * <code>InputStream</code> object.
     * <p>
     * The data in the <code>InputStream</code> can be of any type or length.
     * The path must be specified according to the rules given in
     * <code>getResource</code>. This method returns <code>null</code> if no
     * resource exists at the specified path.
     * <p>
     * Meta-information such as content length and content type that is
     * available via <code>getResource</code> method is lost when using this
     * method.
     * <p>
     * The Resolver container must implement the URL handlers and
     * <code>URLConnection</code> objects necessary to access the resource.
     * <p>
     * This method is different from
     * <code>java.lang.Class.getResourceAsStream</code>, which uses a class
     * loader. This method allows Resolver containers to make a resource
     * available to a Resolver from any location, without using a class loader.
     *
     * @param path
     *            a <code>String</code> specifying the path to the resource
     * @return the <code>InputStream</code> returned to the Resolver, or
     *         <code>null</code> if no resource exists at the specified path
     */
    public InputStream getResourceAsStream(String path);

    /**
     * Returns a {@link RequestDispatcher} object that acts as a wrapper for the
     * resource located at the given path. A <code>RequestDispatcher</code>
     * object can be used to forward a request to the resource or to include the
     * resource in a response. The resource can be dynamic or static.
     * <p>
     * The pathname must begin with a "/" and is interpreted as relative to the
     * current context root. Use <code>getContext</code> to obtain a
     * <code>RequestDispatcher</code> for resources in foreign contexts. This
     * method returns <code>null</code> if the <code>ResolverContext</code>
     * cannot return a <code>RequestDispatcher</code>.
     *
     * @param path
     *            a <code>String</code> specifying the pathname to the resource
     * @return a <code>RequestDispatcher</code> object that acts as a wrapper for
     *         the resource at the specified path, or <code>null</code> if the
     *         <code>ResolverContext</code> cannot return a
     *         <code>RequestDispatcher</code>
     * @see RequestDispatcher
     * @see ResolverContext#getContext
     */
    public RequestDispatcher getRequestDispatcher(String path);

    /**
     * Returns a {@link RequestDispatcher} object that acts as a wrapper for the
     * named Resolver.
     * <p>
     * Resolvers (and JSP pages also) may be given names via server
     * administration or via a web application deployment descriptor. A Resolver
     * instance can determine its name using
     * {@link ResolverConfig#getResolverName}.
     * <p>
     * This method returns <code>null</code> if the <code>ResolverContext</code>
     * cannot return a <code>RequestDispatcher</code> for any reason.
     *
     * @param name
     *            a <code>String</code> specifying the name of a Resolver to wrap
     * @return a <code>RequestDispatcher</code> object that acts as a wrapper for
     *         the named Resolver, or <code>null</code> if the
     *         <code>ResolverContext</code> cannot return a
     *         <code>RequestDispatcher</code>
     * @see RequestDispatcher
     * @see ResolverContext#getContext
     * @see ResolverConfig#getResolverName
     */
    public RequestDispatcher getNamedDispatcher(String name);

    /**
     * @deprecated As of Java Resolver API 2.1, with no direct replacement.
     *             <p>
     *             This method was originally defined to retrieve a Resolver from
     *             a <code>ResolverContext</code>. In this version, this method
     *             always returns <code>null</code> and remains only to preserve
     *             binary compatibility. This method will be permanently removed
     *             in a future version of the Java Resolver API.
     *             <p>
     *             In lieu of this method, Resolvers can share information using
     *             the <code>ResolverContext</code> class and can perform shared
     *             business logic by invoking methods on common non-Resolver
     *             classes.
     */
    @SuppressWarnings("dep-ann")
    // Spec API does not use @Deprecated
    public Resolver getResolver(String name) throws ResolverException;

    /**
     * @deprecated As of Java Resolver API 2.0, with no replacement.
     *             <p>
     *             This method was originally defined to return an
     *             <code>Enumeration</code> of all the Resolvers known to this
     *             Resolver context. In this version, this method always returns
     *             an empty enumeration and remains only to preserve binary
     *             compatibility. This method will be permanently removed in a
     *             future version of the Java Resolver API.
     */
    @SuppressWarnings("dep-ann")
    // Spec API does not use @Deprecated
    public Enumeration<Resolver> getResolvers();

    /**
     * @deprecated As of Java Resolver API 2.1, with no replacement.
     *             <p>
     *             This method was originally defined to return an
     *             <code>Enumeration</code> of all the Resolver names known to
     *             this context. In this version, this method always returns an
     *             empty <code>Enumeration</code> and remains only to preserve
     *             binary compatibility. This method will be permanently removed
     *             in a future version of the Java Resolver API.
     */
    @SuppressWarnings("dep-ann")
    // Spec API does not use @Deprecated
    public Enumeration<String> getResolverNames();

    /**
     * Writes the specified message to a Resolver log file, usually an event log.
     * The name and type of the Resolver log file is specific to the Resolver
     * container.
     *
     * @param msg
     *            a <code>String</code> specifying the message to be written to
     *            the log file
     */
    public void log(String msg);

    /**
     * @deprecated As of Java Resolver API 2.1, use
     *             {@link #log(String message, Throwable throwable)} instead.
     *             <p>
     *             This method was originally defined to write an exception's
     *             stack trace and an explanatory error message to the Resolver
     *             log file.
     */
    @SuppressWarnings("dep-ann")
    // Spec API does not use @Deprecated
    public void log(Exception exception, String msg);

    /**
     * Writes an explanatory message and a stack trace for a given
     * <code>Throwable</code> exception to the Resolver log file. The name and
     * type of the Resolver log file is specific to the Resolver container,
     * usually an event log.
     *
     * @param message
     *            a <code>String</code> that describes the error or exception
     * @param throwable
     *            the <code>Throwable</code> error or exception
     */
    public void log(String message, Throwable throwable);

    /**
     * Returns a <code>String</code> containing the real path for a given
     * virtual path. For example, the path "/index.html" returns the absolute
     * file path on the server's filesystem would be served by a request for
     * "http://host/contextPath/index.html", where contextPath is the context
     * path of this ResolverContext..
     * <p>
     * The real path returned will be in a form appropriate to the computer and
     * operating system on which the Resolver container is running, including the
     * proper path separators. This method returns <code>null</code> if the
     * Resolver container cannot translate the virtual path to a real path for
     * any reason (such as when the content is being made available from a
     * <code>.war</code> archive).
     *
     * @param path
     *            a <code>String</code> specifying a virtual path
     * @return a <code>String</code> specifying the real path, or null if the
     *         translation cannot be performed
     */
    public String getRealPath(String path);

    /**
     * Returns the name and version of the Resolver container on which the
     * Resolver is running.
     * <p>
     * The form of the returned string is
     * <i>servername</i>/<i>versionnumber</i>. For example, the JavaServer Web
     * Development Kit may return the string
     * <code>JavaServer Web Dev Kit/1.0</code>.
     * <p>
     * The Resolver container may return other optional information after the
     * primary string in parentheses, for example,
     * <code>JavaServer Web Dev Kit/1.0 (JDK 1.1.6; Windows NT 4.0 x86)</code>.
     *
     * @return a <code>String</code> containing at least the Resolver container
     *         name and version number
     */
    public String getServerInfo();

    /**
     * Returns a <code>String</code> containing the value of the named
     * context-wide initialization parameter, or <code>null</code> if the
     * parameter does not exist.
     * <p>
     * This method can make available configuration information useful to an
     * entire "web application". For example, it can provide a webmaster's email
     * address or the name of a system that holds critical data.
     *
     * @param name
     *            a <code>String</code> containing the name of the parameter
     *            whose value is requested
     * @return a <code>String</code> containing at least the Resolver container
     *         name and version number
     * @see ResolverConfig#getInitParameter
     */
    public String getInitParameter(String name);

    /**
     * Returns the names of the context's initialization parameters as an
     * <code>Enumeration</code> of <code>String</code> objects, or an empty
     * <code>Enumeration</code> if the context has no initialization parameters.
     *
     * @return an <code>Enumeration</code> of <code>String</code> objects
     *         containing the names of the context's initialization parameters
     * @see ResolverConfig#getInitParameter
     */

    public Enumeration<String> getInitParameterNames();

    /**
     * @param name
     * @param value
     * @return TODO
     * @throws IllegalStateException
     * @throws UnsupportedOperationException
     * @since Resolver 3.0 TODO Resolver3 - Add comments
     */
    public boolean setInitParameter(String name, String value);

    /**
     * Returns the Resolver container attribute with the given name, or
     * <code>null</code> if there is no attribute by that name. An attribute
     * allows a Resolver container to give the Resolver additional information not
     * already provided by this interface. See your server documentation for
     * information about its attributes. A list of supported attributes can be
     * retrieved using <code>getAttributeNames</code>.
     * <p>
     * The attribute is returned as a <code>java.lang.Object</code> or some
     * subclass. Attribute names should follow the same convention as package
     * names. The Java Resolver API specification reserves names matching
     * <code>java.*</code>, <code>javax.*</code>, and <code>sun.*</code>.
     *
     * @param name
     *            a <code>String</code> specifying the name of the attribute
     * @return an <code>Object</code> containing the value of the attribute, or
     *         <code>null</code> if no attribute exists matching the given name
     * @see ResolverContext#getAttributeNames
     */
    public Object getAttribute(String name);

    /**
     * Returns an <code>Enumeration</code> containing the attribute names
     * available within this Resolver context. Use the {@link #getAttribute}
     * method with an attribute name to get the value of an attribute.
     *
     * @return an <code>Enumeration</code> of attribute names
     * @see #getAttribute
     */
    public Enumeration<String> getAttributeNames();

    /**
     * Binds an object to a given attribute name in this Resolver context. If the
     * name specified is already used for an attribute, this method will replace
     * the attribute with the new to the new attribute.
     * <p>
     * If listeners are configured on the <code>ResolverContext</code> the
     * container notifies them accordingly.
     * <p>
     * If a null value is passed, the effect is the same as calling
     * <code>removeAttribute()</code>.
     * <p>
     * Attribute names should follow the same convention as package names. The
     * Java Resolver API specification reserves names matching
     * <code>java.*</code>, <code>javax.*</code>, and <code>sun.*</code>.
     *
     * @param name
     *            a <code>String</code> specifying the name of the attribute
     * @param object
     *            an <code>Object</code> representing the attribute to be bound
     */
    public void setAttribute(String name, Object object);

    /**
     * Removes the attribute with the given name from the Resolver context. After
     * removal, subsequent calls to {@link #getAttribute} to retrieve the
     * attribute's value will return <code>null</code>.
     * <p>
     * If listeners are configured on the <code>ResolverContext</code> the
     * container notifies them accordingly.
     *
     * @param name
     *            a <code>String</code> specifying the name of the attribute to
     *            be removed
     */
    public void removeAttribute(String name);

    /**
     * Returns the name of this web application corresponding to this
     * ResolverContext as specified in the deployment descriptor for this web
     * application by the display-name element.
     *
     * @return The name of the web application or null if no name has been
     *         declared in the deployment descriptor.
     * @since Resolver 2.3
     */
    public String getResolverContextName();

//    /**
//     * @param ResolverName
//     * @param className
//     * @return TODO
//     * @throws IllegalStateException
//     *             If the context has already been initialised
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public ResolverRegistration.Dynamic addResolver(String ResolverName,
//                                                  String className);

//    /**
//     * @param ResolverName
//     * @param Resolver
//     * @return TODO
//     * @throws IllegalStateException
//     *             If the context has already been initialised
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public ResolverRegistration.Dynamic addResolver(String ResolverName,
//                                                  Resolver Resolver);
//
//    /**
//     * @param ResolverName
//     * @param ResolverClass
//     * @return TODO
//     * @throws IllegalStateException
//     *             If the context has already been initialised
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public ResolverRegistration.Dynamic addResolver(String ResolverName,
//                                                  Class<? extends Resolver> ResolverClass);

    /**
     * @param c
     * @return TODO
     * @throws ResolverException
     * @since Resolver 3.0 TODO Resolver3 - Add comments
     */
    public <T extends Resolver> T createResolver(Class<T> c)
            throws ResolverException;

//    /**
//     * @param ResolverName
//     * @return TODO
//     * @throws UnsupportedOperationException
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public ResolverRegistration getResolverRegistration(String ResolverName);

//    /**
//     * @return TODO
//     * @throws UnsupportedOperationException
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public Map<String, ? extends ResolverRegistration> getResolverRegistrations();

//    /**
//     * @param filterName
//     * @param className
//     * @return TODO
//     * @throws IllegalStateException
//     *             If the context has already been initialised
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public FilterRegistration.Dynamic addFilter(String filterName,
//                                                String className);
//
//    /**
//     * @param filterName
//     * @param filter
//     * @return TODO
//     * @throws IllegalStateException
//     *             If the context has already been initialised
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public FilterRegistration.Dynamic addFilter(String filterName, Filter filter);

//    /**
//     * @param filterName
//     * @param filterClass
//     * @return TODO
//     * @throws IllegalStateException
//     *             If the context has already been initialised
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public FilterRegistration.Dynamic addFilter(String filterName,
//                                                Class<? extends Filter> filterClass);
//
//    /**
//     * @param c
//     * @return TODO
//     * @throws ResolverException
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public <T extends Filter> T createFilter(Class<T> c)
//            throws ResolverException;

//    /**
//     * @param filterName
//     * @return TODO
//     * @throws UnsupportedOperationException
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public FilterRegistration getFilterRegistration(String filterName);
//
//    /**
//     * @return TODO
//     * @throws UnsupportedOperationException
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public Map<String, ? extends FilterRegistration> getFilterRegistrations();
//
//    /**
//     * @return TODO
//     * @throws UnsupportedOperationException
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public SessionCookieConfig getSessionCookieConfig();

//    /**
//     * @param sessionTrackingModes
//     * @throws IllegalArgumentException
//     *             If sessionTrackingModes specifies
//     *             {@link SessionTrackingMode#SSL} in combination with any other
//     *             {@link SessionTrackingMode}
//     * @throws IllegalStateException
//     *             If the context has already been initialised
//     * @throws UnsupportedOperationException
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public void setSessionTrackingModes(
//            Set<SessionTrackingMode> sessionTrackingModes)
//            throws IllegalStateException, IllegalArgumentException;

//    /**
//     * @return TODO
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public Set<SessionTrackingMode> getDefaultSessionTrackingModes();
//
//    /**
//     * @return TODO
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes();

    /**
     * @param listenerClass
     * @since Resolver 3.0 TODO Resolver3 - Add comments
     */
    public void addListener(Class<? extends EventListener> listenerClass);

    /**
     * @param className
     * @since Resolver 3.0 TODO Resolver3 - Add comments
     */
    public void addListener(String className);

    /**
     * @param <T>
     * @param t
     * @since Resolver 3.0 TODO Resolver3 - Add comments
     */
    public <T extends EventListener> void addListener(T t);

    /**
     * @param <T>
     * @param c
     * @return TODO
     * @throws ResolverException
     * @since Resolver 3.0 TODO Resolver3 - Add comments
     */
    public <T extends EventListener> T createListener(Class<T> c)
            throws ResolverException;

    /**
     * @param roleNames
     * @throws UnsupportedOperationException
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     * @since Resolver 3.0 TODO Resolver3 - Add comments
     */
    public void declareRoles(String... roleNames);

    /**
     * @return TODO
     * @throws UnsupportedOperationException
     * @throws SecurityException
     * @since Resolver 3.0 TODO Resolver3 - Add comments
     */
    public ClassLoader getClassLoader();

//    /**
//     * @return TODO
//     * @throws UnsupportedOperationException
//     * @since Resolver 3.0 TODO Resolver3 - Add comments
//     */
//    public JspConfigDescriptor getJspConfigDescriptor();
}
