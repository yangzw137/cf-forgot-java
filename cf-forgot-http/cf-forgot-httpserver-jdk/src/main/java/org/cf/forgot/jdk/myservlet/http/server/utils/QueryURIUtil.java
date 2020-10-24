package org.cf.forgot.jdk.myservlet.http.server.utils;


import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/10/24
 * @since todo
 */
public class QueryURIUtil {

    public static Map<String, String> formData(final String queryString, final String charset) {
        Map<String, String> resultMap = new HashMap<String, String>();
        if (StringUtils.isEmpty(queryString)) {
            return resultMap;
        }
        final String[] strs = queryString.trim().split("&");
        for (final String s : strs) {
            final String[] kv = s.split("=");
            if (kv.length < 2) {
                continue;
            }
            try {
                final String k = URLDecoder.decode(kv[0], charset);
                final String v = URLDecoder.decode(kv[1], charset);
                resultMap.put(k, v);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }
}
