package org.cf.forgot.lib.instrument.demo1;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cf
 * @date 2020/8/21
 */
public class MyAgent {

    private static String className = "org.cf.forgot.lib.instrument.model.HelloInstrument";
    private static String methodName = "sayHello";
    private static Instrumentation instrumentation;

    private static void setInstrumentation(Instrumentation inst) {
        if (inst != instrumentation) {
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
        if (instrumentation == null) {
            instrumentation = inst;
        }
    }

    private static Instrumentation getInstrumentation() {
        return instrumentation;
    }

    public static void retransforClasses(Class<?> clazzes) {
        try {
            getInstrumentation().retransformClasses(clazzes);
        } catch (UnmodifiableClassException e) {
            e.printStackTrace();
        }
    }

    public static void agentmain(String args, Instrumentation instrumentation) {
        System.out.println("agentmain start");
        try {
            System.out.println("className: " + className);
            List<Class> needRetransFormClasses = new LinkedList<>();
            Class[] loadedClass = instrumentation.getAllLoadedClasses();
            for (int i = 0; i < loadedClass.length; i++) {
                if (loadedClass[i].getName().equals(className)) {
                    needRetransFormClasses.add(loadedClass[i]);
                }
            }

            instrumentation.addTransformer(new TestTransformer(className, methodName));
            instrumentation.retransformClasses(needRetransFormClasses.toArray(new Class[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("agentmain end");
    }

    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("premain start");
        setInstrumentation(instrumentation);
        instrumentation.addTransformer(new TestTransformer(className, methodName));
        System.out.println("premain end");
    }

}
