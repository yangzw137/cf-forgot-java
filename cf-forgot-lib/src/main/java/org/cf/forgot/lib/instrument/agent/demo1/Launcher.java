package org.cf.forgot.lib.instrument.agent.demo1;

import com.sun.tools.attach.VirtualMachine;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.cf.forgot.lib.instrument.agent.model.HelloInstrument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author cf
 * @date 2020/8/21
 */
public class Launcher {
    private static Logger logger = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        logger.info("main...");
//        HelloInstrument hello = new HelloInstrument();
//        hello.sayHello();

//        MyAgent.retransforClasses(HelloInstrument.class);

        try {
            String jvmPid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            logger.info("Attaching to target JVM with PID: {}", jvmPid);
            VirtualMachine jvm = VirtualMachine.attach(jvmPid);
            jvm.loadAgent("/Users/yangzhiwei/code/javaprojects/cf-forgot-java/cf-forgot-lib/target/cf-forgot-lib-1.0-SNAPSHOT-assembly/lib/cf-forgot-lib-1.0-SNAPSHOT.jar");//agentFilePath为agent的路径
            jvm.detach();
            logger.info("Attached to target JVM and loaded Java agent successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        HelloInstrument hello2 = new HelloInstrument();
        hello2.sayHello();
    }

    private static void sleep(long n) {
        try {
            logger.info("sleep...");
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
            ctMethod.insertBefore("{ logger.info(\"berfor invoke method........22222222..........\"); }");
            ctMethod.insertAfter("{ logger.info(\"after invoke method............22222222...........\"); }");

            mCtc.addMethod(CtMethod.make(methodName, mCtc));
//            MyAgent.retransforClasses(HelloInstrument.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
