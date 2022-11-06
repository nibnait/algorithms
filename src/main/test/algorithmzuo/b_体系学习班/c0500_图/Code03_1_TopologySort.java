package algorithmzuo.b_体系学习班.c0500_图;

import data_struct.ds4_图.Graph;
import data_struct.ds4_图.Node;

import java.util.*;

/**
 * Created by nibnait on 2022/11/06
 */
public class Code03_1_TopologySort {

    /**
     * 有向无环图
     */
    public List<Node> topSort(Graph graph) {

        // 每个节点的剩余入度
        Map<Node, Integer> inDegreeMap = new HashMap<>();
        // 没有入度的节点
        Queue<Node> zeroInDegreeQueue = new LinkedList<>();

        for (Node node : graph.nodes.values()) {
            inDegreeMap.put(node, node.in);
            if (node.in == 0) {
                zeroInDegreeQueue.add(node);
            }
        }

        List<Node> result = new ArrayList<>();
        while (!zeroInDegreeQueue.isEmpty()) {
            Node cur = zeroInDegreeQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inDegreeMap.put(next, inDegreeMap.get(next) - 1);
                if (inDegreeMap.get(next) == 0) {
                    zeroInDegreeQueue.add(next);
                }
            }
        }

        return result;
    }


}
