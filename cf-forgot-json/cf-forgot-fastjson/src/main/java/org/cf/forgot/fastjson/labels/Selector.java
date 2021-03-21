package org.cf.forgot.fastjson.labels;

import java.util.Collection;
import java.util.List;

/**
 * 标签选择器
 */
public interface Selector {

    /**
     * 选择节点
     *
     * @param nodes
     * @return
     */
    List<Node> select(Collection<Node> nodes);
}
