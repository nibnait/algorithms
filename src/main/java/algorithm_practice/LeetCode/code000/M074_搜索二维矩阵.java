package algorithm_practice.LeetCode.code000;

import junit.framework.TestCase;
import org.junit.Test;

/*
编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
每行中的整数从左到右按升序排列。
每行的第一个整数大于前一行的最后一个整数。

示例 1:
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
输出: true

示例 2:
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
输出: false

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-a-2d-matrix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-07
 */
public class M074_搜索二维矩阵 extends TestCase {

    @Test
    public void testCase() {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        int target1 = 3;
        int target2 = 13;
        System.out.println(searchMatrix(matrix, target1));
        System.out.println(searchMatrix(matrix, target2));

        int[][] matrix3 = {{1}};
        int target3 = 0;
        System.out.println(searchMatrix(matrix3, target3));

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int lo = 0;
        int hi = matrix[0].length - 1;
        int rowNum = getRowNum(matrix, target);
        int targetIndex = searchTarget(lo, hi, matrix[rowNum], target);
        return matrix[rowNum][targetIndex] == target;
    }

    private int getRowNum(int[][] matrix, int target) {
        int[] nums = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            nums[i] = matrix[i][0];
        }
        int lo = 0;
        int hi = matrix.length - 1;
        return searchTarget(lo, hi, nums, target);
    }

    private int searchTarget(int lo, int hi, int[] nums, int target) {
        while (lo <= hi) {
            int mid = (lo + hi + 1) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo - 1 >= 0 ? lo - 1 : lo;
    }
}
