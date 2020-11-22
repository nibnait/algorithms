package algorithm_practice.LeetCode.code400;

import common.util.SysOut;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对 (h, k) 表示，其中 h 是这个人的身高，k 是应该排在这个人前面且身高大于或等于 h 的人数。 例如：[5,2] 表示前面应该有 2 个身高大于等于 5 的人，而 [5,0] 表示前面不应该存在身高大于等于 5 的人。

 编写一个算法，根据每个人的身高 h 重建这个队列，使之满足每个整数对 (h, k) 中对人数 k 的要求。

 示例：

 输入：[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 输出：[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
  

 提示：

 总人数少于 1100 人。


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/18
 */
public class M406_根据身高重建队列 {

    @Test
    public void testCase() {
        int[][] people = new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] excepted = new int[][]{{5,0}, {7,0}, {5,2}, {6,1}, {4,4}, {7,1}};
        int[][] actuals = reconstructQueue(people);
//        SysOut.printArray(actuals);
        Assert.assertArrayEquals(excepted, actuals);

        people = new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        excepted = new int[][]{{5,0}, {7,0}, {5,2}, {6,1}, {4,4}, {7,1}};
        actuals = reconstructQueue(people);
//        SysOut.printArray(actuals);
        Assert.assertArrayEquals(excepted, actuals);

    }

    public int[][] reconstructQueue(int[][] people) {

        quickSort(people, 0, people.length-1);

        List<int[]> res = new ArrayList<>();
        for(int[] p : people) {
            res.add(p[1], p);
        }

        return res.toArray(new int[0][2]);
    }

    /**
     * 快速排序
     * 按照arr[0] 降序，arr[1] 升序 排列
     */
    private void quickSort(int[][] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int p = partition(arr, left, right);

        quickSort(arr, 0, p-1);
        quickSort(arr, p+1, right);
    }

    private int partition(int[][] arr, int left, int right) {

        int flag = left;
        for (int i = left; i < right; i++) {
            if (arr[i][0] > arr[right][0]) {
                swap(arr, i, flag);
                flag++;
            }

            if (arr[i][0] == arr[right][0] && arr[i][1] < arr[right][1]) {
                swap(arr, i, flag);
                flag++;
            }
        }
        swap(arr, flag, right);

        return flag;
    }

    private void swap(int[][] arr, int i, int j) {
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
