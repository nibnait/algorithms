package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_04_递归和动态规划;

import junit.framework.TestCase;
import org.junit.Test;

/*
  N 个位置，（N >= 2）
  机器人在 M 位置上，（1 <= M <= N）
  只走 K 步，
  到达 P 位置。（1 <= P <= N）


C
reated by nibnait on 2020-03-15
 */
public class P03_机器人达到指定位置方法数 extends TestCase {

    @Test
    public void testCase() {
        int N = 5, M = 2, K = 3, P = 3;
        System.out.println(way1(N, M, K, P));
        System.out.println(way2(N, M, K, P));

        int N1 = 3, M1 = 1, K1 = 3, P1 = 3;
        System.out.println(way1(N1, M1, K1, P1));
        System.out.println(way2(N1, M1, K1, P1));

        int N2 = 7, M2 = 4, K2 = 9, P2 = 5;
        System.out.println(way1(N2, M2, K2, P2));
        System.out.println(way2(N2, M2, K2, P2));

    }

    private int way2(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }

        int[][] dp = new int[M][K];

        return 0;

    }


    private int way1(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 1 || P < 1 || P > N) {
            return 0;
        }

        this.N = N;
        this.P = P;
        return walk(M, K);
    }

    /**
     * 暴力递归
     *
     * @param n 一共N个位置
     * @param cur 当前位置cur
     * @param rest 还剩rest步
     * @param p 到达p位置
     * @return 方法数
     */
    private int N = 0;
    private int P = 0;

    private int walk(int cur, int rest) {
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }

        if (cur == 1) {
            return walk(cur + 1, rest - 1);
        }

        if (cur == N) {
            return walk(cur - 1, rest - 1);
        }

        return walk(cur + 1, rest - 1) + walk(cur - 1, rest - 1);
    }

}
