package algorithmzuo.b_体系学习班.c0500_图;

import data_struct.ds4_图.Edge;
import data_struct.ds4_图.Graph;
import data_struct.ds4_图.Node;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by nibnait on 2022/11/06
 */
public class Code05_Prim {

    @Test
    public void test() {

    }

    /**
     * 用小根堆
     * 生成一个边的权重代价最小的（将原图的所有多余的边去掉的）图
     *
     * 从任意点开始，依次解锁从当前点出发的所有边（按边的权重排序），
     */
    public Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> heap = new PriorityQueue<>(new MyComparator());
        Set<Node> nodeSet = new HashSet<>();
        Set<Edge> result = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (nodeSet.contains(node)) {
                continue;
            }
            nodeSet.add(node);

            for (Edge edge : node.edges) {
                heap.add(edge);
            }

            while (!heap.isEmpty()) {
                Edge edge = heap.poll();
                Node toNode = edge.to;
                if (nodeSet.contains(toNode)) {
                    continue;
                }
                nodeSet.add(toNode);
                result.add(edge);
                for (Edge nextEdge : toNode.edges) {
                    heap.add(nextEdge);
                }
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

    /**
     * graph[i][j] 表示 i点 到 j点的距离，如果是 Integer.MAX_VALUE 则代表无路
     * @param graph 一个连通图
     * @return 最小连通图的路径之和
     */
    public int prim(int[][] graph) {
        int size = graph.length;
        int[] distance = new int[size];
        boolean[] visit = new boolean[size];
        visit[0] = true;
        for (int i = 0; i < size; i++) {
            distance[i] = graph[0][i];
        }

        int sum = 0;
        for (int i = 1; i < size; i++) {
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distance[j] < minPath) {
                    minPath = distance[j];
                    minIndex = j;
                }
            }

            if (minIndex == -1) {
                return sum;
            }

            visit[minIndex] = true;
            sum += minPath;

            for (int j = 0; j < size; j++) {
                if (!visit[j] && distance[j] > graph[minIndex][j]) {
                    distance[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }
}
