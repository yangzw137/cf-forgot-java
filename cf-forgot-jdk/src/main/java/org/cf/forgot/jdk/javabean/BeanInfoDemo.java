package org.cf.forgot.jdk.javabean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * @author 杨志伟
 * @date 2020/7/25
 */
public class BeanInfoDemo {
    public static void main(String[] args) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

            Stream.of(beanInfo.getPropertyDescriptors())
                    .forEach(propertyDescriptor -> {
                        System.out.println(propertyDescriptor.toString());

                        if ("age".equals(propertyDescriptor.getName())) {
                            propertyDescriptor.setPropertyEditorClass(String2IntPropertyEditor.class);
                        }
                    });
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    static class String2IntPropertyEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(Integer.valueOf(text));
        }
    }
}
