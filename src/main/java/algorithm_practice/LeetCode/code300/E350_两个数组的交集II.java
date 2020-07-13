package algorithm_practice.LeetCode.code300;

import java.util.ArrayList;
import java.util.Arrays;

public class E350_两个数组的交集II {

  /*
  给定两个数组，编写一个函数来计算它们的交集。

 

示例 1：

输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
示例 2:

输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]
 

说明：

输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
我们可以不考虑输出结果的顺序。
进阶：

如果给定的数组已经排好序呢？你将如何优化你的算法？
如果 nums1 的大小比 nums2 小很多，哪种方法更优？
如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    System.out.println(Arrays.toString(intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
    System.out.println(Arrays.toString(intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
  }

  /*
  1.双指针
比较常规的一道问题，只需要对两个数组进行排序，然后维护双指针 i, j
扫一遍两个数组直到某个指针先扫完数组，如果arrayA[i] == arrayB[j]，
则把这个值放入result，i++同时j++，否则arrayA[i] 和arrayB[j]中对应较少的那个i或j ++，终止条件是遇到数组结尾。
   */
  public static int[] intersect(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int i = 0;
    int j = 0;
    int[] res = new int[Math.max(nums1.length, nums2.length)];
    int index = 0;
    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] == nums2[j]) {
        res[index] = nums1[i];
        i++;
        j++;
        index ++;
      } else {
        if (nums1[i] < nums2[j]) {
          i++;
        } else {
          j++;
        }
      }
    }
    return Arrays.copyOfRange(res, 0, index);
  }
}
