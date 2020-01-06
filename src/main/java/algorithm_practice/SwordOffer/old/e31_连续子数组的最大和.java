package algorithm_practice.SwordOffer.old;

/**
 * 题目：输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为 O(n)。
 *
 * /src/Algorithm.nowcoder/b_2nd_Season/bb160727/README.md：
 * 左神做法：
     cur 依次累加各个元素，一旦cur为负数时，则将cur清为零。
     并尝试更新一次result（最大值）
     最终返回result即为子数组的最大累加和

     解释：
     因为最大和的子数组：其任意数量的前缀一定不为负。
     也就是 cur如果没有累加出到负数，就继续往下走。   即模拟了“前缀不可能为负数”的情况。

 *
 *
 * Created by nibnait on 2016/10/1.
 */
public class e31_连续子数组的最大和 {

    public static void main(String[] args) {
        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
        int[] data2 = {-2, -8, -1, -5, -9};
        int[] data3 = {2, 8, 1, 5, 9};
        System.out.println(findGreatestSumOfSubArray(data));
        System.out.println(findGreatestSumOfSubArray(data2));
        System.out.println(findGreatestSumOfSubArray(data3));
    }

    private static int findGreatestSumOfSubArray(int[] arr) {
        if (arr==null || arr.length<=0){
            return 0;
        }

        int cur = arr[0];
        int res = cur;
        for (int i = 1; i < arr.length; i++) {
            cur += arr[i];
            res = Math.max(res, cur);
            cur = cur>0? cur:0;
        }
        return res;
    }
}
