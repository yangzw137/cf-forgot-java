package org.cf.forgot.fastjson.labels;

import java.util.List;

/**
 * 组合标签选择器
 */
public interface Selectors extends Selector {

    /**
     * 获取该组标签选择器所有子选择器
     *
     * @return
     */
    List<Selector> getSelectors();
}
