package org.cf.forgot.jdk.lifecycle.initialization;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/23
 * @since todo
 */
public class MainClass {

    public static void main(String[] args) {
//        initiativeRefer();

        userConstant();

        definitionArray();

        getClassLoader();

        userStaticVar();

        passivityReferSubclassReferSuperStaticVar();
        //实例化
//        System.out.printf("new SubClass(): %s\n", new SubClass());
    }

    private static void getClassLoader() {
        System.out.println("--------------------------------");
        System.out.println(SuperClass.class.getClassLoader());
    }

    private static void userStaticVar() {
        System.out.println("--------------------------------");
        System.out.printf("SuperClass variable a: %d\n", SuperClass.a);
    }

    /**
     * 主动引用
     */
    private static void initiativeRefer() {
        System.out.println("--------------------------------");
        //主动引用触发类初始化时机： new、getstatic、putstatic、invokestatic、reflect
        System.out.println(SuperClass.a);
    }

    /**
     * 被动引用: 通过子类引用父类的静态字段，不会导致子类初始化。
     */
    private static void passivityReferSubclassReferSuperStaticVar() {
        System.out.printf("-------------------\nprint SubClass.a: %d \n\n---------------\n\n", SubClass.a);
    }

    /**
     * 被动引用: 通过定义数组引用类，不会触发类初始化。该过程会对数组进行初始化，数组类是由 jvm 生成的，直接继承 Object 类，其中包含数组的属性和方法。
     */
    private static void definitionArray() {
        System.out.println("--------------------------------");
        SuperClass[] ss = new SuperClass[10];
        System.out.printf("use SuperClass definition a array, length: %d\n", ss.length);
    }

    /**
     * 使用 类 常量
     * 常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化。
     */
    private static void userConstant() {
        System.out.println("--------------------------------");
        System.out.printf("use SuperClass constant c: %d\n", SuperClass.c);
    }

}
