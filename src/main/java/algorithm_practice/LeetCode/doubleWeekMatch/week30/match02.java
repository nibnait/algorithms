package algorithm_practice.LeetCode.doubleWeekMatch.week30;

import java.util.Arrays;

public class match02 {

  public static void main(String[] args) {
    System.out.printf("" + new match02().rangeSum(new int[]{1,2,3,4}, 4, 3, 4));
  }

  public int rangeSum(int[] nums, int n, int left, int right) {
    int[] arr = new int[(n * (n + 1) / 2)];
    int idx = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        arr[idx] = sum(nums, i, j);
        idx++;
      }
    }
    Arrays.sort(arr);
    return sum(arr, left-1, right-1);
  }

  private int sum(int[] nums, int start, int end) {
    int res = 0;
    for(int i=start; i<=end; i++) {
      res += nums[i];
    }
    return res;
  }
}
