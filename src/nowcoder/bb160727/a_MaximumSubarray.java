package nowcoder.bb160727;

/**
 * 子数组最大和（leetcode 53）
 * Created by nibnait on 2016/9/10.
 */
public class a_MaximumSubarray {

    public static void main(String[] args) {
        int[] arr = new int[]{3,-2,1,-6,4,3,-2,3};
        System.out.println(maxSubArray(arr));
    }

    public static int maxSubArray(int[] arr) {
        if (arr==null || arr.length<0){
            return 0;
        }

        int cur = arr[0];
        int res = cur;
        for (int i = 1; i < arr.length; i++) {
            cur = cur>0? cur:0;
            cur += arr[i];
            res = Math.max(res, cur);
        }
        return res;
    }

}
