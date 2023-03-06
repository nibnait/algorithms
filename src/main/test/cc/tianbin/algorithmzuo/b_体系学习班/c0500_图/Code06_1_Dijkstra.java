package cc.tianbin.algorithmzuo.b_体系学习班.c0500_图;

import cc.tianbin.data_struct.ds4_图.Edge;
import cc.tianbin.data_struct.ds4_图.Node;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by nibnait on 2022/11/06
 */
public class Code06_1_Dijkstra {

    @Test
    public void test() {

    }

    /**
     * Dijkstra
     * 给你一个出发点，返回从出发点 到图中所有能到达的点的最短距离 的一个map表
     */
    public Map<Node, Integer> dijkstra(Node from) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        Set<Node> selectedNodes = new HashSet<>();
        Node minNode = getMinDistanceAndUnSelectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            Integer distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.max(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnSelectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    private Node getMinDistanceAndUnSelectedNode(Map<Node, Integer> distanceMap, Set<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }

        return minNode;
    }

}
