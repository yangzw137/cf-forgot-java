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

package com.jd.jtm.org.apache.http.impl.client;

import java.util.Collection;

import com.jd.jtm.org.apache.http.HttpStatus;
import com.jd.jtm.org.apache.http.annotation.Contract;
import com.jd.jtm.org.apache.http.annotation.ThreadingBehavior;
import com.jd.jtm.org.apache.http.auth.AUTH;
import com.jd.jtm.org.apache.http.client.config.RequestConfig;

/**
 * Default {@link org.apache.http.client.AuthenticationStrategy} implementation
 * for proxy host authentication.
 *
 * @since 4.2
 */
@Contract(threading = ThreadingBehavior.IMMUTABLE)
public class TargetAuthenticationStrategy extends AuthenticationStrategyImpl {

    public static final TargetAuthenticationStrategy INSTANCE = new TargetAuthenticationStrategy();

    public TargetAuthenticationStrategy() {
        super(HttpStatus.SC_UNAUTHORIZED, AUTH.WWW_AUTH);
    }

    @Override
    Collection<String> getPreferredAuthSchemes(final RequestConfig config) {
        return config.getTargetPreferredAuthSchemes();
    }

}
