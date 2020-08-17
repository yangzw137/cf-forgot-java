package org.cf.forgot.jdk.string;

/**
 * @author 杨志伟
 * @date 2020/7/28
 */
public class Intern1 {

    public static void main(String[] args) {
        inter0();
        System.out.println("--------------------------");
        String a = new String("1");
        a.intern();
        String b = new String("1").intern();
        System.out.println("a=a.intern: " + (a == a.intern()));
        System.out.println("b=a.intern: " + (b == b.intern()));
//        String s = new StringBuilder().append("a").append("b").toString();
        String s = "a" + "b";
        System.out.println("s = s.inten: " + (s == s.intern()));
        String s2 = "ab";
        System.out.println("s = s2: " + (s == s2));
        String s3 = new String("ab");
        System.out.println("s = s3: " + (s == s3.intern()));
        inter1();
        System.out.println("--------------------------");
        intern2();
    }

    static void inter0() {
        String s1 = new String("com.jd.ssa.service.SsoService").intern();
        String s2 = new String("com.jd.ssa.service.SsoService").intern();
        String s3 = new String("com.jd.ssa.service.SsoService").intern();
        final String s4 = new String("com.jd.ssa.service.SsoService");
        final String s5 = new String("com.jd.ssa.service.SsoService");
        System.out.println("s1 = s2: " + (s1 == s2));
        System.out.println("s1 = s3: " + (s1 == s3));
        System.out.println("s2 = s3: " + (s2 == s3));
        System.out.println("s1 = s4: " + (s1 == s4));
        System.out.println("s4 = s5: " + (s4 == s5));
    }

    static void inter1() {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        String si = s.intern();
        System.out.print((s == s.intern()) + "; ");
        System.out.print((s == s2) + "; ");
        String s3 = new String("2") + new String("3");
        s3.intern();
        String s4 = "23";
        System.out.println(s3 == s4);
    }

    static void intern2() {
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.print((s == s2) + "; ");
        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);
    }
}
