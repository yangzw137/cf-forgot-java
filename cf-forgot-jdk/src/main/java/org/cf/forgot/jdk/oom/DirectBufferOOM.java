package org.cf.forgot.jdk.oom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * -Xmx128M -XX:MaxDirectMemorySize=100M
 *
 * <p>
 * @date 2020/10/10
 * @since
 */
public class DirectBufferOOM {

    private static List<ByteBuffer> list = new ArrayList<>();

    private static int MB = 1024 * 1024;

    public static void main(String[] args) {
        System.out.println("9/10: " + (double)2*1024*1024*1024 * 0.89);
        System.out.println("Runtime.getRuntime().maxMemory() " + Runtime.getRuntime().maxMemory() + " B");
        System.out.println("Runtime.getRuntime().maxMemory() " + (double)Runtime.getRuntime().maxMemory()/(double)1024 + " KB");
        System.out.println("Runtime.getRuntime().maxMemory() " + (double)Runtime.getRuntime().maxMemory()/(double)1024/(double)1024 + " MB");
        System.out.println("Runtime.getRuntime().maxMemory() " + (double)Runtime.getRuntime().maxMemory()/(double)1024/(double)1024/(double)1024 + " GB");
        System.out.println("VM.maxDirectMemory() " + (double) sun.misc.VM.maxDirectMemory()/(double)1024 + " KB");
        System.out.println("VM.maxDirectMemory() " + (double) sun.misc.VM.maxDirectMemory()/(double)1024/(double)1024 + " MB");
        System.out.println("VM.maxDirectMemory() " + (double) sun.misc.VM.maxDirectMemory()/(double)1024/(double)1024/(double)1024 + " GB");

        long counter = 0;
        while (true) {
            try {
                ByteBuffer buffer = ByteBuffer.allocateDirect(MB * 1);
                list.add(buffer);
                counter ++;
                //删除一个 ByteBuffer 对象后，可以继续
//                if (list.size() > 99) {
//                    list.remove(0);
//                    System.out.println("counter: " + counter);
//                }
            } catch (OutOfMemoryError error) {
                System.out.println("counter: " + counter + " MB");
                throw error;
            }
        }
    }
}
