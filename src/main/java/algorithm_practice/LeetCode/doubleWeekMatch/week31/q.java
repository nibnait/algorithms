package algorithm_practice.LeetCode.doubleWeekMatch.week31;

public class q {

  public static void main(String[] args) {

    System.out.println(minFlips("10111"));
  }
  public static int minFlips(String target) {
    int res = 0;
    char flag = '0';
    int n = target.length();
    for(int i=0; i<n; i++) {
      if(target.charAt(i)!=flag) {

        res++;
        flag = reverse(flag);
      }
    }
    return res;
  }

  private static char reverse(char flag) {
    if(flag == '0') {
      return '1';
    }
    return '0';
  }
}
