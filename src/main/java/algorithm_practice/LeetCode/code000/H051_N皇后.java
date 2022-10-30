package algorithm_practice.LeetCode.code000;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

 [".Q..",
  "...Q",
  "Q...",
  "..Q."]

上图为 8 皇后问题的一种解法。

给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

 

示例：

输入：4
输出：[
 [".Q..",  // 解法 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // 解法 2
  "Q...",
  "...Q",
  ".Q.."]
]
解释: 4 皇后问题存在两个不同的解法。
 

提示：

皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/n-queens
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class H051_N皇后 {

    @Test
    public void testCase() {
        int n = 4;
        List<List<String>> excepted = Lists.newArrayList(
                Lists.newArrayList(".Q..", "...Q", "Q...", "..Q."),
                Lists.newArrayList(".Q..", "...Q", "Q...", "..Q.")
        );

        List<List<String>> result = solveNQueens(n);
        Assert.assertEquals(excepted, result);
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(".");
            }
            path.add(sb.toString());
        }

        return backTrack(result, path, 0);
    }

    // 路径：第 row 行之前，都已经放置好 Q 了
    // 选择列表：row 这一行的所有列
    // 结束条件：row == path.size
    private List<List<String>> backTrack(List<List<String>> result, List<String> path, int row) {
        if (row == path.size()) {
            result.add(newArrayList(path.iterator()));
            return result;
        }

        for (int column = 0; column < path.get(0).length(); column++) {
            if (!isValid(path, row, column)) {
                continue;
            }

            path = putQueen(path, row, column);

            backTrack(result, path, row + 1);

            path = popQueen(path, row, column);

        }
        return result;
    }

    private List<String> popQueen(List<String> path, int row, int column) {
        return changeQ(path, row, column, '.');
    }

    private List<String> putQueen(List<String> path, int row, int column) {
        return changeQ(path, row, column, 'Q');
    }

    private List<String> changeQ(List<String> path, int row, int column, char c) {
        char[] chars = path.get(row).toCharArray();
        chars[column] = c;

        List<String> result = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            if (i == row) {
                result.add(String.valueOf(chars));
                continue;
            }
            result.add(path.get(i));
        }

        return result;
    }

    private boolean isValid(List<String> path, int row, int column) {
        for (int i = 0; i < path.size(); i++) { // 行
            String rowList = path.get(i);
            for (int j = 0; j < rowList.length(); j++) { // 列

                // 第 column 列有 Q
                if (j == column && 'Q' == rowList.charAt(j)) {
                    return false;
                }

                // 左前右下方向 有 Q
                if (i != row && column != j
                        && row - i == column - j
                        && 'Q' == rowList.charAt(j)) {
                    return false;
                }

                // 右上左下方向 有 Q
                if (i != row && column != j
                        && row - i == j - column
                        && 'Q' == rowList.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<String> newArrayList(Iterator<String> iterator) {
        List<String> result = new ArrayList<>();

        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        return result;
    }
}
