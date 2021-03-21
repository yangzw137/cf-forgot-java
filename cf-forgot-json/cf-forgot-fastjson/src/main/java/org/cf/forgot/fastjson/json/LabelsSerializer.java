package org.cf.forgot.fastjson.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import org.cf.forgot.fastjson.labels.Labels;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

public class LabelsSerializer implements ObjectSerializer {

    public static final LabelsSerializer instance = new LabelsSerializer();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.getWriter();
        if (object == null) {
            out.writeNull();
            return;
        }
        out.append("{");
        Map<String, Set<String>> labels = ((Labels) object).getAllValues();
        int i = 0;
        for (Map.Entry<String, Set<String>> entry : labels.entrySet()) {
            if (i++ > 0) {
                out.append(",");
            }
            out.writeFieldName(entry.getKey());
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                StringBuilder sb = new StringBuilder();
                int j = 0;
                for (String v : entry.getValue()) {
                    if (j++ > 0) {
                        sb.append(',');
                    }
                    sb.append(v);
                }
                out.writeString(sb.toString());
            }
        }
        out.append("}");
    }
}
