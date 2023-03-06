package cc.tianbin.algorithm_practice.LeetCode.code900;

/*
给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。

对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

你可以返回任何满足上述条件的数组作为答案。

 

示例：

输入：[4,2,5,7]
输出：[4,5,2,7]
解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 

提示：

2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/12
 */
public class E922_按奇偶排序数组2 {

    public int[] sortArrayByParityII(int[] A) {

        int p1 = 0;
        int p2 = 1;

        while (p1 < A.length && p2 < A.length) {
            if (A[p1] % 2 == 0) {
                p1 += 2;
            } else {
                // 找到一个偶数 与其交换
                while (p2 < A.length) {
                    if (A[p2] % 2 == 0) {
                        swap(A, p1, p2);
                        break;
                    } else {
                        p2 += 2;
                    }
                }
                p1 += 2;
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
