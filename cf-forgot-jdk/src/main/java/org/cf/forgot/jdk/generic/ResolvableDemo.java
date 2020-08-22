package org.cf.forgot.jdk.generic;

import org.cf.forgot.jdk.generic.beans.AaClass;
import org.springframework.core.ResolvableType;

/**
 * @author 杨志伟
 * @date 2020/7/29
 */
public class ResolvableDemo {

    public static void main(String[] args) {
        ResolvableType resolvableType = ResolvableType.forClass(AaClass.class);
        resolvableType.asMap();
        System.out.println("------" + resolvableType.getGeneric(0).resolve());
        System.out.println("------" + resolvableType.getSuperType().getGeneric(0));
        System.out.println("------" + resolvableType.getSuperType().getGeneric(0).resolve());

    }
}
