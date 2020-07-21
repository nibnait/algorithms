package algorithm_practice.LeetCode.code100;

import java.util.Arrays;

public class E167_两数之和II输入有序数组 {

  /*
  给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

说明:

返回的下标值（index1 和 index2）不是从零开始的。
你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
示例:

输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
  }

  /*
  解题思路：
1.双指针
地位指针low，高位指针high，当arr[low] + arr[high] > target时，high --；
 当arr[low] + arr[hig] < target是，low++；
 当arr[low] + arr[high] == target时， return low , high
   */
  public static int[] twoSum(int[] numbers, int target) {
    int low = 0;
    int high = numbers.length - 1;
    while (numbers[low] + numbers[high] != target) {
      if (numbers[low] + numbers[high] > target) {
        high--;
      } else {
        low++;
      }
    }
    return new int[]{low + 1, high + 1};
  }
}
