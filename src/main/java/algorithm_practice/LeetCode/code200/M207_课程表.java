package algorithm_practice.LeetCode.code200;

import org.junit.Assert;
import org.junit.Test;

/*
你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？

 

示例 1:

输入: 2, [[1,0]]
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
示例 2:

输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 

提示：

输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
1 <= numCourses <= 10^5


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
                {1,0}
        };
        boolean excepted = true;
        Assert.assertEquals(excepted, canFinish(numCourses, prerequisites));

        numCourses = 2;
        prerequisites = new int[][]{
                {1,0},
                {0,1}
        };
        excepted = false;
        Assert.assertEquals(excepted, canFinish(numCourses, prerequisites));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {




        return true;
    }
}
