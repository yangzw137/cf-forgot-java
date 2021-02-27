/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package com.jd.jtm.org.apache.http.impl.cookie;

import java.util.List;

import com.jd.jtm.org.apache.http.FormattedHeader;
import com.jd.jtm.org.apache.http.Header;
import com.jd.jtm.org.apache.http.HeaderElement;
import com.jd.jtm.org.apache.http.annotation.Contract;
import com.jd.jtm.org.apache.http.annotation.ThreadingBehavior;
import com.jd.jtm.org.apache.http.cookie.Cookie;
import com.jd.jtm.org.apache.http.cookie.CookieOrigin;
import com.jd.jtm.org.apache.http.cookie.CookieSpec;
import com.jd.jtm.org.apache.http.cookie.MalformedCookieException;
import com.jd.jtm.org.apache.http.cookie.SM;
import com.jd.jtm.org.apache.http.cookie.SetCookie2;
import com.jd.jtm.org.apache.http.impl.cookie.BasicCommentHandler;
import com.jd.jtm.org.apache.http.impl.cookie.BasicDomainHandler;
import com.jd.jtm.org.apache.http.impl.cookie.BasicExpiresHandler;
import com.jd.jtm.org.apache.http.impl.cookie.BasicMaxAgeHandler;
import com.jd.jtm.org.apache.http.impl.cookie.BasicSecureHandler;
import com.jd.jtm.org.apache.http.impl.cookie.NetscapeDraftHeaderParser;
import com.jd.jtm.org.apache.http.impl.cookie.NetscapeDraftSpec;
import com.jd.jtm.org.apache.http.impl.cookie.RFC2109DomainHandler;
import com.jd.jtm.org.apache.http.impl.cookie.RFC2109VersionHandler;
import com.jd.jtm.org.apache.http.impl.cookie.RFC2965CommentUrlAttributeHandler;
import com.jd.jtm.org.apache.http.impl.cookie.RFC2965DiscardAttributeHandler;
import com.jd.jtm.org.apache.http.impl.cookie.RFC2965DomainAttributeHandler;
import com.jd.jtm.org.apache.http.impl.cookie.RFC2965PortAttributeHandler;
import com.jd.jtm.org.apache.http.impl.cookie.RFC2965VersionAttributeHandler;
import com.jd.jtm.org.apache.http.message.ParserCursor;
import com.jd.jtm.org.apache.http.util.Args;
import com.jd.jtm.org.apache.http.util.CharArrayBuffer;

/**
 * Default cookie specification that picks up the best matching cookie policy based on
 * the format of cookies sent with the HTTP response.
 *
 * @since 4.4
 */
@Contract(threading = ThreadingBehavior.SAFE)
public class DefaultCookieSpec implements CookieSpec {

    private final RFC2965Spec strict;
    private final RFC2109Spec obsoleteStrict;
    private final NetscapeDraftSpec netscapeDraft;

    DefaultCookieSpec(
            final RFC2965Spec strict,
            final RFC2109Spec obsoleteStrict,
            final NetscapeDraftSpec netscapeDraft) {
        this.strict = strict;
        this.obsoleteStrict = obsoleteStrict;
        this.netscapeDraft = netscapeDraft;
    }

    public DefaultCookieSpec(
            final String[] datepatterns,
            final boolean oneHeader) {
        this.strict = new RFC2965Spec(oneHeader,
                new RFC2965VersionAttributeHandler(),
                new BasicPathHandler(),
                new RFC2965DomainAttributeHandler(),
                new RFC2965PortAttributeHandler(),
                new BasicMaxAgeHandler(),
                new BasicSecureHandler(),
                new BasicCommentHandler(),
                new RFC2965CommentUrlAttributeHandler(),
                new RFC2965DiscardAttributeHandler());
        this.obsoleteStrict = new RFC2109Spec(oneHeader,
                new RFC2109VersionHandler(),
                new BasicPathHandler(),
                new RFC2109DomainHandler(),
                new BasicMaxAgeHandler(),
                new BasicSecureHandler(),
                new BasicCommentHandler());
        this.netscapeDraft = new NetscapeDraftSpec(
                new BasicDomainHandler(),
                new BasicPathHandler(),
                new BasicSecureHandler(),
                new BasicCommentHandler(),
                new BasicExpiresHandler(
                        datepatterns != null ? datepatterns.clone() : new String[]{NetscapeDraftSpec.EXPIRES_PATTERN}));
    }

    public DefaultCookieSpec() {
        this(null, false);
    }

    @Override
    public List<Cookie> parse(
            final Header header,
            final CookieOrigin origin) throws MalformedCookieException {
        Args.notNull(header, "Header");
        Args.notNull(origin, "Cookie origin");
        HeaderElement[] helems = header.getElements();
        boolean versioned = false;
        boolean netscape = false;
        for (final HeaderElement helem: helems) {
            if (helem.getParameterByName("version") != null) {
                versioned = true;
            }
            if (helem.getParameterByName("expires") != null) {
               netscape = true;
            }
        }
        if (netscape || !versioned) {
            // Need to parse the header again, because Netscape style cookies do not correctly
            // support multiple header elements (comma cannot be treated as an element separator)
            final NetscapeDraftHeaderParser parser = NetscapeDraftHeaderParser.DEFAULT;
            final CharArrayBuffer buffer;
            final ParserCursor cursor;
            if (header instanceof FormattedHeader) {
                buffer = ((FormattedHeader) header).getBuffer();
                cursor = new ParserCursor(
                        ((FormattedHeader) header).getValuePos(),
                        buffer.length());
            } else {
                final String s = header.getValue();
                if (s == null) {
                    throw new MalformedCookieException("Header value is null");
                }
                buffer = new CharArrayBuffer(s.length());
                buffer.append(s);
                cursor = new ParserCursor(0, buffer.length());
            }
            helems = new HeaderElement[] { parser.parseHeader(buffer, cursor) };
            return netscapeDraft.parse(helems, origin);
        } else {
            if (SM.SET_COOKIE2.equals(header.getName())) {
                return strict.parse(helems, origin);
            } else {
                return obsoleteStrict.parse(helems, origin);
            }
        }
    }

    @Override
    public void validate(
            final Cookie cookie,
            final CookieOrigin origin) throws MalformedCookieException {
        Args.notNull(cookie, "Cookie");
        Args.notNull(origin, "Cookie origin");
        if (cookie.getVersion() > 0) {
            if (cookie instanceof SetCookie2) {
                strict.validate(cookie, origin);
            } else {
                obsoleteStrict.validate(cookie, origin);
            }
        } else {
            netscapeDraft.validate(cookie, origin);
        }
    }

    @Override
    public boolean match(final Cookie cookie, final CookieOrigin origin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(origin, "Cookie origin");
        if (cookie.getVersion() > 0) {
            if (cookie instanceof SetCookie2) {
                return strict.match(cookie, origin);
            } else {
                return obsoleteStrict.match(cookie, origin);
            }
        } else {
            return netscapeDraft.match(cookie, origin);
        }
    }

    @Override
    public List<Header> formatCookies(final List<Cookie> cookies) {
        Args.notNull(cookies, "List of cookies");
        int version = Integer.MAX_VALUE;
        boolean isSetCookie2 = true;
        for (final Cookie cookie: cookies) {
            if (!(cookie instanceof SetCookie2)) {
                isSetCookie2 = false;
            }
            if (cookie.getVersion() < version) {
                version = cookie.getVersion();
            }
        }
        if (version > 0) {
            if (isSetCookie2) {
                return strict.formatCookies(cookies);
            } else {
                return obsoleteStrict.formatCookies(cookies);
            }
        } else {
            return netscapeDraft.formatCookies(cookies);
        }
    }

    @Override
    public int getVersion() {
        return strict.getVersion();
    }

    @Override
    public Header getVersionHeader() {
        return null;
    }

    @Override
    public String toString() {
        return "default";
    }

}
