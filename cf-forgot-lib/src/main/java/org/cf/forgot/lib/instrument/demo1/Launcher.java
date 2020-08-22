package org.cf.forgot.lib.instrument.demo1;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.cf.forgot.lib.instrument.model.HelloInstrument;

import java.util.concurrent.TimeUnit;

/**
 * @author cf
 * @date 2020/8/21
 */
public class Launcher {

    public static void main(String[] args) {
        System.out.println("main...");
        HelloInstrument hello = new HelloInstrument();
        hello.sayHello();

//        MyAgent.retransforClasses(HelloInstrument.class);

        HelloInstrument hello2 = new HelloInstrument();
        hello2.sayHello();
    }

    private static void sleep(long n) {
        try {
            System.out.println("sleep...");
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void modifyClass() {
        try {
            String className = "org.cf.forgot.lib.instrument.model.HelloInstrument";
            String methodName = "sayHello";
            ClassPool classPool = ClassPool.getDefault();
            CtClass mCtc = classPool.get(className);
            CtMethod ctMethod = mCtc.getDeclaredMethod(methodName);
            ctMethod.insertBefore("{ System.out.println(\"berfor invoke method........22222222..........\"); }");
            ctMethod.insertAfter("{ System.out.println(\"after invoke method............22222222...........\"); }");

            mCtc.addMethod(CtMethod.make(methodName, mCtc));
            MyAgent.retransforClasses(HelloInstrument.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
