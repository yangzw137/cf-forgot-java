/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cf.forgot.jdk.myservlet.http;

import org.cf.forgot.jdk.myservlet.http.exception.ResolverException;

import java.io.IOException;
import java.util.Enumeration;

/**
 * Provides a convenient implementation of the HttpResolverRequest interface that
 * can be subclassed by developers wishing to adapt the request to a Resolver.
 * This class implements the Wrapper or Decorator pattern. Methods default to
 * calling through to the wrapped request object.
 * 
 * @see HttpResolverRequest
 * @since v 2.3
 */
public class HttpResolverRequestWrapper extends ResolverRequestWrapper implements
        HttpResolverRequest {

    /**
     * Constructs a request object wrapping the given request.
     * 
     * @throws IllegalArgumentException
     *             if the request is null
     */
    public HttpResolverRequestWrapper(HttpResolverRequest request) {
        super(request);
    }

    private HttpResolverRequest _getHttpResolverRequest() {
        return (HttpResolverRequest) super.getRequest();
    }

    /**
     * The default behavior of this method is to return getAuthType() on the
     * wrapped request object.
     */
    @Override
    public String getAuthType() {
        return this._getHttpResolverRequest().getAuthType();
    }

    /**
     * The default behavior of this method is to return getCookies() on the
     * wrapped request object.
     */
    @Override
    public Cookie[] getCookies() {
        return this._getHttpResolverRequest().getCookies();
    }

    /**
     * The default behavior of this method is to return getDateHeader(String
     * name) on the wrapped request object.
     */
    @Override
    public long getDateHeader(String name) {
        return this._getHttpResolverRequest().getDateHeader(name);
    }

    /**
     * The default behavior of this method is to return getHeader(String name)
     * on the wrapped request object.
     */
    @Override
    public String getHeader(String name) {
        return this._getHttpResolverRequest().getHeader(name);
    }

    /**
     * The default behavior of this method is to return getHeaders(String name)
     * on the wrapped request object.
     */
    @Override
    public Enumeration<String> getHeaders(String name) {
        return this._getHttpResolverRequest().getHeaders(name);
    }

    /**
     * The default behavior of this method is to return getHeaderNames() on the
     * wrapped request object.
     */
    @Override
    public Enumeration<String> getHeaderNames() {
        return this._getHttpResolverRequest().getHeaderNames();
    }

    /**
     * The default behavior of this method is to return getIntHeader(String
     * name) on the wrapped request object.
     */
    @Override
    public int getIntHeader(String name) {
        return this._getHttpResolverRequest().getIntHeader(name);
    }

    /**
     * The default behavior of this method is to return getMethod() on the
     * wrapped request object.
     */
    @Override
    public String getMethod() {
        return this._getHttpResolverRequest().getMethod();
    }

    /**
     * The default behavior of this method is to return getPathInfo() on the
     * wrapped request object.
     */
    @Override
    public String getPathInfo() {
        return this._getHttpResolverRequest().getPathInfo();
    }

    /**
     * The default behavior of this method is to return getPathTranslated() on
     * the wrapped request object.
     */
    @Override
    public String getPathTranslated() {
        return this._getHttpResolverRequest().getPathTranslated();
    }

    /**
     * The default behavior of this method is to return getContextPath() on the
     * wrapped request object.
     */
    @Override
    public String getContextPath() {
        return this._getHttpResolverRequest().getContextPath();
    }

    /**
     * The default behavior of this method is to return getQueryString() on the
     * wrapped request object.
     */
    @Override
    public String getQueryString() {
        return this._getHttpResolverRequest().getQueryString();
    }

    /**
     * The default behavior of this method is to return getRemoteUser() on the
     * wrapped request object.
     */
    @Override
    public String getRemoteUser() {
        return this._getHttpResolverRequest().getRemoteUser();
    }

    /**
     * The default behavior of this method is to return isUserInRole(String
     * role) on the wrapped request object.
     */
    @Override
    public boolean isUserInRole(String role) {
        return this._getHttpResolverRequest().isUserInRole(role);
    }

    /**
     * The default behavior of this method is to return getUserPrincipal() on
     * the wrapped request object.
     */
    @Override
    public java.security.Principal getUserPrincipal() {
        return this._getHttpResolverRequest().getUserPrincipal();
    }

    /**
     * The default behavior of this method is to return getRequestedSessionId()
     * on the wrapped request object.
     */
    @Override
    public String getRequestedSessionId() {
        return this._getHttpResolverRequest().getRequestedSessionId();
    }

    /**
     * The default behavior of this method is to return getRequestURI() on the
     * wrapped request object.
     */
    @Override
    public String getRequestURI() {
        return this._getHttpResolverRequest().getRequestURI();
    }

    /**
     * The default behavior of this method is to return getRequestURL() on the
     * wrapped request object.
     */
    @Override
    public StringBuffer getRequestURL() {
        return this._getHttpResolverRequest().getRequestURL();
    }

    /**
     * The default behavior of this method is to return getResolverPath() on the
     * wrapped request object.
     */
    @Override
    public String getResolverPath() {
        return this._getHttpResolverRequest().getResolverPath();
    }

    /**
     * The default behavior of this method is to return getSession(boolean
     * create) on the wrapped request object.
     */
    @Override
    public HttpSession getSession(boolean create) {
        return this._getHttpResolverRequest().getSession(create);
    }

    /**
     * The default behavior of this method is to return getSession() on the
     * wrapped request object.
     */
    @Override
    public HttpSession getSession() {
        return this._getHttpResolverRequest().getSession();
    }

    /**
     * The default behavior of this method is to return
     * isRequestedSessionIdValid() on the wrapped request object.
     */
    @Override
    public boolean isRequestedSessionIdValid() {
        return this._getHttpResolverRequest().isRequestedSessionIdValid();
    }

    /**
     * The default behavior of this method is to return
     * isRequestedSessionIdFromCookie() on the wrapped request object.
     */
    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return this._getHttpResolverRequest().isRequestedSessionIdFromCookie();
    }

    /**
     * The default behavior of this method is to return
     * isRequestedSessionIdFromURL() on the wrapped request object.
     */
    @Override
    public boolean isRequestedSessionIdFromURL() {
        return this._getHttpResolverRequest().isRequestedSessionIdFromURL();
    }

    /**
     * The default behavior of this method is to return
     * isRequestedSessionIdFromUrl() on the wrapped request object.
     * 
     * @deprecated As of Version 3.0 of the Java Resolver API
     */
    @Override
    @SuppressWarnings("dep-ann")
    // Spec API does not use @Deprecated
    public boolean isRequestedSessionIdFromUrl() {
        return this._getHttpResolverRequest().isRequestedSessionIdFromUrl();
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default behavior of this method is to return
     * {@link HttpResolverRequest#authenticate(HttpResolverResponse)}
     * on the wrapped request object.
     *
     * @since Resolver 3.0
     */
    @Override
    public boolean authenticate(HttpResolverResponse response)
            throws IOException, ResolverException {
        return this._getHttpResolverRequest().authenticate(response);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default behavior of this method is to return
     * {@link HttpResolverRequest#login(String, String)}
     * on the wrapped request object.
     *
     * @since Resolver 3.0
     */
    @Override
    public void login(String username, String password) throws ResolverException {
        this._getHttpResolverRequest().login(username, password);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default behavior of this method is to return
     * {@link HttpResolverRequest#logout()}
     * on the wrapped request object.
     *
     * @since Resolver 3.0
     */
    @Override
    public void logout() throws ResolverException {
        this._getHttpResolverRequest().logout();
    }

//    /**
//     * {@inheritDoc}
//     * <p>
//     * The default behavior of this method is to return
//     * {@link HttpResolverRequest#getParts()}
//     * on the wrapped request object.
//     *
//     * @since Resolver 3.0
//     */
//    @Override
//    public Collection<Part> getParts() throws IllegalStateException,
//            IOException, ResolverException {
//        return this._getHttpResolverRequest().getParts();
//    }
//
//    /**
//     * {@inheritDoc}
//     * <p>
//     * The default behavior of this method is to return
//     * {@link HttpResolverRequest#getPart(String)}
//     * on the wrapped request object.
//     *
//     * @since Resolver 3.0
//     */
//    @Override
//    public Part getPart(String name) throws IllegalStateException, IOException,
//            ResolverException {
//        return this._getHttpResolverRequest().getPart(name);
//    }
}
