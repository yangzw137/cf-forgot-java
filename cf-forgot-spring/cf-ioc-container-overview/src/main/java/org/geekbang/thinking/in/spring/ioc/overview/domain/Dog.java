package org.geekbang.thinking.in.spring.ioc.overview.domain;

/**
 * @author cf
 * @date 2020/8/22
 */
public class Dog {

    private Long id;
    private int color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", color=" + color +
                '}';
    }
}
