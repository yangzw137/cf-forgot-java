package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.concurrent.TimeUnit;

/**
 * @author cf
 * @date 2020/8/29
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();

//        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
//        System.out.println("userFactory: " + userFactory);

        System.out.println("");
        applicationContext.close();
//        sleep(5);
        System.out.println("befor garbage collection");
        System.gc();
        sleep(2);
        System.out.println("after garbage collection");
        createBigObject();

        sleep(2);
    }

    public static void createBigObject() {
        int size = Integer.MAX_VALUE/200;
        Byte n[] = new Byte[size];
        int index = 0;
        while(true) {
            n[index] = new Byte("12");
            index++;
            if (index >= size) {
                return;
            }
        }
    }

    public static void sleep(long n) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
