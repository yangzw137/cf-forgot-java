/**
 * JsonUtils.java Created on 2015/1/8 10:01
 * <p>
 * Copyright (c) 2015 by www.jd.com.
 */
package org.cf.forgot.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.cf.forgot.fastjson.json.LabelsDeserializer;
import org.cf.forgot.fastjson.json.LabelsSerializer;
import org.cf.forgot.fastjson.labels.Labels;

import java.lang.reflect.Type;

/**
 * Title: 包装了JSON的基本行为，隐藏后面的实现 <br>
 * <p/>
 * Description: <br>
 * <p/>
 * Company: <a href=www.jd.com>京东</a><br>
 *
 * @author <a href=mailto:zhanggeng@jd.com>章耿</a>
 */
public class JsonUtils {

    static {
        Object labelsSerializer = SerializeConfig.getGlobalInstance().get(Labels.class);
        if (!(labelsSerializer instanceof LabelsSerializer)) {
            SerializeConfig.getGlobalInstance().put(Labels.class, LabelsSerializer.instance);
        }

        Object labelsDeserializer = ParserConfig.getGlobalInstance().getDeserializer(Labels.class);
        if (!(labelsDeserializer instanceof LabelsDeserializer)) {
            ParserConfig.getGlobalInstance().putDeserializer(Labels.class, LabelsDeserializer.instance);
        }
    }

//    /**
//     * 注册json模板
//     */
//    public static void registerTemplate() {
//        //for extend 便于扩展，定制invocation 序列化/反序化模板
//        Object serializerObj = SerializeConfig.getGlobalInstance().get(Invocation.class);
//        if (serializerObj == null || !(serializerObj instanceof InvocationSerializer)) {
//            SerializeConfig.getGlobalInstance().put(Invocation.class, InvocationSerializer.instance);
//        }
//        ObjectDeserializer deserializerObj = ParserConfig.getGlobalInstance().getDeserializer(Invocation.class);
//        if (deserializerObj == null || !(deserializerObj instanceof InvocationDeserializer)) {
//            ParserConfig.getGlobalInstance().putDeserializer(Invocation.class, InvocationDeserializer.instance);
//        }
//        //for extend 便于扩展，定制ResponseMessage 序列化/反序化模板
//        Object responseSerializer = SerializeConfig.getGlobalInstance().get(ResponseMessage.class);
//        if (responseSerializer == null || !(responseSerializer instanceof ResponseSerializer)) {
//            SerializeConfig.getGlobalInstance().put(ResponseMessage.class, ResponseSerializer.instance);
//        }
//        ObjectDeserializer responseDeserializerObj = ParserConfig.getGlobalInstance().getDeserializer(ResponseMessage.class);
//        if (responseDeserializerObj == null || !(responseDeserializerObj instanceof ResponseDeserializer)) {
//            ParserConfig.getGlobalInstance().putDeserializer(ResponseMessage.class, ResponseDeserializer.instance);
//        }
//    }

    /**
     * 对象转为json字符串
     *
     * @param object 对象
     * @return json字符串
     */
    public static final String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 对象转为json字符串
     *
     * @param object 对象
     * @return json字符串
     */
    public static final String toJSONString(Object object, SerializerFeature feature) {
        return JSON.toJSONString(object, SerializerFeature.WriteClassName);
    }

    /**
     * 解析为指定对象
     *
     * @param text  json字符串
     * @param clazz 指定类
     * @param <T>   指定对象
     * @return 指定对象
     */
    public static final <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * 解析为指定对象
     *
     * @param text  json字符串
     * @param clazz 指定类
     * @param <T>   指定对象
     * @return 指定对象
     */
    public static final <T> T parseObjectByType(String text, Type clazz) {
        return JSON.parseObject(text, clazz);
    }

}