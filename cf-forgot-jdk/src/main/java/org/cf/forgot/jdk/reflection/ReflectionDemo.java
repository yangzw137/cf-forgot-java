package org.cf.forgot.jdk.reflection;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/4
 * @since todo
 */
public class ReflectionDemo {

    public static void main(String[] args) {
        arrayDemo();
        methodDemo();
        isInstance();
        changeB2A();
        fieldReflectDemo();
        constructorReflectDemo();
        reflect1();
    }

    private static void arrayDemo() {
        int[] a = new int[3];
        int[] b = new int[]{4, 5, 5};
        int[][] c = new int[3][2];
        String[] d = new String[]{"jjj", "kkk"};

        System.out.printf("int[] a == int[]b: %b\n", a == b);
        System.out.printf("a.class == b.class: %b\n", a.getClass() == b.getClass());
        System.out.printf("a.getClass(): %s\n", a.getClass());
        System.out.printf("a.getClass().getName(): %s\n", a.getClass().getName());

        System.out.printf("a.getClass().getSuperClass(): %s\n", a.getClass().getSuperclass());
        System.out.printf("b.getClass().getSuperClass(): %s\n", b.getClass().getSuperclass());

        Object obj1 = a;
        Object obj2 = b;
        Object[] obj3 = c;
        Object obj31 = c;
        Object obj32 = obj3;
        Object obj4 = d;

        List list = Arrays.asList(b);
        System.out.printf("Arrays.asList(b): %s\n",list);
        Integer[] e = new Integer[]{4, 5, 5};
        System.out.printf("Arrays.asList(e): %s\n", Arrays.asList(e));
        System.out.printf("Arrays.asList(d): %s\n", Arrays.asList(d));

        System.out.printf("Arrays.class.getDeclaredClasses(): %s\n", Arrays.class.getDeclaredFields());
        System.out.println("arrayDemo......");
    }

    private static void printFields(Field[] fields) {
        for (Field f : fields) {
            System.out.printf("Field: %s\n", f);
        }
    }

    private static void methodDemo() {
        String str = "abcdef";
        Class cls = String.class;
        try {
            Method method = cls.getMethod("charAt", int.class);
            Character c = (Character) method.invoke(str, 2);
            System.out.printf("char at 2 is: %c\n", c);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("methodDemo......");
    }

    private static void isInstance() {
        S s = new S();
        Class clazz = s.getClass();
        System.out.printf("Integer object is instance of S: %b\n", clazz.isInstance(new Integer(1)));
        System.out.printf("S object is instance os S: %b\n", clazz.isInstance(new S()));
        System.out.println("isInstance......");
    }

    private static void changeB2A() {
        ReflectPointer point = new ReflectPointer(3, 4);
        Field[] fields = point.getClass().getDeclaredFields();
        System.out.printf("before change b to a: %s\n", point);
        for (Field f : fields) {
            if (f.getType() == String.class) {
                if (Modifier.isPublic(f.getModifiers())) {
                    f.setAccessible(true);
                }
                try {
                    String old = (String) f.get(point);
                    String newStr = old.replace("b", "a");
                    f.set(point, newStr);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.printf("after change b to a: %s \n", point);
        System.out.println("changeB2A..........");
    }

    private static void fieldReflectDemo() {
        ReflectPointer point = new ReflectPointer(3, 4);
        try {
            Field fx = point.getClass().getDeclaredField("x");
            fx.setAccessible(true);
            int x = (int) fx.get(point);
            System.out.printf("point: %s\n", point);
            System.out.printf("point.x: %d\n", x);

            Field fser = point.getClass().getDeclaredField("y");
            fser.setAccessible(true);
            int y = (int) fser.get(point);
            System.out.printf("point.y: %d\n", y);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("fieldReflectDemo..........");
    }

    private static void constructorReflectDemo() {
        try {
            Constructor[] cs = String.class.getConstructors();
            for (Constructor c : cs) {
                System.out.printf("String.class.getConstructors: %s\n", c);
            }
            System.out.printf("String.class.getConstructor(byte[].class): %s\n", String.class.getConstructor(byte[].class));
            System.out.printf("String.class.getConstructor(StringBuffer.class): %s\n", String.class.getConstructor(StringBuffer.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("constructorReflectDemo.....................");
    }

    private static void reflect1() {
        String str = "abc";
        Class clazz1 = str.getClass();
        Class clazz2 = String.class;
        Class clazz3 = null;
        try {
            clazz3 = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.printf("int.class: %s\n", int.class);
        System.out.printf("int.class.getTypeName(): %s\n", int.class.getTypeName());
        System.out.printf("str.getClass() == String.class: %b \n", clazz1 == clazz2);
        System.out.printf("str.getClass() == Class.forName(\"java.lang.String\"): %b \n", clazz1 == clazz3);

        System.out.printf("String.class.isPrimitive(): %b \n", String.class.isPrimitive());
        System.out.printf("int.class.isPrimitive() = %b\n", int.class.isPrimitive());
        System.out.printf("int.class == Integer.class: %b\n", int.class == Integer.class);
        System.out.printf("int.class == Integer.TYPE: %b\n", int.class == Integer.TYPE);
        System.out.printf("int[].class.isPrimitive(): %b\n", int[].class.isPrimitive());
        System.out.printf("int[].class.isArray(): %b\n", int[].class.isArray());
        System.out.println("reflect1.....................");
    }

    static class ReflectPointer {

        private int x = 0;
        public int y = 0;
        public String str1 = "ball";
        public String str2 = "basketball";
        public String str3 = "itcat";

        public ReflectPointer(int x, int y) {//alt + shift +s相当于右键source
            super();
            // TODO Auto-generated constructor stub
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "ReflectPointer [str1=" + str1 + ", str2=" + str2 + ", str3="
                    + str3 + "]";
        }
    }

    static class S {

    }
}
