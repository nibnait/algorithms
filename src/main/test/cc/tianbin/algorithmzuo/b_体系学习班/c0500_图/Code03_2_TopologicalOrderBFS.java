package cc.tianbin.algorithmzuo.b_体系学习班.c0500_图;

import org.junit.Test;

import java.util.*;

/**
 * Created by nibnait on 2022/11/06
 */
public class Code03_2_TopologicalOrderBFS {

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
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> inDegreeMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            inDegreeMap.put(node, 0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode next : node.neighbors) {
                inDegreeMap.put(next, inDegreeMap.get(next) + 1);
            }
        }

        Queue<DirectedGraphNode> zeroInDegreeQueue = new LinkedList<>();
        for (DirectedGraphNode node : inDegreeMap.keySet()) {
            if (inDegreeMap.get(node) == 0) {
                zeroInDegreeQueue.add(node);
            }
        }

        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        while (!zeroInDegreeQueue.isEmpty()) {
            DirectedGraphNode node = zeroInDegreeQueue.poll();
            result.add(node);
            for (DirectedGraphNode next : node.neighbors) {
                inDegreeMap.put(next, inDegreeMap.get(next) - 1);
                if (inDegreeMap.get(next) == 0) {
                    zeroInDegreeQueue.add(next);
                }
            }
        }

        return result;
    }


}
