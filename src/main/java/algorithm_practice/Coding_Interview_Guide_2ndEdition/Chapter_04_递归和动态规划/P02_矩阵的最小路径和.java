package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_04_递归和动态规划;

import junit.framework.TestCase;
import org.junit.Test;

/*
  
Created by nibnait on 2020-03-15
 */
public class P02_矩阵的最小路径和 extends TestCase {

    @Test
    public void testCase() {
        int[][] matrix = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        System.out.println(minPathSum1(matrix));
        System.out.println(minPathSum2(matrix));
        System.out.println(minPathSum3(matrix));

        int[][] matrix1 = new int[][]{
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };
        System.out.println(minPathSum1(matrix1));
        System.out.println(minPathSum2(matrix1));
        System.out.println(minPathSum3(matrix1));


    }

    private int minPathSum3(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        boolean rowMoreColumn = matrix.length > matrix[0].length;
        int shorterArrayLength = rowMoreColumn ? matrix[0].length : matrix.length;
        int longerArrayLength = rowMoreColumn ? matrix[0].length : matrix.length;

        int[] dp = new int[shorterArrayLength];
        dp[0] = matrix[0][0];

        for (int i = 1; i < shorterArrayLength; i++) {
            dp[i] = dp[i - 1] + (rowMoreColumn ? matrix[0][i] : matrix[i][0]);
        }

        for (int i = 1; i < longerArrayLength; i++) {
            dp[0] = dp[0] + (rowMoreColumn ? matrix[i][0] : matrix[0][i]);
            for (int j = 1; j < shorterArrayLength; j++) {
                int currentValue = rowMoreColumn ? matrix[i][j] : matrix[j][i];
                dp[j] = Math.min(dp[j - 1], dp[j]) + currentValue;
            }
        }

        return dp[shorterArrayLength - 1];
    }

    private int minPathSum2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int[] dp = new int[matrix[0].length];
        dp[0] = matrix[0][0];
        for (int i = 1; i < matrix[0].length; i++) {
            dp[i] = dp[i - 1] + matrix[0][i];
        }

        for (int i = 1; i < matrix.length; i++) {
            dp[0] = dp[0] + matrix[i][0];
            for (int j = 1; j < matrix[0].length; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + matrix[i][j];
            }
        }

        return dp[dp.length - 1];
    }

    private int minPathSum1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0];

        for (int i = 1; i < matrix.length; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < matrix[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }

        return dp[matrix.length - 1][matrix[0].length - 1];
    }

}
