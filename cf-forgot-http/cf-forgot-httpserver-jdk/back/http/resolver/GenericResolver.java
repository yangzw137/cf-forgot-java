package org.cf.forgot.jdk.myservlet.http.resolver;

import org.cf.forgot.jdk.myservlet.http.ResolverConfig;
import org.cf.forgot.jdk.myservlet.http.ResolverContext;
import org.cf.forgot.jdk.myservlet.http.exception.ResolverException;
import org.cf.forgot.jdk.myservlet.http.ResolverRequest;
import org.cf.forgot.jdk.myservlet.http.ResolverResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/10/22
 * @since todo
 */
public abstract class GenericResolver implements Resolver, ResolverConfig,
        java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private transient ResolverConfig config;

    /**
     * Does nothing. All of the servlet initialization is done by one of the
     * <code>init</code> methods.
     */
    public GenericResolver() {
        // NOOP
    }

    /**
     * Called by the servlet container to indicate to a servlet that the servlet
     * is being taken out of service. See {@link Resolver#destroy}.
     */
    @Override
    public void destroy() {
        // NOOP by default
    }

    /**
     * Returns a <code>String</code> containing the value of the named
     * initialization parameter, or <code>null</code> if the parameter does not
     * exist. See {@link ResolverConfig#getInitParameter}.
     * <p>
     * This method is supplied for convenience. It gets the value of the named
     * parameter from the servlet's <code>ResolverConfig</code> object.
     *
     * @param name
     *            a <code>String</code> specifying the name of the
     *            initialization parameter
     * @return String a <code>String</code> containing the value of the
     *         initialization parameter
     */
    @Override
    public String getInitParameter(String name) {
        return getResolverConfig().getInitParameter(name);
    }

    /**
     * Returns the names of the servlet's initialization parameters as an
     * <code>Enumeration</code> of <code>String</code> objects, or an empty
     * <code>Enumeration</code> if the servlet has no initialization parameters.
     * See {@link ResolverConfig#getInitParameterNames}.
     * <p>
     * This method is supplied for convenience. It gets the parameter names from
     * the servlet's <code>ResolverConfig</code> object.
     *
     * @return Enumeration an enumeration of <code>String</code> objects
     *         containing the names of the servlet's initialization parameters
     */
    @Override
    public Enumeration<String> getInitParameterNames() {
        return getResolverConfig().getInitParameterNames();
    }

    /**
     * Returns this servlet's {@link ResolverConfig} object.
     *
     * @return ResolverConfig the <code>ResolverConfig</code> object that
     *         initialized this servlet
     */
    @Override
    public ResolverConfig getResolverConfig() {
        return config;
    }

    /**
     * Returns a reference to the {@link ResolverContext} in which this servlet
     * is running. See {@link ResolverConfig#getResolverContext}.
     * <p>
     * This method is supplied for convenience. It gets the context from the
     * servlet's <code>ResolverConfig</code> object.
     *
     * @return ResolverContext the <code>ResolverContext</code> object passed to
     *         this servlet by the <code>init</code> method
     */
    @Override
    public ResolverContext getResolverContext() {
        return getResolverConfig().getResolverContext();
    }

    /**
     * Returns information about the servlet, such as author, version, and
     * copyright. By default, this method returns an empty string. Override this
     * method to have it return a meaningful value. See
     * {@link Resolver#getResolverInfo}.
     *
     * @return String information about this servlet, by default an empty string
     */
    @Override
    public String getResolverInfo() {
        return "";
    }

    /**
     * Called by the servlet container to indicate to a servlet that the servlet
     * is being placed into service. See {@link Resolver#init}.
     * <p>
     * This implementation stores the {@link ResolverConfig} object it receives
     * from the servlet container for later use. When overriding this form of
     * the method, call <code>super.init(config)</code>.
     *
     * @param config
     *            the <code>ResolverConfig</code> object that contains
     *            configuration information for this servlet
     * @exception ResolverException
     *                if an exception occurs that interrupts the servlet's
     *                normal operation
     */
    @Override
    public void init(ResolverConfig config) throws ResolverException {
        this.config = config;
        this.init();
    }

    /**
     * A convenience method which can be overridden so that there's no need to
     * call <code>super.init(config)</code>.
     * <p>
     * Instead of overriding {@link #init(ResolverConfig)}, simply override this
     * method and it will be called by
     * <code>GenericResolver.init(ResolverConfig config)</code>. The
     * <code>ResolverConfig</code> object can still be retrieved via
     * {@link #getResolverConfig}.
     *
     * @exception ResolverException
     *                if an exception occurs that interrupts the servlet's
     *                normal operation
     */
    public void init() throws ResolverException {
        // NOOP by default
    }

    /**
     * Writes the specified message to a servlet log file, prepended by the
     * servlet's name. See {@link ResolverContext#log(String)}.
     *
     * @param msg
     *            a <code>String</code> specifying the message to be written to
     *            the log file
     */
    public void log(String msg) {
        getResolverContext().log(getResolverName() + ": " + msg);
    }

    /**
     * Writes an explanatory message and a stack trace for a given
     * <code>Throwable</code> exception to the servlet log file, prepended by
     * the servlet's name. See {@link ResolverContext#log(String, Throwable)}.
     *
     * @param message
     *            a <code>String</code> that describes the error or exception
     * @param t
     *            the <code>java.lang.Throwable</code> error or exception
     */
    public void log(String message, Throwable t) {
        getResolverContext().log(getResolverName() + ": " + message, t);
    }

    /**
     * Called by the servlet container to allow the servlet to respond to a
     * request. See {@link Resolver#service}.
     * <p>
     * This method is declared abstract so subclasses, such as
     * <code>HttpResolver</code>, must override it.
     *
     * @param req
     *            the <code>ResolverRequest</code> object that contains the
     *            client's request
     * @param res
     *            the <code>ResolverResponse</code> object that will contain the
     *            servlet's response
     * @exception ResolverException
     *                if an exception occurs that interferes with the servlet's
     *                normal operation occurred
     * @exception IOException
     *                if an input or output exception occurs
     */
    @Override
    public abstract void service(ResolverRequest req, ResolverResponse res) throws ResolverException, IOException;

    /**
     * Returns the name of this servlet instance. See
     * {@link ResolverConfig#getResolverName}.
     *
     * @return the name of this servlet instance
     */
    @Override
    public String getResolverName() {
        return config.getResolverName();
    }
}
