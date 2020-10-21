package org.cf.forgot.jdk.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Description:
 * <p>
 *
 * @date 2020/10/16
 * @since
 */
public class UnsafeDemo {
    private int n = 10;

    public static void main(String[] args) {
        UnsafeDemo demo = new UnsafeDemo();

        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            int result = unsafe.getAndSetInt(demo, unsafe.objectFieldOffset(UnsafeDemo.class.getDeclaredField("n")), 11);
            System.out.println("result: " + result);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
