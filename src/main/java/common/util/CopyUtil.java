package common.util;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/11/03
 */
public class CopyUtil {

    public static int[] copy(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static char[][] copy(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        char[][] ans = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[i][j] = board[i][j];
            }
        }
        return ans;
    }
}
