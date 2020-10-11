package org.cf.forgot.jdk.oom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * -Xmx=128M -XX:MaxDirectMemorySize=100M
 *
 * <p>
 * @date 2020/10/10
 * @since
 */
public class DirectBufferOOM {

    private static List<ByteBuffer> list = new ArrayList<>();

    public static void main(String[] args) {
        long counter = 0;
        while (true) {
            try {
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 1);
                list.add(buffer);
                counter ++;
            } catch (OutOfMemoryError error) {
                System.out.println("counter: " + counter);
                throw error;
            }
        }
    }
}
