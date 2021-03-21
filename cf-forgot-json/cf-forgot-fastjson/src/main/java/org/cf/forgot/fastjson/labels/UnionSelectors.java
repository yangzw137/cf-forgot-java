package org.cf.forgot.fastjson.labels;

import java.util.*;

/**
 * 取并集的组合标签选择器
 */
public class UnionSelectors implements Selectors {

    /**
     * 一组标签选择器
     */
    private List<Selector> selectors;

    /**
     * 构造方法
     *
     * @param selectors
     */
    public UnionSelectors(List<Selector> selectors) {
        this.selectors = selectors;
    }

    @Override
    public List<Selector> getSelectors() {
        return new ArrayList<Selector>(selectors);
    }

    @Override
    public List<Node> select(Collection<Node> nodes) {
        Set<Node> resNodes = new HashSet<Node>();
        for (Selector selector : selectors) {
            resNodes.addAll(selector.select(nodes));
        }
        return new ArrayList<Node>(resNodes);
    }


}
