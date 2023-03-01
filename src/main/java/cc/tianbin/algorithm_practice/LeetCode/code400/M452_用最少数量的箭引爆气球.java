package cc.tianbin.algorithm_practice.LeetCode.code400;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/*
在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。

一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。

给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。

 
示例 1：

输入：points = [[10,16],[2,8],[1,6],[7,12]]
输出：2
解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
示例 2：

输入：points = [[1,2],[3,4],[5,6],[7,8]]
输出：4
示例 3：

输入：points = [[1,2],[2,3],[3,4],[4,5]]
输出：2
示例 4：

输入：points = [[1,2]]
输出：1
示例 5：

输入：points = [[2,3],[2,3]]
输出：1
 

提示：

0 <= points.length <= 104
points[i].length == 2
-231 <= xstart < xend <= 231 - 1


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/23
 */
public class M452_用最少数量的箭引爆气球 {

    @Test
    public void testCase() {
        int[][] points = new int[][]{
                {10,16},{2,8},{1,6},{7,12}
        };
        int excepted = 2;
        Assert.assertEquals(excepted, findMinArrowShots(points));
        
        points = new int[][]{
                {1,2},{3,4},{5,6},{7,8}
        };
        excepted = 4;
        Assert.assertEquals(excepted, findMinArrowShots(points));
        
        points = new int[][]{
                {1,2},{2,3},{3,4},{4,5}
        };
        excepted = 2;
        Assert.assertEquals(excepted, findMinArrowShots(points));
        
        points = new int[][]{
                {1,2}
        };
        excepted = 1;
        Assert.assertEquals(excepted, findMinArrowShots(points));

        points = new int[][]{
                {2,3},{2,3}
        };
        excepted = 1;
        Assert.assertEquals(excepted, findMinArrowShots(points));

        points = new int[][]{
                {3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}
        };
        excepted = 2;
        Assert.assertEquals(excepted, findMinArrowShots(points));

        points = new int[][]{
                {9,12},{1,10},{4,11},{8,12},{3,9},{6,9},{6,7}
        };
        excepted = 2;
        Assert.assertEquals(excepted, findMinArrowShots(points));

        points = new int[][]{
                {-2147483646,-2147483645},{2147483646,2147483647}
        };
        excepted = 2;
        Assert.assertEquals(excepted, findMinArrowShots(points));

    }

    public int findMinArrowShots(int[][] points) {

        if (points.length == 0) {
            return 0;
        }

//        quickSort(points, 0, points.length-1);
        Arrays.sort(points, (p1, p2) -> {return p1[1] > p2[1] ? 1 : -1;});

        int res = 1;
        int position = points[0][1];
        for(int[] balloon : points) {
            if (balloon[0] > position) {
                position = balloon[1];
                ++res;
            }
        }
        return res;
    }

    /**
     * 升序排列
     *
     * points[1]相同 按points[0] 升序排列
     */
    private void quickSort(int[][] points, int left, int right) {

        if (left >= right) {
            return;
        }

        int partition = partition(points, left, right);

        quickSort(points, left, partition-1);
        quickSort(points, partition+1, right);

    }

    private int partition(int[][] points, int left, int right) {

        int flag = left;
        for (int i = left; i <= right; i++) {
            if (points[i][1] < points[right][1]) {
                swap(points, i, flag);
                flag++;
            } else if (points[i][1] == points[right][1] && points[i][0] < points[right][0]) {
                swap(points, i, flag);
                flag++;
            }
        }
        swap(points, right, flag);

        return flag;
    }

    private void swap(int[][] points, int left, int right) {
        int[] temp = points[left];
        points[left] = points[right];
        points[right] = temp;
    }
}
