package org.geekbang.thinking.in.spring.ioc.overview.domain;

import org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * @author cf
 * @date 2020/8/15
 */
@Super
public class SuperUser extends User{
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }

    @Override
    public String toSampleString() {
        return "SuperUser{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                '}';
    }
}
