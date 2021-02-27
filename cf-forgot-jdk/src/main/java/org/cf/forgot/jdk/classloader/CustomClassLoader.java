package org.cf.forgot.jdk.classloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @author 杨志伟
 * @date 2020/8/9
 */
public class CustomClassLoader {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                System.out.println("000000000000000000000000000000000000000000");
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }
    }

    public static void main(String args[]) throws Exception {
        URL url = CustomClassLoader.class.getClassLoader().getResource("./");
        System.out.println("url: " + url);
        //--
        String path = "/Users/yangzhiwei/Desktop/classes/";
        MyClassLoader classLoader = new MyClassLoader(path);
        String clazzName = "org.cf.forgot.jdk.classloader.ClassloaderTestBean";
        Class clazz = classLoader.loadClass(clazzName);
        Object obj = clazz.newInstance();
        Method helloMethod = clazz.getDeclaredMethod("hello", null);
        helloMethod.invoke(obj, null);

    }
}
