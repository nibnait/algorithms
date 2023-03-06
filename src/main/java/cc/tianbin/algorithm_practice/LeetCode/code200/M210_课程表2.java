package cc.tianbin.algorithm_practice.LeetCode.code200;

import cc.tianbin.common.util.SysOut;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。

可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。

示例 1:

输入: 2, [[1,0]]
输出: [0,1]
解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
示例 2:

输入: 4, [[1,0],[2,0],[3,1],[3,2]]
输出: [0,1,2,3] or [0,2,1,3]
解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
说明:

输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
提示:

这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
拓扑排序也可以通过 BFS 完成。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/course-schedule-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/22
 */
public class M210_课程表2 {

    @Test
    public void testCase() {
        int numCourses = 2;
        int[][] prerequisites = new int[][]{
                {1,0}
        };
        int[] excepted = new int[]{0, 1};
        int[] actual = findOrder(numCourses, prerequisites);
        SysOut.printArray(actual);
        Assert.assertArrayEquals(excepted, actual);

        numCourses = 2;
        prerequisites = new int[][]{
                {1,0},
                {0,1}
        };
        excepted = new int[]{};
        actual = findOrder(numCourses, prerequisites);
        SysOut.printArray(actual);
        Assert.assertArrayEquals(excepted, actual);

    }

    /**
     * dfs
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, Set<Integer>> adjoinMap = new HashMap<>(numCourses);
        for(int[] p : prerequisites) {
            Set<Integer> outDegreeSet = adjoinMap.getOrDefault(p[1], new HashSet<>());
            outDegreeSet.add(p[0]);

            adjoinMap.put(p[1], outDegreeSet);
        }

        int[] visited = new int[numCourses];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (dfs(adjoinMap, visited, stack, i)) {
                // 有环，不能完成所有课程的学习
                return new int[0];
            }
        }

        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    /**
     * adjoinMap，访问到节点 i，判断此图是否有环
     *
     * visited[i] == 1, 说明此节点还在访问中，就又被访问到了。直接 return true; 有环！
     * visited[i] == 2, 说明此节点已经被访问过了，直接 return false; 不用再往下访问了，肯定无环。
     * visited[i] == 0, 说明此节点还没被访问过，继续 dfs 访问
     */
    private boolean dfs(Map<Integer, Set<Integer>> adjoinMap, int[] visited, Stack<Integer> stack, int i) {
        if (visited[i] == 1) {
            return true;
        }
        if (visited[i] == 2) {
            return false;
        }

        visited[i] = 1;
        for (Integer course : adjoinMap.getOrDefault(i, new HashSet<>())) {
            if (dfs(adjoinMap, visited, stack, course)) {
                return true;
            }
        }

        visited[i] = 2;
        stack.push(i);
        return false;
    }

    /**
     * bfs
     * （拓扑排序）
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            inDegree[p[0]]++;
        }

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }


        int[] res = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            Integer course = queue.pollFirst();
            res[index++] = course;

            for(int[] p : prerequisites) {
                if (p[1] == course) {
                    inDegree[p[0]]--;
                    if (inDegree[p[0]] == 0) {
                        queue.offerLast(p[0]);
                    }
                }
            }
        }

        return index == numCourses ? res : new int[0];
    }

}
