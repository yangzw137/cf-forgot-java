package org.cf.forgot.fastjson.sample.bo;

import com.alibaba.fastjson.serializer.SerializerFeature;
import org.cf.common.model.Bean1;
import org.cf.common.model.EntityFactory;
import org.cf.common.model.GBean1;
import org.cf.forgot.fastjson.util.JsonUtils;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/17
 * @since todo
 */
public class Sample1 {

    public static void main(String[] args) {
        Bean1 bean1 = EntityFactory.createBean1();

        String json = JsonUtils.toJSONString(bean1, SerializerFeature.WriteClassName);
        System.out.println("json: " + json);

        json = JsonUtils.toJSONString(bean1);
        System.out.println("json: " + json);

        bean1 = JsonUtils.parseObject(json, Bean1.class);

        System.out.println("bean1: " + bean1);

        System.out.println("---------------------------------------------------------------");

        GBean1 gBean1 = EntityFactory.createGBean1(bean1);

        json = JsonUtils.toJSONString(gBean1);
        System.out.println("json: \n" + json);

        gBean1 = JsonUtils.parseObject(json, GBean1.class);
        System.out.println("gbean1: \n" + gBean1.toString());


        GBean1 gb2 = EntityFactory.createGBean1(bean1);
        gBean1 = EntityFactory.createGBean1(gb2);

        json = JsonUtils.toJSONString(gBean1, SerializerFeature.WriteClassName);
        System.out.println("json: " + json);
        gBean1 = JsonUtils.parseObject(json, GBean1.class);
        System.out.println("gbean1: \n" + gBean1.toString());
    }
}
