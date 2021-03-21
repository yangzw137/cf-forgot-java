package org.cf.common.model;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/17
 * @since todo
 */
public class Bean1 {
    private int i;
    private long l;
    private String s;
    private byte b;
    private char c;

    private Bean2 bean2;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public long getL() {
        return l;
    }

    public void setL(long l) {
        this.l = l;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public byte getB() {
        return b;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public Bean2 getBean2() {
        return bean2;
    }

    public void setBean2(Bean2 bean2) {
        this.bean2 = bean2;
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "i=" + i +
                ", l=" + l +
                ", s='" + s + '\'' +
                ", b=" + b +
                ", c=" + c +
                ", bean2=" + bean2 +
                '}';
    }
}
