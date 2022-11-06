package algorithmzuo.b_体系学习班.c0500_图;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by nibnait on 2022/11/06
 */
public class Code03_3_TopologicalOrderDFS {


    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    @Test
    public void test() {

    }

    /**
     * https://www.lintcode.com/problem/topological-sorting
     */
    private class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    private class MyComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
        }
    }

    /**
     * 当前节点的深度越深，它的拓扑排序就越在前面
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            process(node, order);
        }

        return order.values().stream()
                .sorted(new MyComparator())
                .map(record -> record.node)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Record process(DirectedGraphNode node, Map<DirectedGraphNode, Record> order) {
        if (order.containsKey(node)) {
            return order.get(node);
        }

        int deep = 0;
        for (DirectedGraphNode next : node.neighbors) {
            deep = Math.max(deep, process(next, order).deep);
        }

        Record record = new Record(node, deep + 1);
        order.put(node, record);
        return record;
    }

}
