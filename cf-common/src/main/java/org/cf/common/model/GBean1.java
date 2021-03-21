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
public class GBean1 <T>{

    private T inner;

    private String name;

    public T getInner() {
        return inner;
    }

    public void setInner(T inner) {
        this.inner = inner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GBean1{" +
                "inner=" + inner +
                ", name='" + name + '\'' +
                '}';
    }
}
