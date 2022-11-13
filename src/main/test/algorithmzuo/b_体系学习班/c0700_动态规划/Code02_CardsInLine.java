package algorithmzuo.b_体系学习班.c0700_动态规划;

import common.CommonConstants;
import common.util.SysOut;
import common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/12
 */
@Slf4j
public class Code02_CardsInLine {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_10; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int[] arr = SysRandom.randomArrNaturalNumRandomLength(20);
        SysOut.printArray(arr);

        int ans1 = winRecur(arr);
        int ans2 = winDp(arr);

        if (ans1 != ans2) {
            SysOut.printArray(arr);
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }

        SysOut.println(ans1);
    }

    /**
     * 两人抽牌，返回获胜者 获得的点数
     */
    public int winRecur(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int first = processFirst(arr, 0, arr.length - 1);
        int second = processSecond(arr, 0, arr.length - 1);

        return Math.max(first, second);
    }

    // 第1个抽牌的人。返回当前牌面的最好结果
    private int processFirst(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }

        int p1 = arr[left] + processSecond(arr, left + 1, right);
        int p2 = arr[right] + processSecond(arr, left, right - 1);
        return Math.max(p1, p2);
    }

    // 第2个抽牌的人。返回当前牌面的最好结果
    private int processSecond(int[] arr, int left, int right) {
        if (left == right) {
            // 就剩1张牌了，但是被对手拿走了。
            return 0;
        }

        // 对手拿了当前的 arr[left]，只能争取在 left+1, right 上 去拿最好结果了
        int p1 = processFirst(arr, left + 1, right);
        // 对手拿了当前的 arr[right]
        int p2 = processFirst(arr, left, right - 1);
        // 大值已经被对手拿走了，所以只剩下小值了。
        return Math.min(p1, p2);
    }


    /**
     * dp数组
     */
    public int winDp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int length = arr.length;
        int[][] firstMap = new int[length][length];
        int[][] secondMap = new int[length][length];

        for (int i = 0; i < length; i++) {
            firstMap[i][i] = arr[i];
        }

        for (int startCol = 1; startCol < length; startCol++) {
            // 对角线 斜着填
            int row = 0;
            int col = startCol;
            while (col < length) {
                firstMap[row][col] = Math.max(arr[row] + secondMap[row + 1][col], arr[col] + secondMap[row][col - 1]);
                secondMap[row][col] = Math.min(firstMap[row + 1][col], firstMap[row][col - 1]);
                row++;
                col++;
            }
        }

        return Math.max(firstMap[0][length - 1], secondMap[0][length - 1]);
    }

}
