package cc.tianbin.algorithm_practice.LeetCode.code100;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * 
 * 说明: 
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 *
 * 示例 1:
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * 输出: 3
 *
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 示例 2:
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * 输出: -1
 * 
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 * 
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gas-station
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/18
 */
public class M134_加油站 {

    @Test
    public void testCase() {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        int excepted = 3;
        Assert.assertEquals(excepted, canCompleteCircuit(gas, cost));

        gas = new int[]{2, 3, 4};
        cost = new int[]{3, 4, 3};
        excepted = -1;
        Assert.assertEquals(excepted, canCompleteCircuit(gas, cost));

        gas = new int[]{1,2,3,4,5};
        cost = new int[]{3,4,5,1,2};
        excepted = 3;
        Assert.assertEquals(excepted, canCompleteCircuit(gas, cost));

    }

    /**
     * 再根据自己的理解，重新写一遍
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] arr = new int[n * 2];

        for (int i = 0; i < gas.length; i++) {
            arr[i] = gas[i] - cost[i];
            arr[i + n] = gas[i] - cost[i];
        }

        // arr[i] 表示从i出发，走1步，还剩多少油
        // 求前缀和之后，arr[i] 表示从0出发，走到i，还剩多少油
        // 此时即可用 arr[y] - arr[x] 表示从x出发，走到y，还剩多少油
        for (int i = 1; i < n * 2; i++) {
            arr[i] += arr[i - 1];
        }

        // 初始化窗口
        // 窗口内 左->右，装的数字是 小->大 的
        // 目的：获取窗口内的最小值
        // 因为只要窗口内的最小值 -【出发位置】的前缀和 都>=0，说明途中没有缺油的时刻，说明可以从【出发位置】出发
        // 否则 继续往后滑动1格，从1位置出发，再看 window.peekLast()
        LinkedList<Integer> window = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!window.isEmpty() && arr[i] <= arr[window.peekLast()]) {
                window.pollLast();
            }
            window.addLast(i);
        }

        // 开始滑动
        for (int i = n, startIndex = 0, start = 0; i < n * 2; i++) {
            if (arr[window.peekFirst()] - start >= 0) {
                // 代表 从 index位置出发，走到 window.peekFirst(油耗最低点) 位置，油
                return i - n;
            }

            // 往后滑动一个，看要不要 pop
            while (!window.isEmpty() && arr[i] <= arr[window.peekLast()]) {
                window.pollLast();
            }
            // window 内部，大->小
            window.addLast(i);

            // 把队首弹出。保证 window 的大小永远是n
            while (i - window.peekFirst() >= n) {
                window.pollFirst();
            }

            //
            start = arr[startIndex++];
        }

        return -1;
    }

    /**
     * 窗口内最大值最小值的更新结构
     */
    public static int canCompleteCircuit5(int[] gas, int[] cost) {
        boolean[] good = goodArray(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] goodArray(int[] gas, int[] cost) {
        int N = gas.length;
        int M = N << 1;
        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = gas[i] - cost[i];
            arr[i + N] = gas[i] - cost[i];
        }
        // 求 arr[i] 位置的累加和。
        // i位置累加和 < 0，则代表此位置无法跑通
        for (int i = 1; i < M; i++) {
            arr[i] += arr[i - 1];
        }

        // 窗口大小为 gas 数组的大小
        LinkedList<Integer> w = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {
                w.pollLast();
            }
            w.addLast(i);
        }

        // 每次窗口向后移动1个位置，
        boolean[] ans = new boolean[N];
        for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
            if (arr[w.peekFirst()] - offset >= 0) {
                ans[i] = true;
            }
            if (w.peekFirst() == i) {
                w.pollFirst();
            }
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
                w.pollLast();
            }
            w.addLast(j);
        }
        return ans;
    }

    /**
     * 换个思路：只遍历一遍。
     * totalLeft = 总油量 - 总耗油量
     * totalLeft < 0，一定走不到。
     *
     * curLeft += gas[i] - cost[i];
     * curLeft < 0，不能从那个点出发，也不能从那个点到当前位置的任意一点出发（中间几站一定是负增长 才导致最后 < 0 的），因为最终都会导致油量不够。
     * 只能从 i+1 这一点 重新出发。
     *
     * 不用担心 i+1 = gas.length，因为如果这样的话 totalLeft 一定< 0
     */
    public int canCompleteCircuit4(int[] gas, int[] cost) {
        int curLeft = 0;
        int start = 0;
        int totalRest = 0;

        for (int i = 0; i < gas.length; i++) {
            curLeft += gas[i] - cost[i];
            totalRest += gas[i] - cost[i];

            if (curLeft < 0) {
                start = i+1;
                curLeft = 0;
            }

        }

        return totalRest < 0 ? -1 : start;
    }

    /**
     * 空间优化
     */
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int length = gas.length;

        // dp[i]: 到达i位置 还剩多少油
        int dp;

        for (int i = 0; i < length; i++) {
            dp = gas[i];

            for (int j = i + 1; j < 2 * length; j++) {
                if (j >= length) {
                    j = j % length;
                }

                int left = dp - (j == 0 ? cost[length - 1] : cost[j - 1]);

                if (left < 0) {
                    break;
                }

                if (j == i) {
                    return i;
                }

                dp = left + gas[j];
            }
        }

        return -1;
    }

    /**
     * 动态规划
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int length = gas.length;

        // dp[i]: 到达i位置 还剩多少油
        int[] dp = new int[length];

        for (int i = 0; i < length; i++) {
            dp[i] = gas[i];

            for (int j = i + 1; j < 2 * length; j++) {
                if (j >= length) {
                    j = j % length;
                }

                int left;
                if (j == 0) {
                    left = dp[length - 1] - cost[length - 1];
                } else {
                    left = dp[j - 1] - cost[j - 1];
                }

                if (left < 0) {
                    break;
                }

                if (j == i) {
                    return i;
                }

                dp[j] = left + gas[j];

            }
        }

        return -1;
    }

}
