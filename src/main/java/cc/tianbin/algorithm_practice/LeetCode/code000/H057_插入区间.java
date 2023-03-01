package cc.tianbin.algorithm_practice.LeetCode.code000;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
给出一个无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

 

示例 1：

输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
输出：[[1,5],[6,9]]
示例 2：

输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出：[[1,2],[3,10],[12,16]]
解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/insert-interval
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class H057_插入区间 {

    @Test
    public void testCase() {
        int[][] intervals = new int[][]{
                {1,3},{6,9}
        };
        int[] newInterval = new int[]{2,5};
        int[][] excepted = new int[][]{
                {1,5},{6,9}
        };
        Assert.assertArrayEquals(excepted, insert(intervals, newInterval));
        
        intervals = new int[][]{
                {1,2},{3,5},{6,7},{8,10},{12,16}
        };
        newInterval = new int[]{4,8};
        excepted = new int[][]{
                {1,2},{3,10},{12,16}
        };
        Assert.assertArrayEquals(excepted, insert(intervals, newInterval));
        
        
    }

    /*

     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }


        List<int[]> res = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i++]);
        }

        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        res.add(newInterval);

        while (i < intervals.length) {
            res.add(intervals[i++]);
        }

        return res.toArray(new int[0][]);
    }

}
