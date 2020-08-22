package org.cf.forgot.lib.instrument.demo1;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author cf
 * @date 2020/8/21
 */
public class TestTransformer implements ClassFileTransformer {
    //目标类名称，  .分隔
    private String targetClassName;
    private String targetMethodName;

    public TestTransformer(String className, String methodName) {
        System.out.println("TestTransformer constructer ");
        this.targetMethodName = methodName;
        this.targetClassName = className;
        System.out.println("targetClassName: " + this.targetClassName);
        System.out.println("targetMethodName: " + this.targetMethodName);
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
            return classfileBuffer;
        } else {
            System.out.println("transform className: " + className + ", pineClassName: " + plainClassName);
        }

        //javassist的包名是用点分割的，需要转换下
        if (className != null && className.indexOf("/") != -1) {
            className = className.replaceAll("/", ".");
        }
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass mCtc = classPool.get(className);
            CtMethod ctMethod = mCtc.getDeclaredMethod(targetMethodName);
            ctMethod.insertBefore("{ System.out.println(\"berfor invoke method.........................\"); }");
            ctMethod.insertAfter("{ System.out.println(\"after invoke method.............................\"); }");

            String methodName = ctMethod.getName();
            String targetMethodName = "do" + methodName;
            ctMethod.setName(targetMethodName);

            StringBuilder proxyMethodBody = new StringBuilder();
            proxyMethodBody.append("public ");
            proxyMethodBody.append(" String ");
            proxyMethodBody.append(methodName);
            proxyMethodBody.append("()");
            proxyMethodBody.append("{");
            proxyMethodBody.append(" return ");
            proxyMethodBody.append(targetMethodName);
            proxyMethodBody.append("();");
            proxyMethodBody.append("}");

            mCtc.addMethod(CtMethod.make(proxyMethodBody.toString(), mCtc));
            return mCtc.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("transform end. className: " + className);
        return classfileBuffer;
    }
}