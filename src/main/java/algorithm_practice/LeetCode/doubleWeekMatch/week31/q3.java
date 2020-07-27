package algorithm_practice.LeetCode.doubleWeekMatch.week31;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class q3 {

  public static void main(String[] args) {
    System.out.println(new q3().numSplits("acbadbaada"));
  }

  public int numSplits(String s) {
    int l = s.length();
    if(l == 0) {
      return 0;
    }
    int[] farr = new int[l];
    int[] barr = new int[l];
    Set<Character> fset = new HashSet<>();
    Set<Character> bset = new HashSet<>();
    farr[0] =1;
    fset.add(s.charAt(0));
    for(int i=1; i<l;i++) {
      if(fset.contains(s.charAt(i))) {
        farr[i] = farr[i-1];
      } else {
        farr[i] = farr[i-1] +1;
        fset.add(s.charAt(i));
      }
    }

    barr[l-1] =1;
    bset.add(s.charAt(l-1));
    for(int j=l-2;j>=0;j--) {
      if(bset.contains(s.charAt(j))) {
        barr[j] = barr[j+1];
      } else {
        barr[j] = barr[j+1] +1;
        bset.add(s.charAt(j));
      }
    }

    int res = 0;
    for(int i=0;i<l-1;i++) {
      if(farr[i] == barr[i+1]) {
        res++;
      }
    }
    return res;

  }

}
