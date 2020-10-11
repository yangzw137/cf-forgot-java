package org.cf.forgot.jdk.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * -Xmx10M
 *
 * <p>
 * @date 2020/10/10
 * @since
 */
public class HeapOOM {

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        long counter = 0;
        while (true) {
            try {
                list.add(new Long(counter++));
            } catch (OutOfMemoryError error) {
                System.out.println("counter: " + counter);
                throw error;
            }
        }
    }
}
