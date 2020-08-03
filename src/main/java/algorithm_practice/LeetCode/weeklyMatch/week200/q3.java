package algorithm_practice.LeetCode.weeklyMatch.week200;

public class q3 {

  public static void main(String[] args) {
    int[][] test = new int[][]{new int[]{1, 0, 0, 0}, new int[]{1, 1, 1, 1}, new int[]{1, 0, 0, 0},
        new int[]{1, 0, 0, 0}};
    System.out.println(new q3().minSwaps(test));
  }

  public int minSwaps(int[][] grid) {
    int n = grid.length;
    int res = 0;
    int[] idx = new int[n];
    for (int i = 0; i < n; i++) {
      idx[i] = lastP(grid[i]);
    }

    for (int i = 0; i < n; i++) {
      int firstI = findFirst(idx, i);
      //non suit
      if (firstI == -1) {
        return -1;
      }

      res += firstI - i;
//      System.out.println("res = " +res);
      swapTo(idx, firstI, i);
    }
    return res;
  }

  private void swapTo(int[] before, int lastP, int startP) {
    int tmp = before[lastP];
    for (int i = lastP; i > startP; i--) {
      before[i] = before[i - 1];
    }
    before[startP] = tmp;
  }

  private int findFirst(int[] arr, int target) {
    for (int i = target; i < arr.length; i++) {
      if (arr[i] <= target) {
        return i;
      }
    }
    return -1;
  }

  private int lastP(int[] arr) {
    for (int i = arr.length - 1; i >= 0; i--) {
      if (arr[i] == 1) {
        return i;
      }
    }
    return 0;
  }
}
