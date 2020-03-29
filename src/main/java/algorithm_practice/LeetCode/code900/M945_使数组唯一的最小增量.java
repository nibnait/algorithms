package algorithm_practice.LeetCode.code900;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

/*
给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。

返回使 A 中的每个值都是唯一的最少操作次数。

示例 1:

输入：[1,2,2]
输出：1
解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
示例 2:

输入：[3,2,1,2,1,7]
输出：6
解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
提示：

0 <= A.length <= 40000
0 <= A[i] < 40000


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
Created by nibnait on 2020-03-22
 */
public class M945_使数组唯一的最小增量 extends TestCase {

    @Test
    public void testCase() {

        int[] A = new int[]{1, 2, 2};
        System.out.println(minIncrementForUnique(A));

        int[] A1 = new int[]{3, 2, 1, 2, 1, 7};
        System.out.println(minIncrementForUnique(A1));


    }

    /**
     * 1. 先排序
     * 2. 如果（前一项A[i-1] == 后一项A[i]）
     *        则将A[i-1]加到A[i]+1
     *        A[i] = A[i-1] +1
     *
     *    A[i-1] > A[i]  也是同理
     */
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);

        int move = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] >= A[i]) {
                move += A[i - 1] - A[i] + 1;
                A[i] = A[i - 1] + 1;
            }
        }

        return move;
    }
}
