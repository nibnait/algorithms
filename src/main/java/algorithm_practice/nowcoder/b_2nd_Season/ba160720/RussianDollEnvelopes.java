package algorithm_practice.nowcoder.b_2nd_Season.ba160720;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 俄国沙皇问题（leetCode354）
 * 二元组 最多可以摞几个？
 *
 * 策略：
 *      先 a 小-->大，（a = a', 再 b 大-->小 排序）
 *      此时，h[]中，放的是b的值。
 *      即：因为a值是升序排列的，所以 要想摞更多的二元组，则b同时也得是升序排列的。
 *          这时，只关注b的值，即 按照最长递增子序列O(N* logN)的思想，即可求出 可以摞出最多的二元组的个数。
 *
 *
 * Created by nibnait on 2016/9/9.
 */
public class RussianDollEnvelopes {

    public static void main(String[] args) {
        int[][] doll = new int[][]{{1,2},{3,7},{4,3},{5,4},{6,5}};
        System.out.println(maxEnvelopes(doll));
    }

    public static class Dot{
        int a;
        int b;
        public Dot(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    /**
     * 比较器
     */
    public static class DotComparator implements Comparator<Dot>{

        @Override
        public int compare(Dot d1, Dot d2) {
            if (d1.a == d2.a){  //a相等时
                return d2.b - d1.b;     //b降序排列
            }else {
                return d1.a - d2.a;
            }
        }
    }

    public static int maxEnvelopes(int[][] doll) {

        Dot[] dots = new Dot[doll.length];
        for (int i = 0; i < doll.length; i++) {
            dots[i] = new Dot(doll[i][0], doll[i][1]);
        }
        Arrays.sort(dots, new DotComparator());

        int h[] = new int[dots.length];
        int j = 0;
        h[0] = dots[0].b;
        for (int i = 0; i < dots.length; i++) {
            int lo = 0;
            int hi = j;
            while (lo <= hi){
                int mid = (lo + hi) / 2;
                if (h[mid] < dots[i].b){
                    lo = mid +1;
                }else {
                    hi = mid -1;
                }
            }
            h[lo] = dots[i].b;
            j = lo>j? lo: j;
        }
        return j+1;
    }

}
