package org.cf.forgot.jdk.classloader;

import java.util.Date;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/1/17
 * @since todo
 */
public class ClassloaderTestBean1 {

    private Date date = new Date(10000);

    public void hello() {
        System.out.println("ClassloaderTestBean say hello");
    }

    public void hhh(Date d){

    }

    public static void main(String[] args) {
        ClassloaderTestBean1 c = new ClassloaderTestBean1();
        c.hhh(c.date = new Date());
        System.out.println(c.date);
    }
}
