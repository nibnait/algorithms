package algorithm_practice.nowcoder.b_2nd_Season.ba160720;

/**
 * 雨水补漏（leetCode42）
 * Created by nibnait on 2016/9/9.
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap2(a));
    }

    /**
     * 自然方法：
         两个数组：L[],R[]
         L[i]：表示从左边一直到当前位置的最大值
         然后比较L[i]、R[i]、arr[i]
         计算出当前位置的sum，依次累加
         时间复杂度：  3* O(N)
         额外空间复杂度：    2* O(N)
     * 改进：
         L[i]就是遍历到当前位置的最大值，一个变量即可搞定！
         时间复杂度：  2* O(N)
         额外空间复杂度：    O(N)
     *
     * @param arr
     * @return
     */
    public static int trap1(int[] arr) {
        int length = arr.length;
        if (arr==null || length<=3){
            return 0;
        }

        int[] Rmax = new int[length];
        Rmax[length-1] = arr[length-1];
        int Lmax = arr[0];
        int sum = 0;
        for (int i = length - 2; i >= 0; i--) {
            Rmax[i] = arr[i]>Rmax[i+1]? arr[i]: Rmax[i+1];
        }
        for (int i = 1; i < length; i++) {
            Lmax = arr[i]>Lmax? arr[i]: Lmax;
            int min = Lmax<Rmax[i]? Lmax: Rmax[i];
            sum += Math.max(min-arr[i], 0);
        }

        return sum;
    }

    /**
     * 神级方法：
     * 两个指针 L、R
     * 哪边的LMAX，RMAX更小，结算哪边当前水量，指针往中间移动一位，（并尝试更新最大值）
     * 相遇即停止
     *
     * @param arr
     * @return
     */
    public static int trap2(int[] arr) {
        int sum = 0;
        int length = arr.length;
        int Lmax = arr[0];
        int Rmax = arr[length-1];
        int L = 1;
        int R = length-2;
        while (L<=R){
            if (Lmax <= Rmax){
                sum += Math.max(Lmax-arr[L], 0);
                Lmax = Math.max(Lmax, arr[L++]);
            }else {
                sum += Math.max(Rmax-arr[R], 0);
                Rmax = Math.max(Rmax, arr[R--]);
            }
        }
        return sum;
    }


}
