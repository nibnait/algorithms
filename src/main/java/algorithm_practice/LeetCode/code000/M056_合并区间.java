package algorithm_practice.LeetCode.code000;

import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;
import sun.jvm.hotspot.utilities.BitMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
给出一个区间的集合，请合并所有重叠的区间。

示例 1:
输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

示例 2:
输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-intervals
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-01-26
 */
public class M056_合并区间 extends TestCase {

    @Test
    public void testCase() {
        int[][] intervals1 = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        SysOut.printArray(merge(intervals1));

        int[][] intervals2 = new int[][]{{1, 4}, {4, 5}};
        SysOut.printArray(merge(intervals2));

        int[][] intervals3 = new int[][]{{1, 4}, {0, 4}};
        SysOut.printArray(merge(intervals3));

        int[][] intervals4 = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        SysOut.printArray(merge(intervals4));

        int[][] intervals5 = new int[][]{{1, 4}, {2, 3}};
        SysOut.printArray(merge(intervals5));
        
        int[][] intervals6 = new int[][]{{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        SysOut.printArray(merge(intervals6));

    }

    /*
    1. 先按照interval[0]排序
    2. 遍历，寻找能合并的，把他合并了
       List<int[]>
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 合并
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (result.size() == 0) {
                result.add(interval);
                continue;
            }

            int[] resultInterval = result.get(result.size()-1);

            if (interval[1] < resultInterval[1]) {
                continue;
            }

            if (interval[0] <= resultInterval[1]) {
                resultInterval[1] = interval[1];

                result.remove(result.size()-1);
                result.add(resultInterval);
            } else {
                result.add(interval);
            }
        }

        return result.toArray(new int[0][]);
    }

    /**
     * 使用BitMap
     */
    public int[][] merge2(int[][] intervals) {
        BitMap bitMap = new BitMap(100);

        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            for (int j = interval[0]; j < interval[1]; j++) {
                if (j > bitMap.size()) {
                    bitMap.set_size(j);
                }

                bitMap.atPut(j, true);
            }
        }

        int[][] result = new int[1][2];
        int count = 0;
        for (int i = 0; i < bitMap.size(); i++) {
            if (bitMap.at(i)) {
                result[count][0] = i;
                while (bitMap.at(i)) {
                    i++;
                }

                result[count][1] = i;

                count++;
            }
        }

        return result;
    }
}
