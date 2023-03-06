package cc.tianbin.algorithm_practice.LeetCode.code900;

import org.junit.Test;

/*
给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。

你可以返回满足此条件的任何数组作为答案。

 

示例：

输入：[3,1,2,4]
输出：[2,4,3,1]
输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 

提示：

1 <= A.length <= 5000
0 <= A[i] <= 5000


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-array-by-parity
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/12
 */
public class E905_按奇偶排序数组 {

    public int[] sortArrayByParity(int[] A) {

        int p1 = 0;
        int p2 = A.length-1;

        while (p1 < p2) {
            if (A[p1] % 2 == 0) {
                p1++;
            } else {
                swap(A, p1, p2);
                p2--;
            }
        }

        return A;
    }

    private void swap(int[] A, int p1, int p2) {
        int temp = A[p1];
        A[p1] = A[p2];
        A[p2] = temp;
    }

}
