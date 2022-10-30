package algorithm_practice.LeetCode.code1100;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 给你两个数组，arr1 和 arr2，

 arr2 中的元素各不相同
 arr2 中的每个元素都出现在 arr1 中
 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

  

 示例：

 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 输出：[2,2,2,1,4,3,3,9,6,7,19]
  

 提示：

 1 <= arr1.length, arr2.length <= 1000
 0 <= arr1[i], arr2[i] <= 1000
 arr2 中的元素 arr2[i] 各不相同
 arr2 中的每个元素 arr2[i] 都出现在 arr1 中


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/relative-sort-array
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/18
 */
public class E1122_数组的相对排序 {

    @Test
    public void testCase() {
        int[] arr1 = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = new int[]{2,1,4,3,9,6};
        int[] excepted = new int[]{2,2,2,1,4,3,3,9,6,7,19};
        Assert.assertArrayEquals(excepted, relativeSortArray(arr1, arr2));

    }

    /**
     * 快速排序
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>(arr2.length);
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }

        quickSort(arr1, 0, arr1.length-1, map);

        return arr1;
    }

    private void quickSort(int[] arr1, int left, int right, Map<Integer, Integer> map) {
        if (left >= right) {
            return;
        }

        int p = partition(arr1, left, right, map);
        quickSort(arr1, left, p-1, map);
        quickSort(arr1, p+1, right, map);

    }

    private int partition(int[] arr1, int left, int right, Map<Integer, Integer> map) {

        int flag = left;
        for (int i = left; i < right; i++) {
            if (!isGreater(arr1[i], arr1[right], map)) {
                swap(arr1, i, flag);
                flag++;
            }
        }
        swap(arr1, flag, right);

        return flag;
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private boolean isGreater(int left, int right, Map<Integer, Integer> map) {
        Integer leftValue = map.get(left);
        Integer rightValue = map.get(right);
        if (leftValue == null && rightValue == null) {
            return left > right;
        }

        if (leftValue == null && rightValue != null) {
            return true;
        }

        if (leftValue != null && rightValue == null) {
            return false;
        }

        return leftValue > rightValue;
    }
}
