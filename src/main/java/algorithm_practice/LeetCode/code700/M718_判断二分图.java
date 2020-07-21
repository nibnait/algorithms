package algorithm_practice.LeetCode.code700;

import com.sun.jmx.remote.internal.ArrayQueue;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class M718_判断二分图 {

  /*
  给定一个无向图graph，当这个图为二分图时返回true。

如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。

graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。


示例 1:
输入: [[1,3], [0,2], [1,3], [0,2]]
输出: true
解释:
无向图如下:
0----1
|    |
|    |
3----2
我们可以将节点分成两组: {0, 2} 和 {1, 3}。

示例 2:
输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
输出: false
解释:
无向图如下:
0----1
| \  |
|  \ |
3----2
我们不能将节点分割成两个独立的子集。
注意:

graph 的长度范围为 [1, 100]。
graph[i] 中的元素的范围为 [0, graph.length - 1]。
graph[i] 不会包含 i 或者有重复的值。
图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/is-graph-bipartite
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    int[][] graph1 = new int[][]{new int[]{1, 3}, new int[]{0, 2}, new int[]{1, 3},
        new int[]{0, 2}};
    System.out.println(isBipartite(graph1));
    int[][] graph2 = new int[][]{new int[]{1, 2, 3}, new int[]{0, 2}, new int[]{0, 1, 3},
        new int[]{0, 2}};
    System.out.println(isBipartite(graph2));
  }

  /*
  解法：
1.BFS，注意整个图不一定是全连通的，所以需要遍历所有的点，
然后进行BFS，BFS的过程中对节点Node周围的节点进行染色，
让周围节点与该节点颜色不同，如果周围节点已经有颜色了，
就判断该颜色是否是需要染色的结果，如果是，不作处理继续循环，否则直接中断循环返回false。
注意BFS通常使用Queue来实现。

   */
  public static final int UNCOLOR = 0;
  public static final int RED = 1;
  public static final int BLUE = 2;

  public static boolean isBipartite(int[][] graph) {
    int n = graph.length;
    int[] color = new int[n];
    Arrays.fill(color, UNCOLOR);
    for (int i = 0; i < n; i++) {
      if (color[i] == UNCOLOR) {
        color[i] = RED;
        //开始BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        while (!queue.isEmpty()) {
          int node = queue.poll();
          int neighborColor = color[node] == RED ? BLUE : RED;
          for (int nei : graph[node]) {
            if (color[nei] == UNCOLOR) {
              color[nei] = neighborColor;
              queue.offer(nei);
            } else if (color[nei] != neighborColor) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

}
