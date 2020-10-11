package org.cf.forgot.jdk.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Description:
 *
 * jvm parameters:  -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 *
 * <p>
 * @date 2020/10/10
 * @since
 */
public class MetaspaceOOM {

    static class OOMObject{
//        public OOMObject(){}
    }

    public static void main(String[] args) {
        long counter = 0;
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(o, objects);
                }
            });
            try {
                Object obj = enhancer.create();
                counter ++;
            } catch (OutOfMemoryError error) {
                System.out.println("counter: " + counter);
                error.printStackTrace();
                throw error;
            }
        }
    }
}
