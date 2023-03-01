package cc.tianbin.algorithm_practice.LeetCode.code200;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？

 

示例 1:

输入: 2, [[1,0]]
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
示例 2:

输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 

提示：

输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
1 <= numCourses <= 10^5


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/course-schedule
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/22
 */
public class M207_课程表 {

    @Test
    public void testCase() {
        int numCourses = 2;
        int[][] prerequisites = new int[][]{
                {1, 0}
        };
        boolean excepted = true;
        Assert.assertEquals(excepted, canFinish(numCourses, prerequisites));

        numCourses = 2;
        prerequisites = new int[][]{
                {1, 0},
                {0, 1}
        };
        excepted = false;
        Assert.assertEquals(excepted, canFinish(numCourses, prerequisites));
    }

    /**
     * DFS
     * 1. 建立邻接表
     * 2. dfs 访问每一个课程，只要有环 就 return false;
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

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
                return false;
            }
        }

        return true;
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
     * BFS
     * 【拓扑排序】
     * 1. 建立入度表
     * 2. 入度为0的课程入队
     * 3. pollFist（同时 numCourses--），将以此课程为入度的课程 -1，之后有 入度为0的课程，继续入队
     * 4. 重复第3步
     * 5. 栈为空时，看 numCourses 是否为0
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
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

        while (!queue.isEmpty()) {
            Integer course = queue.pollFirst();
            numCourses--;

            for(int[] p : prerequisites) {
                if (p[1] == course) {
                    inDegree[p[0]]--;
                    if (inDegree[p[0]] == 0) {
                        queue.offerLast(p[0]);
                    }
                }

            }
        }

        return numCourses == 0;
    }

}
