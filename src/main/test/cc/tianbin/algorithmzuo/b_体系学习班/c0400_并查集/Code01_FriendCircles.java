package cc.tianbin.algorithmzuo.b_体系学习班.c0400_并查集;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/03
 */
public class Code01_FriendCircles {

    @Test
    public void testCase() {
        int[][] M = new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        int circleNum = findCircleNum(M);
        Assert.assertEquals(1, circleNum);

        M = new int[][]{
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        circleNum = findCircleNum(M);
        Assert.assertEquals(1, circleNum);

        M = new int[][]{
                {1, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1}
        };
        circleNum = findCircleNum(M);
        Assert.assertEquals(1, circleNum);
    }

    /**
     * https://leetcode.cn/problems/number-of-provinces/
     * <p>
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
     * 返回矩阵中 省份 的数量。
     */
    public int findCircleNum(int[][] M) {
        return 1;
    }

}
