package org.cf.forgot.fastjson.labels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 标签选择器
 */
public class TermSelector implements Selector {

    /**
     * 条件标签
     */
    protected Labels termLabels;

    /**
     * 构造方法
     *
     * @param termLabels
     */
    public TermSelector(Labels termLabels) {
        this.termLabels = termLabels;
    }

    @Override
    public List<Node> select(Collection<Node> nodes) {
        List<Node> resNodes = new ArrayList<Node>();
        if (nodes != null && !nodes.isEmpty()) {
            for (Node node : nodes) {
                Labels nodeLabels = node.getLabels();
                if (nodeLabels != null && nodeLabels.matchLabels(termLabels)) {
                    resNodes.add(node);
                }
            }
        }
        return resNodes;
    }

}
