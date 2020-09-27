package org.cf.forgot.lib.instrument.agent.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cf
 * @date 2020/8/21
 */
public class MyAgent {
    private static Logger logger = LoggerFactory.getLogger(MyAgent.class);

    private static String className = "org.cf.forgot.lib.instrument.agent.model.HelloInstrument";
    private static String methodName = "sayHello";
    private static ClassFileTransformer classFileTransformer;


//    public static void retransforClasses(Class<?> clazzes) {
//        try {
//            getInstrumentation().retransformClasses(clazzes);
//        } catch (UnmodifiableClassException e) {
//            e.printStackTrace();
//        }
//    }

    public static void agentmain(String args, Instrumentation instrumentation) {
        logger.info("agentmain start");

        try {
            logger.info("className: " + className);
            List<Class> needRetransFormClasses = new LinkedList<>();
            Class[] loadedClass = instrumentation.getAllLoadedClasses();
            for (int i = 0; i < loadedClass.length; i++) {
                if (loadedClass[i].getName().equals(className)) {
                    needRetransFormClasses.add(loadedClass[i]);
                }
            }
//            instrumentation.removeTransformer(classFileTransformer);
            classFileTransformer = new TestTransformer(className, methodName, "-------- agentmain ----");
            instrumentation.addTransformer(classFileTransformer);
            instrumentation.retransformClasses(needRetransFormClasses.toArray(new Class[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("agentmain end");
    }

    public static void premain(String args, Instrumentation instrumentation) {
//        logger.info("premain start");
//        classFileTransformer = new TestTransformer(className, methodName, "************ " +
//                "premain **************");
//        instrumentation.addTransformer(classFileTransformer);
//        logger.info("premain end");
    }

}
