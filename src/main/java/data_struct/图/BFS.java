package data_struct.图;

import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * 广度优先遍历（求最短路径）
 * <p>
 *
 * 队列 FIFO
 * 先将此结点的所有相邻队列放入Queue中，将这些结点到起始结点的距离标记为1，
 * 然后依次pop队列，
 * 继续<b>递归</b>，到起始结点的距离+1
 *
 */
public class BFS extends TestCase {

    @Test
    public void testBreadthFirstSearch() throws IOException {
        Path digitGraphfilePath = Paths.get("/Users/nibnait/Downloads/github/algorithms/src/Algorithm/algorithm_practice.Algorithms_4thEdition/图/data/digitGraph.txt");
        Path characterGraphfilePath = Paths.get("/Users/nibnait/Downloads/github/algorithms/src/Algorithm/algorithm_practice.Algorithms_4thEdition/图/data/characterGraphfilePath.txt");
        Graph graph = new Graph(digitGraphfilePath);
//        Graph graph = new Graph(characterGraphfilePath);

        BreadthFirstSearch(graph, startVertex);
        System.out.println("\n递归法：");
        BreadthFirstSearch_v2(graph, startVertex);
    }

    /**
     * 法2： 递归
     * @param graph
     * @param startVertex
     */

    String startVertex = "1";
//        String startVertex = "s";
    private Map<String, Integer> distanceMap_recursive = new HashMap<>();

    private void BreadthFirstSearch_v2(Graph graph, String startVertex) {
        checkParam(graph, startVertex);

        Queue<String> queue = new LinkedList<>();
        queue.add(startVertex);
        distanceMap_recursive.put(startVertex, 0);
        BFS_recursive(graph, queue);
    }

    private void BFS_recursive(Graph graph, Queue<String> queue) {
        if (queue.isEmpty()) {
            return;
        }
        String poll = queue.poll();
        SysOut.println("node %s 到 %s 的距离为: %d", poll, startVertex, distanceMap_recursive.get(poll));
        int distance = distanceMap_recursive.get(poll)+1;
        for (String adjoinNode : graph.getAdjoinMap().get(poll)) {
            if (!distanceMap_recursive.containsKey(adjoinNode)) {
                queue.add(adjoinNode);
                distanceMap_recursive.put(adjoinNode, distance);
            }
        }
        BFS_recursive(graph, queue);
    }


    private void BreadthFirstSearch(Graph graph, String startVertex) {
        checkParam(graph, startVertex);
        Map<String, Integer> distanceMap = new HashMap<>();  //各顶点到起始点的距离
        Queue<String> queue = new LinkedList<>();

        queue.add(startVertex);
        distanceMap.put(startVertex, 0);
        while (!queue.isEmpty()) {
            String poll = queue.poll();
            SysOut.println("node %s 到 %s 的距离为: %d", poll, startVertex, distanceMap.get(poll));
            int distance = distanceMap.get(poll) + 1;
            for (String adjoinNode : graph.getAdjoinMap().get(poll)) {
                if (!distanceMap.containsKey(adjoinNode)) {
                    queue.add(adjoinNode);
                    distanceMap.put(adjoinNode, distance);
                }
            }
        }
    }


    private void checkParam(Graph graph, String currentVertex) {
        if (graph.getAdjoinMap().get(currentVertex) == null) {
            throw new RuntimeException(String.format("无此结点: %s", currentVertex));
        }
    }

}