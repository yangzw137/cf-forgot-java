package org.cf.forgot.fastjson.labels;


import org.cf.forgot.fastjson.util.JsonUtils;
import org.cf.forgot.fastjson.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 一组标签
 */
public class Labels {

    /**
     * 标签多个值的分隔符
     */
    private static final String LABEL_VALUES_SEP = ",";

    /**
     * 标签多个值的分隔符正则
     */
    private static final String LABEL_VALUES_SPLIT = "\\s+|" + LABEL_VALUES_SEP + "|\\s+";

    /**
     * 标签 k，vs
     */
    private Map<String, Set<String>> labels = new HashMap<String, Set<String>>();

    /**
     * 构造方法
     *
     * @param labels
     */
    public Labels(Map<String, Set<String>> labels) {
        this.labels.putAll(labels);
    }

    /**
     * 标签是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return labels == null || labels.isEmpty();
    }

    /**
     * 获取所有标签key
     *
     * @return
     */
    public Set<String> getKeys() {
        return new HashSet<String>(labels.keySet());
    }

    /**
     * 获取一个标签key下的value
     *
     * @param key
     * @return
     */
    public Set<String> getValues(String key) {
        return new HashSet<String>(labels.get(key));
    }

    /**
     * 获取所key下的标签
     *
     * @return
     */
    public Map<String, Set<String>> getAllValues() {
        return new HashMap<String, Set<String>>(labels);
    }

    /**
     * 验证该组标签是否存在入参key
     *
     * @param key
     * @return
     */
    public boolean containsKey(String key) {
        return labels.containsKey(key);
    }

    /**
     * 验证该组标签是否存在入参key与value
     *
     * @param key
     * @param value
     * @return
     */
    public boolean containsValue(String key, String value) {
        Set<String> values = labels.get(key);
        if (values == null || values.isEmpty()) {
            return false;
        }
        return values.contains(value);
    }

    /**
     * 验证该组标签是否包含一个入参key与value集合
     *
     * @param key
     * @param values
     * @return
     */
    public boolean containsAllValues(String key, Set<String> values) {
        Set<String> valueSet = labels.get(key);
        if (valueSet == null || valueSet.isEmpty()) {
            return false;
        }
        for (String v : values) {
            if (!valueSet.contains(v)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 该组标签配置一个目标标签（存在目标key，且存在目标value值中至少一个值）
     *
     * @param key
     * @param values
     * @return
     */
    public boolean matchValues(String key, Set<String> values) {
        Set<String> valueSet = labels.get(key);
        if (valueSet == null || valueSet.isEmpty()) {
            return false;
        }
        if (values == null || values.isEmpty()) {
            return true;
        }
        for (String v : values) {
            if (valueSet.contains(v)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 匹配一组标签
     *
     * @param targetLabels
     * @return
     */
    public boolean matchLabels(Labels targetLabels) {
        if (targetLabels == null || targetLabels.labels == null) {
            return true;
        }
        for (Map.Entry<String, Set<String>> entry : targetLabels.labels.entrySet()) {
            if (!matchValues(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 构造一组标签
     *
     * @param labelsJson
     * @return
     */
    public static Labels valueOf(String labelsJson) {
        if (labelsJson == null || labelsJson.isEmpty()) {
            return null;
        }
        return valueOf(JsonUtils.parseObject(labelsJson, Map.class));
    }

    /**
     * 构造一组标签
     *
     * @param labels
     * @return
     */
    public static Labels valueOf(Map<String, String> labels) {
        if (labels == null || labels.isEmpty()) {
            return null;
        }
        Map<String, Set<String>> labs = new HashMap<String, Set<String>>();
        for (Map.Entry<String, String> entry : labels.entrySet()) {
            String key = entry.getKey();
            String valsStr = entry.getValue();
            String[] vals = StringUtils.isNotEmpty(valsStr) ? valsStr.split(LABEL_VALUES_SPLIT) : new String[0];
            Set<String> values = new HashSet<String>();
            for (String val : vals) {
                if (StringUtils.isNotEmpty(val)) {
                    values.add(val);
                }
            }
            labs.put(key, values);
        }
        return new Labels(labs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        int i = 0;
        for (Map.Entry<String, Set<String>> lab : labels.entrySet()) {
            if (i++ > 0) {
                sb.append(',');
            }
            sb.append(lab.getKey()).append(':').append('[');
            if (lab.getValue() != null && !lab.getValue().isEmpty()) {
                int j = 0;
                for (String v : lab.getValue()) {
                    if (j++ > 0) {
                        sb.append(',');
                    }
                    sb.append(v);
                }
            }
            sb.append(']');
        }

        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof Labels)) {
            return false;
        }
        Map<String, Set<String>> thatLabs = ((Labels) that).labels;
        return labels == thatLabs || (labels != null && labels.equals(thatLabs));
    }

    @Override
    public int hashCode() {
        return labels == null ? 0 : labels.hashCode();
    }
}
