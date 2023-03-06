package cc.tianbin.algorithmzuo.b_体系学习班.c0700_动态规划;

import org.junit.Test;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code20_NQueens {

    @Test
    public void test() {
        int n = 13;

        long start = System.currentTimeMillis();
        System.out.println(NQueen2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(NQueen1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }

    /**
     * N皇后问题
     */
    public int NQueen1(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[] record = new int[n];
        return process(record, 0);
    }

    /**
     *
     * @param record record[i] 表示 第i行，皇后饭后了 record[i] 位置上
     * @param i 当前来到了第1行。 第 0...i-1 行，都已经放好了皇后
     * @return 当前情况下，继续往下摆，有多少种摆法。
     */
    private int process(int[] record, int i) {
        if (i == record.length) {
            return 1;
        }

        int ans = 0;
        // i行的皇后，放哪一列呢？j列，
        for (int j = 0; j < record.length; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                ans += process(record, i + 1);
            }
        }

        return ans;
    }

    /**
     *
     * @param record
     * @param i 当前第 i 行
     * @param j 放在 j 位置上
     * @return 是否与 第 0...i-1 行 有冲突
     */
    private boolean isValid(int[] record, int i, int j) {
        // 0..i-1
        for (int row = 0; row < i; row++) {
            if (j == record[row] || Math.abs(record[row] - j) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 基于位运算解 N皇后问题
     * （时间复杂度不变，只是常数时间可以做到更少）
     */
    public int NQueen2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 如果你是13皇后问题，limit 最右13个1，其他都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // 7皇后问题
    // limit : 0....0 1 1 1 1 1 1 1
    // 之前皇后的列影响：colLim
    // 之前皇后的左下对角线影响：leftDiaLim
    // 之前皇后的右下对角线影响：rightDiaLim
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        // pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }
}
