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

import com.jd.jtm.org.apache.http.annotation.Contract;
import com.jd.jtm.org.apache.http.annotation.ThreadingBehavior;
import com.jd.jtm.org.apache.http.client.utils.DateUtils;
import com.jd.jtm.org.apache.http.cookie.CommonCookieAttributeHandler;
import com.jd.jtm.org.apache.http.impl.cookie.BasicDomainHandler;
import com.jd.jtm.org.apache.http.impl.cookie.BasicExpiresHandler;
import com.jd.jtm.org.apache.http.impl.cookie.BasicMaxAgeHandler;
import com.jd.jtm.org.apache.http.impl.cookie.BasicSecureHandler;
import com.jd.jtm.org.apache.http.impl.cookie.RFC6265CookieSpecBase;

/**
 * Standard {@link org.apache.http.cookie.CookieSpec} implementation that enforces syntax
 * and semantics of the well-behaved profile of the HTTP state management specification
 * (RFC 6265, section 4).
 *
 * @since 4.4
 */
@Contract(threading = ThreadingBehavior.SAFE)
public class RFC6265StrictSpec extends RFC6265CookieSpecBase {

    final static String[] DATE_PATTERNS = {
        DateUtils.PATTERN_RFC1123,
        DateUtils.PATTERN_RFC1036,
        DateUtils.PATTERN_ASCTIME
    };

    public RFC6265StrictSpec() {
        super(new BasicPathHandler(),
                new BasicDomainHandler(),
                new BasicMaxAgeHandler(),
                new BasicSecureHandler(),
                new BasicExpiresHandler(DATE_PATTERNS));
    }

    RFC6265StrictSpec(final CommonCookieAttributeHandler... handlers) {
        super(handlers);
    }

    @Override
    public String toString() {
        return "rfc6265-strict";
    }

}