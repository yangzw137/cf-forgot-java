package org.cf.forgot.lib.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author cf
 * @date 2020/8/21
 */
public class CGlibDynamicProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行前...");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("执行后...");
        return object;
    }

    static class PersonService {
        public PersonService() {
            System.out.println("PersonService构造");
        }

        public void setPerson() {
            System.out.println("PersonService:setPerson");
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback(new CGlibDynamicProxy());
        PersonService proxy = (PersonService) enhancer.create();
        proxy.setPerson();
    }
}
