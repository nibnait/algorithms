package algorithmzuo.b_体系学习班.c0700_动态规划;

import common.CommonConstants;
import common.util.SysOut;
import common.util.SysRandom;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by nibnait on 2022/11/13
 */
public class Code09_Coffee {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int[] arr = SysRandom.randomArrNaturalNumRandomLength(10);

        int n = (int) (Math.random() * 7) + 1;
        int a = (int) (Math.random() * 7) + 1;
        int b = (int) (Math.random() * 10) + 1;
        int ans1 = minTime(arr, n, a, b);
        int ans2 = dp(arr, n, a, b);
        if (ans1 != ans2) {
            SysOut.printArray(arr);
            SysOut.printf("n={} 个人\n a={} 洗干净\n b={} 挥发干净\n", a, b, n);
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }
    }

    /**
     * 暴力递归
     */
    private class Machine {
        public int timePoint;
        public int workTime;

        public Machine(int timePoint, int workTime) {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }
    }

    private class MachineComparetor implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    public int minTime(int[] arr, int n, int a, int b) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparetor());
        for (int workTime : arr) {
            heap.add(new Machine(0, workTime));
        }

        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }

        return bestTime(drinks, a, b, 0, 0);
    }

    /**
     *
     * @param drinks 所有被子可以洗的时间
     * @param wash 单杯洗干净的时间点（串行）
     * @param air 挥发干净的时间点（并行）
     * @param free 洗的机器可以用的时间点
     * @param index 当前到了第 index 号杯子
     * @return drinks[index] 都变干净，最早的结束时间
     */
    private int bestTime(int[] drinks, int wash, int air, int free, int index) {
        if (index == drinks.length) {
            return 0;
        }

        // index号杯子 决定洗
        int selfClean1 = Math.max(drinks[index], free) + wash;
        int restClean1 = bestTime(drinks, wash, air, selfClean1, index + 1);
        int p1 = Math.max(selfClean1, restClean1);

        // index号杯子 决定挥发
        int selfClean2 = drinks[index] + air;
        int restClean2 = bestTime(drinks, wash, air, free, index + 1);
        int p2 = Math.max(selfClean2, restClean2);

        return Math.min(p1, p2);
    }

    /**
     * dp 数组
     */
    public int dp(int[] arr, int n, int a, int b) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparetor());
        for (int workTime : arr) {
            heap.add(new Machine(0, workTime));
        }

        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }

        return bestTime(drinks, a, b);
    }

    private int bestTime(int[] drinks, int wash, int air) {
        int N = drinks.length;
        int maxFree = 0;
        for (int i = 0; i < drinks.length; i++) {
            maxFree = Math.max(maxFree, drinks[i]) + wash;
        }
        int[][] dp = new int[N + 1][maxFree + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int free = 0; free <= maxFree; free++) {
                int selfClean1 = Math.max(drinks[index], free) + wash;
                if (selfClean1 > maxFree) {
                    break; // 因为后面的也都不用填了
                }
                // index号杯子 决定洗
                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);
                // index号杯子 决定挥发
                int selfClean2 = drinks[index] + air;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }
}
