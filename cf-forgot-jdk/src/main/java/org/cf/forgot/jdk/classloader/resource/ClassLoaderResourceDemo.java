package org.cf.forgot.jdk.classloader.resource;

import java.io.*;
import java.util.Arrays;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/5
 * @since todo
 */
public class ClassLoaderResourceDemo {

    public static void main(String[] args) throws IOException {
        System.out.printf(" (1<<31): %d\n", (1<<31));
        System.out.printf(" (1<<31)+1: %d\n", (1<<31)+1);
        System.out.printf(" Integer.MAX_VALUE: %d\n", Integer.MAX_VALUE);

        System.exit(0);
        System.out.printf("2147483647: %b\n", 2<<31 - 1 == 2147483647);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.printf("classLoader: %s\n", classLoader);
        System.out.printf("classLoader.getParent(): %s\n", classLoader.getParent().toString());
        System.out.printf("classLoader.getParent().getParent(): %s\n", classLoader.getParent().getParent());
        String name = "org/cf/forgot/jdk/classloader/CustomClassLoader.class";
        System.out.printf("URL : [%s] \n", classLoader.getResource(name));
        name = "ClassLoaderResourceDemo.class";
        name = "/logback.xml";
        System.out.printf("ClassLoaderResourceDemo.class.getResource(\"./\"): [%s]\n", ClassLoaderResourceDemo.class.getResource("./"));
        System.out.printf("ClassLoaderResourceDemo.class.getResource(\"/\"): [%s]\n", ClassLoaderResourceDemo.class.getResource("/"));

        System.out.printf("classLoader.getResource(\"./\"): [%s]\n", classLoader.getResource("./"));
        System.out.printf("classLoader.getResource(\"/\"): [%s]\n", classLoader.getResource("/"));
        System.out.printf("classLoader.getResource(\"../\"): [%s]\n", classLoader.getResource("../"));

        System.out.printf("classLoader.getParent().getResource(\"./\"): [%s]\n", classLoader.getParent().getResource("./"));
        System.out.printf("classLoader.getParent().getResource(\"/\"): [%s]\n", classLoader.getParent().getResource("/"));

        System.out.printf("ClassLoader.getSystemClassLoader(): [%s]\n", ClassLoader.getSystemClassLoader());
        System.out.printf("ClassLoader.getSystemClassLoader().getResource(\"./\"): [%s]\n", ClassLoader.getSystemClassLoader().getResource("./"));

//        InputStream in = ClassLoaderResourceDemo.class.getResourceAsStream("/logback.xml");
        InputStream in = classLoader.getResourceAsStream("./logback.xml");
        if (in != null) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

//            byte[] bytes = new byte[10];
//            while (in.read(bytes) > 0) {
//                out.write(bytes);
//            }
            byte[] bytes = getBytes(in);
            out.write(bytes);
            System.out.printf("content: [%s]\n", out.toString());
            in.close();
        }


    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        boolean interrupted = Thread.interrupted();

        int available;
        while(true) {
            try {
                available = inputStream.available();
                break;
            } catch (InterruptedIOException var22) {
                Thread.interrupted();
                interrupted = true;
            }
        }

        byte[] bytes;
        try {
            bytes = new byte[0];
            if (available == -1) {
                available = 2147483647;
            }

            int length;
            for(int off = 0; off < available; off += length) {
                int remain;
                if (off >= bytes.length) {
                    remain = Math.min(available - off, bytes.length + 1024);
                    if (bytes.length < off + remain) {
                        bytes = Arrays.copyOf(bytes, off + remain);
                    }
                } else {
                    remain = bytes.length - off;
                }

                length = 0;

                try {
                    length = inputStream.read(bytes, off, remain);
                } catch (InterruptedIOException var20) {
                    Thread.interrupted();
                    interrupted = true;
                }

                if (length < 0) {
                    if (available != 2147483647) {
                        throw new EOFException("Detect premature EOF");
                    }

                    if (bytes.length != off) {
                        bytes = Arrays.copyOf(bytes, off);
                    }
                    break;
                }
            }
        } finally {
            try {
                inputStream.close();
            } catch (InterruptedIOException var18) {
                interrupted = true;
            } catch (IOException var19) {
            }

            if (interrupted) {
                Thread.currentThread().interrupt();
            }

        }

        return bytes;
    }
}
