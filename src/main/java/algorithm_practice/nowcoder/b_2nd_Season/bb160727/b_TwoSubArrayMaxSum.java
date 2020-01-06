package algorithm_practice.nowcoder.b_2nd_Season.bb160727;

/**
 * 求两个子数组最大的累加和
 *
 * Created by nibnait on 2016/9/10.
 */
public class b_TwoSubArrayMaxSum {

    public static void main(String[] args) {
        int[] arr = new int[]{3,-2,1,-6,4,3,-2,3};
        System.out.println(maxSum(arr));
    }

    public static int maxSum(int[] arr) {

        int length = arr.length;
        int[] Rmax = new int[length];
        int cur = arr[length-1];
        Rmax[length-1] = cur;
        for (int i=length-2; i>=0; i--){
            cur = cur>0? cur: 0;
            cur += arr[i];
            Rmax[i] = Math.max(Rmax[i], cur);
        }// Rmax遍历完毕

        cur = arr[0];
        int Lmax = arr[0];
        int res = Lmax + Rmax[1];
        for (int i=1; i<length-1; i++){
            cur = cur>0? cur: 0;
            cur += arr[i];
            Lmax = Math.max(cur, Lmax);
            res = Math.max(res, Lmax+Rmax[i+1]);
        }
        return res;
    }


}
