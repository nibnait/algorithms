package cc.tianbin.algorithm_practice.LeetCode.code500;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 *
 *
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 *
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 *
 *
 * 提示：
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 *
 * https://leetcode.cn/problems/number-of-provinces
 * Created by nibnait on 2023/03/30
 */
public class M547_省份数量 {


    @Test
    public void tes() {
        int[][] isConnected = new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        int result = findCircleNum(isConnected);
        int expect = 2;
        Assert.assertEquals(expect, result);

        isConnected = new int[][]{
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        result = findCircleNum(isConnected);
        expect = 1;
        Assert.assertEquals(expect, result);


        isConnected = new int[][]{
                {1, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1}
        };
        result = findCircleNum(isConnected);
        expect = 3;
        Assert.assertEquals(expect, result);

    }


    private class Dot {
    }

    private class UnionFind {
        private Map<Dot, Integer> sizeMap = new HashMap<>();
        private Map<Dot, Dot> fatherMap = new HashMap<>();

        public int size() {
            return this.sizeMap.size();
        }

        public void addDot(Dot dot) {
            sizeMap.put(dot, 1);
            fatherMap.put(dot, dot);
        }

        public void union(Dot dot1, Dot dot2) {
            Dot head1 = findAncestor(dot1);
            Dot head2 = findAncestor(dot2);

            if (head1 == head2) {
                return;
            }

            int head1Size = sizeMap.get(head1);
            int head2Size = sizeMap.get(head2);
            Dot big = head1Size > head2Size ? head1 : head2;
            Dot small = head1Size > head2Size ? head2 : head1;

            sizeMap.put(big, head1Size + head2Size);
            sizeMap.remove(small);
            fatherMap.put(small, big);
        }

        private Dot findAncestor(Dot dot) {
            Stack<Dot> stack = new Stack<>();
            while (dot != fatherMap.get(dot)) {
                stack.push(dot);
                dot = fatherMap.get(dot);
            }

            while (!stack.isEmpty()) {
                Dot pop = stack.pop();
                fatherMap.put(pop, dot);
            }

            return dot;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        UnionFind unionFind = new UnionFind();

        Dot[] dots = new Dot[isConnected.length];
        for (int i = 0; i < isConnected[0].length; i++) {
            dots[i] = new Dot();
            unionFind.addDot(dots[i]);
        }

        for (int i = 0; i < isConnected[0].length; i++) {
            for (int j = 0; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(dots[i], dots[j]);
                }
            }
        }

        return unionFind.size();
    }

}
