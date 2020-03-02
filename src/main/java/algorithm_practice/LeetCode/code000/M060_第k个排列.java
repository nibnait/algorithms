package algorithm_practice.LeetCode.code000;

import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

/*
给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

"123"
"132"
"213"
"231"
"312"
"321"
给定 n 和 k，返回第 k 个排列。

说明：

给定 n 的范围是 [1, 9]。
给定 k 的范围是[1,  n!]。
示例 1:

输入: n = 3, k = 3
输出: "213"
示例 2:

输入: n = 4, k = 9
输出: "2314"


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutation-sequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-01-26
 */
public class M060_第k个排列 extends TestCase {

    @Test
    public void testCase() {
        System.out.println(getPermutation(3, 3));
        System.out.println(getPermutation(3, 1));
        System.out.println(getPermutation(4, 9));
    }

    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        int[] result = getKthPermutation(nums, k);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(result[i]);
        }
        return sb.toString();
    }




    private int[] getKthPermutation(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }

        int p1 = nums.length-1;
        int pEnd = nums.length-1;
        int p2, pFind;

        k--;

        while (p1 > 0) {
            p2 = p1;
            p1--;

            if (nums[p1] < nums[p2]) {
                // 第一个递增序列
                pFind = pEnd;
                while (nums[pFind] < nums[p1]) {
                    // 从后往前找比替换点大的第一个数
                    --pFind;
                }
                swap(nums, p1, pFind);

                //替换点后面的数全部反转
                reverse(nums, p2, pEnd);

                if (k-- == 1) {
                    return nums;
                }

                p1 = pEnd;
            }
        }

        return nums;
    }

    private void reverse(int[] nums, int p2, int pEnd) {
        while (p2 < pEnd) {
            swap(nums, p2++, pEnd--);
        }
    }

    private void swap(int[] nums, int begin, int end) {
        int temp = nums[begin];
        nums[begin] = nums[end];
        nums[end] = temp;
    }

}
