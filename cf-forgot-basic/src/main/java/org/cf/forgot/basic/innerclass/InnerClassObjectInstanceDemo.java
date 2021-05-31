package org.cf.forgot.basic.innerclass;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/29
 * @since todo
 */
public class InnerClassObjectInstanceDemo {

    private int number = 10;

    class NoStaticInner {

        private int number = 200;

        private void paint() {
            System.out.printf("number: %d\n", number);
            System.out.printf("this.number: %d\n", this.number);
            System.out.printf("InnerClassObjectInstanceDemo.this.number: %d\n", InnerClassObjectInstanceDemo.this.number);
        }
    }

    static class StaticInner {
        private int number = 300;
    }

    public static void main(String[] args) {
        //非 static 内部类实例化必须先实例化外部类，再用外部类的对象去实例化内部类
        InnerClassObjectInstanceDemo out = new InnerClassObjectInstanceDemo();
        NoStaticInner noStaticInner = out.new NoStaticInner();

        //static 内部类实例化可以直接使用内部类实例化，不需要外部类的对象
        StaticInner staticInner = new StaticInner();
    }
}
