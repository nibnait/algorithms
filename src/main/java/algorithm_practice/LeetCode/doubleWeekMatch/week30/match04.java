package algorithm_practice.LeetCode.doubleWeekMatch.week30;

public class match04 {

  public static void main(String[] args) {
    for(int i=1; i<48; i++) {
      System.out.println("i=" + i + " is " + new match04().winnerSquareGame(i));
    }
  }

  public boolean winnerSquareGame(int n) {
    boolean[] dp = new boolean[n+1];
    for(int i=1; i<=n; i++) {
      if(isPower(i)) {
        dp[i] = true;
        continue;
      }
      dp[i] = process(dp, i);
    }
    return dp[n];
  }

  private boolean process(boolean[] dp, int target) {
    boolean res = true;
    for(int i=1; i<=Math.sqrt(target)+1; i++) {
      if(target - i*i >0) {
        res = res && dp[target - i*i];
      }
    }
    return !res;
  }

  private boolean isPower(int i) {
    for(int a =1; a<Math.sqrt(i)+1; a++) {
      if(a*a == i) {
        return true;
      }
    }
    return false;
  }
}
