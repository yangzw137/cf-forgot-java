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

import java.io.IOException;
import java.util.Collection;

/**
 * Provides a convenient implementation of the HttpResolverResponse interface
 * that can be subclassed by developers wishing to adapt the response from a
 * Resolver. This class implements the Wrapper or Decorator pattern. Methods
 * default to calling through to the wrapped response object.
 * 
 * @author Various
 * @since v 2.3
 * @see HttpResolverResponse
 */
public class HttpResolverResponseWrapper extends ResolverResponseWrapper
        implements HttpResolverResponse {

    /**
     * Constructs a response adaptor wrapping the given response.
     * 
     * @throws IllegalArgumentException
     *             if the response is null
     */
    public HttpResolverResponseWrapper(HttpResolverResponse response) {
        super(response);
    }

    private HttpResolverResponse _getHttpResolverResponse() {
        return (HttpResolverResponse) super.getResponse();
    }

    /**
     * The default behavior of this method is to call addCookie(Cookie cookie)
     * on the wrapped response object.
     */
    @Override
    public void addCookie(Cookie cookie) {
        this._getHttpResolverResponse().addCookie(cookie);
    }

    /**
     * The default behavior of this method is to call containsHeader(String
     * name) on the wrapped response object.
     */
    @Override
    public boolean containsHeader(String name) {
        return this._getHttpResolverResponse().containsHeader(name);
    }

    /**
     * The default behavior of this method is to call encodeURL(String url) on
     * the wrapped response object.
     */
    @Override
    public String encodeURL(String url) {
        return this._getHttpResolverResponse().encodeURL(url);
    }

    /**
     * The default behavior of this method is to return encodeRedirectURL(String
     * url) on the wrapped response object.
     */
    @Override
    public String encodeRedirectURL(String url) {
        return this._getHttpResolverResponse().encodeRedirectURL(url);
    }

    /**
     * The default behavior of this method is to call encodeUrl(String url) on
     * the wrapped response object.
     * 
     * @deprecated As of Version 3.0 of the Java Resolver API
     */
    @Override
    @SuppressWarnings("dep-ann")
    // Spec API does not use @Deprecated
    public String encodeUrl(String url) {
        return this._getHttpResolverResponse().encodeUrl(url);
    }

    /**
     * The default behavior of this method is to return encodeRedirectUrl(String
     * url) on the wrapped response object.
     * 
     * @deprecated As of Version 3.0 of the Java Resolver API
     */
    @Override
    @SuppressWarnings("dep-ann")
    // Spec API does not use @Deprecated
    public String encodeRedirectUrl(String url) {
        return this._getHttpResolverResponse().encodeRedirectUrl(url);
    }

    /**
     * The default behavior of this method is to call sendError(int sc, String
     * msg) on the wrapped response object.
     */
    @Override
    public void sendError(int sc, String msg) throws IOException {
        this._getHttpResolverResponse().sendError(sc, msg);
    }

    /**
     * The default behavior of this method is to call sendError(int sc) on the
     * wrapped response object.
     */
    @Override
    public void sendError(int sc) throws IOException {
        this._getHttpResolverResponse().sendError(sc);
    }

    /**
     * The default behavior of this method is to return sendRedirect(String
     * location) on the wrapped response object.
     */
    @Override
    public void sendRedirect(String location) throws IOException {
        this._getHttpResolverResponse().sendRedirect(location);
    }

    /**
     * The default behavior of this method is to call setDateHeader(String name,
     * long date) on the wrapped response object.
     */
    @Override
    public void setDateHeader(String name, long date) {
        this._getHttpResolverResponse().setDateHeader(name, date);
    }

    /**
     * The default behavior of this method is to call addDateHeader(String name,
     * long date) on the wrapped response object.
     */
    @Override
    public void addDateHeader(String name, long date) {
        this._getHttpResolverResponse().addDateHeader(name, date);
    }

    /**
     * The default behavior of this method is to return setHeader(String name,
     * String value) on the wrapped response object.
     */
    @Override
    public void setHeader(String name, String value) {
        this._getHttpResolverResponse().setHeader(name, value);
    }

    /**
     * The default behavior of this method is to return addHeader(String name,
     * String value) on the wrapped response object.
     */
    @Override
    public void addHeader(String name, String value) {
        this._getHttpResolverResponse().addHeader(name, value);
    }

    /**
     * The default behavior of this method is to call setIntHeader(String name,
     * int value) on the wrapped response object.
     */
    @Override
    public void setIntHeader(String name, int value) {
        this._getHttpResolverResponse().setIntHeader(name, value);
    }

    /**
     * The default behavior of this method is to call addIntHeader(String name,
     * int value) on the wrapped response object.
     */
    @Override
    public void addIntHeader(String name, int value) {
        this._getHttpResolverResponse().addIntHeader(name, value);
    }

    /**
     * The default behavior of this method is to call setStatus(int sc) on the
     * wrapped response object.
     */
    @Override
    public void setStatus(int sc) {
        this._getHttpResolverResponse().setStatus(sc);
    }

    /**
     * The default behavior of this method is to call setStatus(int sc, String
     * sm) on the wrapped response object.
     * 
     * @deprecated As of Version 3.0 of the Java Resolver API
     */
    @Override
    @SuppressWarnings("dep-ann")
    // Spec API does not use @Deprecated
    public void setStatus(int sc, String sm) {
        this._getHttpResolverResponse().setStatus(sc, sm);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation is to call
     * {@link HttpResolverResponse#getStatus()}
     * on the wrapper {@link HttpResolverResponse}.
     *
     * @since Resolver 3.0
     */
    @Override
    public int getStatus() {
        return this._getHttpResolverResponse().getStatus();
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation is to call
     * {@link HttpResolverResponse#getHeader(String)}
     * on the wrapper {@link HttpResolverResponse}.
     *
     * @since Resolver 3.0
     */
    @Override
    public String getHeader(String name) {
        return this._getHttpResolverResponse().getHeader(name);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation is to call
     * {@link HttpResolverResponse#getHeaders(String)}
     * on the wrapper {@link HttpResolverResponse}.
     *
     * @since Resolver 3.0
     */
    @Override
    public Collection<String> getHeaders(String name) {
        return this._getHttpResolverResponse().getHeaders(name);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The default implementation is to call
     * {@link HttpResolverResponse#getHeaderNames()}
     * on the wrapper {@link HttpResolverResponse}.
     *
     * @since Resolver 3.0
     */
    @Override
    public Collection<String> getHeaderNames() {
        return this._getHttpResolverResponse().getHeaderNames();
    }
}
