package org.cf.forgot.jdk.java.lang;

import java.util.ArrayList;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/27
 * @since todo
 */
public class ClassDemo {
    private static String ss = new String();

    private static class Bean1 {
        static class Bean2 {

        }
        class Bean3 {
            class Bean31 {

            }
        }
    }

    public static void main(String[] args) {
        class Bean4 {

        }

        String s = new String();
        System.out.println("string declaringClass: " + s.getClass().getDeclaringClass());
        System.out.println("string declaringClass: " + ss.getClass().getDeclaringClass());
        System.out.println("string declaringClass: " + String.class.getDeclaringClass());
        System.out.println("String EnclosingClass: " + String.class.getEnclosingClass());
        System.out.println();
        System.out.println("ClassDemo declaringClass: " + ClassDemo.class.getDeclaringClass());

        System.out.println();
        System.out.println("Bean1 declaringClass: " + Bean1.class.getDeclaringClass());
        System.out.println("Bean1 EnclosingClass: " + Bean1.class.getEnclosingClass());

        System.out.println();
        System.out.println("Bean1.Bean2 declaringClass: " + Bean1.Bean2.class.getDeclaringClass());
        System.out.println("Bean1.Bean2 EnclosingClass: " + Bean1.Bean2.class.getEnclosingClass());

        System.out.println();
        System.out.println("Bean1.Bean3 declaringClass: " + Bean1.Bean3.class.getDeclaringClass());
        System.out.println("Bean1.Bean3 EnclosingClass: " + Bean1.Bean3.class.getEnclosingClass());

        System.out.println();
        System.out.println("Bean1.Bean31 declaringClass: " + Bean1.Bean3.Bean31.class.getDeclaringClass());
        System.out.println("Bean1.Bean31 EnclosingClass: " + Bean1.Bean3.Bean31.class.getEnclosingClass());

        System.out.println();
        System.out.println("Bean4 declaringClass: " + Bean4.class.getDeclaringClass());
        System.out.println("Bean4 EnclosingClass: " + Bean4.class.getEnclosingClass());

        Class clazz = new ArrayList<String>(){
        }.getClass();

        System.out.println();
        System.out.println("anonymous declaringClass: " + clazz.getDeclaringClass());
        System.out.println("anonymous EnclosingClass: " + clazz.getEnclosingClass());

    }
}
