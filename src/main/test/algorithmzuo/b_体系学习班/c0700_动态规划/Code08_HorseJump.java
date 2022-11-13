package algorithmzuo.b_体系学习班.c0700_动态规划;

import common.util.SysOut;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code08_HorseJump {

    @Test
    public void test() {
        int x = 7;
        int y = 7;
        int k = 10;

        int ans1 = ways(x, y, k);
        int ans2 = dp(x, y, k);

        if (ans1 != ans2) {
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
        }
    }

    private static final int tableXLength = 9;
    private static final int tableYLength = 10;

    /**
     * 从(0,0) 出发，必须走 k 步，落在 (x, y) 位置
     * <p>
     * 有多少种走法？
     */
    public int ways(int x, int y, int k) {
        if (x < 0 || x >= tableXLength || y < 0 || y >= tableYLength) {
            return 0;
        }
        return process(x, y, 0, 0, k);
    }

    /**
     * 终点 (x, y)
     * 当前走到了 (curX, curY)
     * 还剩 rest 步
     * <p>
     * 有多少种走法？
     */
    private int process(int x, int y, int curX, int curY, int rest) {
        if (curX < 0 || curX >= tableXLength || curY < 0 || curY >= tableYLength) {
            return 0;
        }
        if (rest == 0) {
            return x == curX && y == curY ? 1 : 0;
        }

        int ways = process(x, y, curX + 1, curY + 2, rest - 1);
        ways += process(x, y, curX + 2, curY + 1, rest - 1);
        ways += process(x, y, curX + 2, curY - 1, rest - 1);
        ways += process(x, y, curX + 1, curY - 2, rest - 1);
        ways += process(x, y, curX - 1, curY - 2, rest - 1);
        ways += process(x, y, curX - 2, curY - 1, rest - 1);
        ways += process(x, y, curX - 2, curY + 1, rest - 1);
        ways += process(x, y, curX - 1, curY + 2, rest - 1);
        return ways;
    }

    /**
     * 转成 dp 数组
     * <p>
     * 再优化，就是把 搞两个二维数组即可
     */
    public int dp(int x, int y, int k) {
        if (x < 0 || x >= tableXLength || y < 0 || y >= tableYLength) {
            return 0;
        }

        int[][][] dp = new int[tableXLength][tableYLength][k + 1];
        dp[x][y][0] = 1;

        for (int rest = 1; rest <= k; rest++) {
            for (int curX = 0; curX < tableXLength; curX++) {
                for (int curY = 0; curY < tableYLength; curY++) {
                    int ways = getValue(dp, curX + 1, curY + 2, rest - 1);
                    ways += getValue(dp, curX + 2, curY + 1, rest - 1);
                    ways += getValue(dp, curX + 2, curY - 1, rest - 1);
                    ways += getValue(dp, curX + 1, curY - 2, rest - 1);
                    ways += getValue(dp, curX - 1, curY - 2, rest - 1);
                    ways += getValue(dp, curX - 2, curY - 1, rest - 1);
                    ways += getValue(dp, curX - 2, curY + 1, rest - 1);
                    ways += getValue(dp, curX - 1, curY + 2, rest - 1);
                    dp[curX][curY][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    private int getValue(int[][][] dp, int x, int y, int k) {
        if (x < 0 || x >= tableXLength || y < 0 || y >= tableYLength) {
            return 0;
        }
        return dp[x][y][k];
    }
}
