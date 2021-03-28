package org.cf.forgot.jdk.java.util;

import java.util.StringJoiner;
import java.util.stream.IntStream;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/21
 * @since todo
 */
public class StringJoinerDemo {

    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner(",");

        IntStream.range(1,10).forEach(i -> joiner.add(i+""));

        System.out.println(joiner.toString());

    }
}
