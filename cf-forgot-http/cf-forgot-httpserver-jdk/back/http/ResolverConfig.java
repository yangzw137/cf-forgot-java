package org.cf.forgot.jdk.myservlet.http;

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
public interface ResolverConfig {

    /**
     * Returns the name of this resolver instance. The name may be provided via
     * server administration, assigned in the web application deployment
     * descriptor, or for an unregistered (and thus unnamed) resolver instance it
     * will be the resolver's class name.
     *
     * @return the name of the resolver instance
     */
    public String getResolverName();

    /**
     * Returns a reference to the {@link ResolverContext} in which the caller is
     * executing.
     *
     * @return a {@link ResolverContext} object, used by the caller to interact
     *         with its resolver container
     * @see ResolverContext
     */
    public ResolverContext getResolverContext();

    /**
     * Returns a <code>String</code> containing the value of the named
     * initialization parameter, or <code>null</code> if the parameter does not
     * exist.
     *
     * @param name
     *            a <code>String</code> specifying the name of the
     *            initialization parameter
     * @return a <code>String</code> containing the value of the initialization
     *         parameter
     */
    public String getInitParameter(String name);

    /**
     * Returns the names of the resolver's initialization parameters as an
     * <code>Enumeration</code> of <code>String</code> objects, or an empty
     * <code>Enumeration</code> if the resolver has no initialization parameters.
     *
     * @return an <code>Enumeration</code> of <code>String</code> objects
     *         containing the names of the resolver's initialization parameters
     */
    public Enumeration<String> getInitParameterNames();
}
