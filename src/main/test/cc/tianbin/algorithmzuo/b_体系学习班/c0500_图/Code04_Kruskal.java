package cc.tianbin.algorithmzuo.b_体系学习班.c0500_图;

import cc.tianbin.data_struct.ds4_图.Node;
import cc.tianbin.data_struct.ds4_图.Edge;
import cc.tianbin.data_struct.ds4_图.Graph;
import org.junit.Test;

import java.util.*;

/**
 * Created by nibnait on 2022/11/06
 */
public class Code04_Kruskal {

    @Test
    public void test() {

    }

    /**
     * 用并查集
     * 生成一个边的权重代价最小的（将原图的所有多余的边去掉的）图
     */
    public Set<Edge> KruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind(graph.nodes.values());
        PriorityQueue<Edge> heap = new PriorityQueue<>(new MyComparator());
        for (Edge edge : graph.edges) {
            heap.add(edge);
        }

        Set<Edge> result = new HashSet<>();
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                unionFind.union(edge.from, edge.to);
                result.add(edge);
            }
        }
        return result;
    }

    private class MyComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    private class UnionFind {
        private Map<Node, Node> fatherMap;
        private Map<Node, Integer> sizeMap;

        public UnionFind(Collection<Node> nodes) {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();

            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findAncestor(Node node) {
            Stack<Node> stack = new Stack<>();
            while(node != fatherMap.get(node)) {
                stack.push(node);
                node = fatherMap.get(node);
            }
            while (!stack.isEmpty()) {
                Node pop = stack.pop();
                fatherMap.put(pop, node);
            }
            return node;
        }

        public boolean isSameSet(Node o1, Node o2) {
            return findAncestor(o1) == findAncestor(o2);
        }

        public void union(Node o1, Node o2) {
            Node head1 = findAncestor(o1);
            Node head2 = findAncestor(o2);
            if (head1 == head2) {
                return;
            }
            Integer size1 = sizeMap.get(o1);
            Integer size2 = sizeMap.get(o2);
            Node big = size1 > size2 ? head1 : head2;
            Node small = big == head1 ? head2 : head1;
            fatherMap.put(small, big);
            sizeMap.put(big, size1 + size2);
            sizeMap.remove(small);
        }

    }

}
