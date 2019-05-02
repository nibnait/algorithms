package Algorithm.Algorithms_4thEdition.c_Graph;

import utils.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 深度优先遍历（全排列、油田问题、棋盘问题）
 * <p>
 *
 * 递归 访问此节点的的下一个节点，知道走到头（当前节点已经没有下一个相邻节点了 或其相邻节点均已被访问）
 * 返回，（类似于 栈 FILO）
 * 继续访问此节点的下一个未被访问过的节点。
 */
public class DFS extends TestCase {

    @Test
    public void testDepthFirstSearch() throws IOException {
        Path digitGraphfilePath = Paths.get("/Users/nibnait/Downloads/github/algorithms/src/Algorithm/Algorithms_4thEdition/c_Graph/data/digitGraph.txt");
        Path characterGraphfilePath = Paths.get("/Users/nibnait/Downloads/github/algorithms/src/Algorithm/Algorithms_4thEdition/c_Graph/data/characterGraphfilePath.txt");
        Graph graph = new Graph(digitGraphfilePath);

        DepthFirstSearch(graph, startVertex);
        printAllNodesDistance(distanceMap);
    }

    String startVertex = "1";
    //        String startVertex = "u";
    private Map<String, Integer> distanceMap = new HashMap<>();  //各顶点到起始点的距离
    private int distance = 0;
    private int step = 1;       //脚印

    private void DepthFirstSearch(Graph graph, String currentVertex) {
        checkParam(graph, currentVertex);
        distanceMap.put(currentVertex, distance++);
        SysOut.println("visit Stack IN node %s \t step:%d", currentVertex, step++);

        for (String vertex : graph.getAdjoinMap().get(currentVertex)) {
            if (!distanceMap.containsKey(vertex)) {
                DepthFirstSearch(graph, vertex);
            }
        }
        SysOut.println("visit Stack OUT node %s \t step:%d", currentVertex, step++);
        distance--;
    }


    private void checkParam(Graph graph, String currentVertex) {
        if (graph.getAdjoinMap().get(currentVertex) == null) {
            throw new RuntimeException(String.format("无此节点: %s", currentVertex));
        }
    }


    private void printAllNodesDistance(Map<String, Integer> distanceMap) {
        System.out.println();
        for (Map.Entry<String, Integer> entry : distanceMap.entrySet()) {
            SysOut.println("node %s 到 %s 的距离为: %d", entry.getKey(), startVertex, entry.getValue());
        }
    }
}
