package algorithmzuo.b_体系学习班.c0400_并查集;

import common.util.CopyUtil;
import common.util.SysRandom;
import org.junit.Test;

import java.util.*;

/**
 * Created by nibnait on 2022/11/03
 */
public class Code02_NumberOfIslands {

    @Test
    public void test() {
        int row = 0;
        int col = 0;
        char[][] board1 = null;
        char[][] board2 = null;
        char[][] board3 = null;
        long start = 0;
        long end = 0;

        row = 1000;
        col = 1000;
        board1 = SysRandom.generateMatrixZeroOneChar(row, col);
        board2 = CopyUtil.copy(board1);
        board3 = CopyUtil.copy(board1);

        System.out.println("感染方法、并查集(map实现)、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + numIslands3(board1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行结果: " + numIslands1(board2));
        end = System.currentTimeMillis();
        System.out.println("并查集(map实现)的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行结果: " + numIslands2(board3));
        end = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

        System.out.println();

        row = 10000;
        col = 10000;
        board1 = SysRandom.generateMatrixZeroOneChar(row, col);
        board3 = CopyUtil.copy(board1);
        System.out.println("感染方法、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

        start = System.currentTimeMillis();
        System.out.println("感染方法的运行结果: " + numIslands3(board1));
        end = System.currentTimeMillis();
        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行结果: " + numIslands2(board3));
        end = System.currentTimeMillis();
        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

    }

    /**
     * https://leetcode.cn/problems/number-of-islands/
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     */
    public int numIslands1(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        Dot[][] dots = new Dot[rows][cols];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dots[i][j] = new Dot();
                dotList.add(dots[i][j]);
            }
        }

        UnionFind1 unionFind = new UnionFind1(dotList);

        // 第1列
        for (int i = 1; i < rows; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                unionFind.union(dots[i - 1][0], dots[i][0]);
            }
        }

        // 第1排
        for (int i = 1; i < cols; i++) {
            if (board[0][i - 1] == '1' && board[0][i] == '1') {
                unionFind.union(dots[0][i - 1], dots[0][i]);
            }
        }

        // (1, 1) 开始，和它的左边+上边 union
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (board[i][j] == '1' && board[i - 1][j] == '1') {
                    unionFind.union(dots[i][j], dots[i - 1][j]);
                }
                if (board[i][j] == '1' && board[i][j - 1] == '1') {
                    unionFind.union(dots[i][j], dots[i][j - 1]);
                }
            }
        }

        return unionFind.size();
    }

    private class Dot {
    }

    private class UnionFind1 {
        private Map<Dot, Dot> fathers;
        private Map<Dot, Integer> sizeMap;

        public UnionFind1(List<Dot> dotList) {
            fathers = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Dot dot : dotList) {
                fathers.put(dot, dot);
                sizeMap.put(dot, 1);
            }
        }

        public Dot findAncestor(Dot dot) {
            Stack<Dot> stack = new Stack<>();
            while (dot != fathers.get(dot)) {
                stack.push(dot);
                dot = fathers.get(dot);
            }
            while (!stack.isEmpty()) {
                Dot d = stack.pop();
                fathers.put(d, dot);
            }
            return dot;
        }

        public void union(Dot d1, Dot d2) {
            Dot d1Head = findAncestor(d1);
            Dot d2Head = findAncestor(d2);
            if (d1Head == d2Head) {
                return;
            }

            Integer d1Size = sizeMap.get(d1Head);
            Integer d2Size = sizeMap.get(d2Head);
            Dot big = d1Size > d2Size ? d1Head : d2Head;
            Dot small = big == d1Head ? d2Head : d1Head;
            fathers.put(small, big);
            sizeMap.put(big, d1Size + d2Size);
            sizeMap.remove(small);
        }

        public int size() {
            return sizeMap.size();
        }

    }

    public int numIslands2(char[][] board) {
        return 1;
    }

    public int numIslands3(char[][] board) {
        int isLands = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    isLands++;
                    infect(board, i, j);
                }
            }
        }
        return isLands;
    }

    private void infect(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != '1') {
            return;
        }

        board[i][j] = '2';
        infect(board, i - 1, j);
        infect(board, i, j - 1);
        infect(board, i + 1, j);
        infect(board, i, j + 1);
    }

}
