package org.cf.forgot.lib.instrument.agent.demo1;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author cf
 * @date 2020/8/21
 */
public class TestTransformer implements ClassFileTransformer {
    private static Logger logger = LoggerFactory.getLogger(TestTransformer.class);

    //目标类名称，  .分隔
    private String targetClassName;
    private String targetMethodName;
    private String mark = "";

    public TestTransformer(String className, String methodName, final String mark) {
        System.out.println("TestTransformer constructer");
        logger.info("TestTransformer constructer");
        this.targetMethodName = methodName;
        this.targetClassName = className;
        this.mark = mark;
        logger.info("targetClassName: {}, targetMethodName: {}, mark: {}",
                this.targetClassName, this.targetMethodName, this.mark);
    }

    /**
     * 类加载时会执行该函数，其中参数 classfileBuffer为类原始字节码，返回值为目标字节码，className为/分隔
     *
     * @param loader
     * @param className
     * @param classBeingRedefined
     * @param protectionDomain
     * @param classfileBuffer
     * @return
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        //判断类名是否为目标类名
        String plainClassName = className.replaceAll("/", ".");
        if (!plainClassName.equals(targetClassName)) {
//            System.out.println("1 transform targetClassName: " + targetClassName + ", pineClassName: " +
//            plainClassName);
//            logger.info("1 transform targetClassName: " + targetClassName + ", pineClassName: " + plainClassName);
            return classfileBuffer;
        }

        logger.info("2 [transform className: {}, plainClassName: {},targetMethodName: {}] \n",
                className, plainClassName, targetMethodName);
        System.out.printf("2 [transform className: %s, plainClassName: %s,targetMethodName: %s] \n",
                className, plainClassName, targetMethodName);

        try {
            logger.info("get CtClass Object 1");
            //通过包名获取类文件
            CtClass mCtc = ClassPool.getDefault().get(plainClassName);
            logger.info("get ctMethod");
            //获得指定方法名的方法
            CtMethod ctMethod = mCtc.getDeclaredMethod(targetMethodName);
            //在方法执行前插入代码
            logger.info("insert before");
            ctMethod.insertBefore("{ logger.info(\"berfor invoke method, mark: {}.........................\", \""
                    + this.mark + "\"" +
                    "); }");
            logger.info("insert after");
            ctMethod.insertAfter("{ logger.info(\"after invoke method.............................\"); }");

//            String methodName = ctMethod.getName();
//            String targetMethodName = "do" + methodName;
//            ctMethod.setName(targetMethodName);
//
//            StringBuilder proxyMethodBody = new StringBuilder();
//            proxyMethodBody.append("public ");
//            proxyMethodBody.append(" String ");
//            proxyMethodBody.append(methodName);
//            proxyMethodBody.append("()");
//            proxyMethodBody.append("{");
//            proxyMethodBody.append(" return ");
//            proxyMethodBody.append(targetMethodName);
//            proxyMethodBody.append("();");
//            proxyMethodBody.append("}");
//            mCtc.addMethod(CtMethod.make(proxyMethodBody.toString(), mCtc));

            return mCtc.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("", e);
        } catch (Throwable e) {
            e.printStackTrace();
            logger.error("", e);
        } finally {
            logger.info("transform end. plainClassName: {}", plainClassName);
        }
        return classfileBuffer;
    }
}