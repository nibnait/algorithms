package cc.tianbin.algorithmzuo.b_体系学习班.c0300_贪心;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by nibnait on 2022/11/02
 */
public class Code04_IPO {

    @Test
    public void test() {
        int K = 3;
        int W = 3;
        int[] profits = new int[]{2,3,5,5,453};
        int[] costs = new int[]{3,1,2,6,23};
        int result = findMaximizedCapital(K, W, profits, costs);
        Assert.assertEquals(16, result);

    }

    /**
     * @param K 最多能做 K 个项目
     * @param W 启动资金
     * @param profits 每i个项目的利润
     * @param costs 第i个项目的花费
     * @return 最后获得的最大钱数
     */
    private int findMaximizedCapital(int K, int W, int[] profits, int[] costs) {
        return 0;
    }

    @AllArgsConstructor
    private static class Project {
        public int profit;
        public int cost;
    }
}
