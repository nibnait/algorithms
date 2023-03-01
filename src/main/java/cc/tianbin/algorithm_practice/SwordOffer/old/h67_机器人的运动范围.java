package cc.tianbin.algorithm_practice.SwordOffer.old;

/**
 * 题目：地上有个 m 行 n 列的方格。一个机器人从坐标(0,0)的格子开始移动，
 *      它每一次可以向左、右、上、下移动一格，但不能进入行坐标和列坐标的数位之和大于 k 的格子。
 * 例如，当 k 为 18 时，机器人能够进入方格(35,37)，因为 3+5+3+7=18
 *                       但它不能进入方格(35,38)，因为 3+5+3+8=19
 *       请问该机器人能够达到多少格子？
 *
 *【解】
 *  和前面的"h66_矩阵中的路径"类似，这个方格也可以看出一个 m*n 的矩阵。同样在这个矩阵中，除边界上的格子之外其他格子都有四个相邻的格子。
 *
 *  机器人从坐标(0,0)开始移动。
 *  当它准备进入坐标为(i,j)的格子时，通过检查坐标的数位和来判断机器人是否能够进入。
 *      如果机器人能够进入坐标为(i,j)的格子，我们接着再判断它能否进入四个相邻的格子(i,j-1)、(i-1,j),(i,j+1) 和 (i+1,j)。
 *
 *
 *
 * Created by nibnait on 2016/10/5.
 */
public class h67_机器人的运动范围 {

    /**
     *
     * @param threshold 约束值
     * @param rows      方格的行数
     * @param cols      方格的列数
     * @return 最多可走的方格
     */
    public static int movingCount(int threshold, int rows, int cols) {
        // 参数校验
        if (threshold < 0 || rows < 1 || cols < 1) {
            return 0;
        }

        // 变量初始化
        boolean[] visited = new boolean[rows * cols];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        return movingCountCore(threshold, rows, cols, 0, 0, visited);
    }

    /**
     * 递归回溯方法
     *
     * @param threshold 约束值
     * @param rows      方格的行数
     * @param cols      方格的列数
     * @param row       当前处理的行号
     * @param col       当前处理的列号
     * @param visited   访问标记数组
     * @return 最多可走的方格
     */
    private static int movingCountCore(int threshold, int rows, int cols,
                                       int row, int col, boolean[] visited) {

        int count = 0;

        if (check(threshold, rows, cols, row, col, visited)) {
            visited[row * cols + col] = true;
            count = 1
                    + movingCountCore(threshold, rows, cols, row - 1, col, visited)
                    + movingCountCore(threshold, rows, cols, row, col - 1, visited)
                    + movingCountCore(threshold, rows, cols, row + 1, col, visited)
                    + movingCountCore(threshold, rows, cols, row, col + 1, visited);
        }

        return count;
    }

    /**
     * 断机器人能否进入坐标为(row, col)的方格
     *
     * @param threshold 约束值
     * @param rows      方格的行数
     * @param cols      方格的列数
     * @param row       当前处理的行号
     * @param col       当前处理的列号
     * @param visited   访问标记数组
     * @return 是否可以进入，true是，false否
     */
    private static boolean check(int threshold, int rows, int cols,
                                 int row, int col, boolean[] visited) {
        return col >= 0 && col < cols
                && row >= 0 && row < rows
                && !visited[row * cols + col]
                && (getDigitSum(col) + getDigitSum(row) <= threshold);
    }

    /**
     * 一个数字的数位之和
     *
     * @param number 数字
     * @return 数字的数位之和
     */
    private static int getDigitSum(int number) {
        int result = 0;
        while (number > 0) {
            result += (number % 10);
            number /= 10;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(5, 10, 10) + "--21");
        System.out.println(movingCount(15, 20, 20) + "--359");
        System.out.println(movingCount(10, 1, 100) + "--29");
        System.out.println(movingCount(10, 1, 10) + "--10");
        System.out.println(movingCount(15, 100, 1) + "--79");
        System.out.println(movingCount(15, 10, 1) + "--10");
        System.out.println(movingCount(5, 10, 10) + "--21");
        System.out.println(movingCount(12, 1, 1) + "--1");
        System.out.println(movingCount(-10, 10, 10) + "--0");
    }
}