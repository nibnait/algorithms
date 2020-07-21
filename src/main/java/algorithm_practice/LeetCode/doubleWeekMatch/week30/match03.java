package algorithm_practice.LeetCode.doubleWeekMatch.week30;

import java.util.Arrays;

public class match03 {

  public static void main(String[] args) {
    System.out.println("" + new match03().minDifference(new int[]{5, 3, 2, 4}));
    System.out.println("" + new match03().minDifference(new int[]{1, 5, 0, 10, 14}));
    System.out.println("" + new match03().minDifference(new int[]{6, 6, 0, 1, 1, 4, 6}));
    System.out.println("" + new match03().minDifference(new int[]{1, 5, 6, 14, 15}));
    System.out.println("" + new match03().minDifference(new int[]{82, 81, 95, 75, 20}));
  }

  public int minDifference(int[] nums) {
    if (nums.length <= 4) {
      return 0;
    }
    Arrays.sort(nums);
    int a1 = nums[nums.length - 4] - nums[0];
    int a2 = nums[nums.length - 3] - nums[1];
    int a3 = nums[nums.length - 2] - nums[2];
    int a4 = nums[nums.length - 1] - nums[3];
    return Math.min(Math.min(a1, a2), Math.min(a3, a4));
  }
}
