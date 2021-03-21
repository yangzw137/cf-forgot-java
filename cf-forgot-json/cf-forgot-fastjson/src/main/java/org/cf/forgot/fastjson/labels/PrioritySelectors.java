package org.cf.forgot.fastjson.labels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 取优先的组合标签选择器
 */
public class PrioritySelectors implements Selectors {

    /**
     * 一组标签选择器
     */
    private List<Selector> selectors;

    /**
     * 构造方法
     *
     * @param selectors
     */
    public PrioritySelectors(List<Selector> selectors) {
        this.selectors = selectors;
    }

    @Override
    public List<Selector> getSelectors() {
        return new ArrayList<Selector>(selectors);
    }

    @Override
    public List<Node> select(Collection<Node> nodes) {
        for (Selector selector : selectors) {
            List<Node> resNodes = selector.select(nodes);
            if (resNodes != null && !resNodes.isEmpty()) {
                return resNodes;
            }
        }
        return new ArrayList<Node>();
    }
}
