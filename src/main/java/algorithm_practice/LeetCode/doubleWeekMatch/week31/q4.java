package algorithm_practice.LeetCode.doubleWeekMatch.week31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class q4 {

  public static void main(String[] args) {
//    System.out.println(new q4().minNumberOperations(new int[]{1,2,3,2,1}));

    char[] a = new char[]{'a', 'b', 'c'};
    String s = String.valueOf(a);
    System.out.println(s);
  }

  public int minNumberOperations(int[] target) {
    Map<Integer, List<Integer>> cntMap = new HashMap<>();
    TreeSet<Integer> ts = new TreeSet<>();
    for (int i = 0; i < target.length; i++) {
      ts.add(target[i]);
      if (cntMap.containsKey(target[i])) {
        cntMap.get(target[i]).add(i);
      } else {
        List<Integer> lst = new ArrayList<>();
        lst.add(i);
        cntMap.put(target[i], lst);
      }
    }
    List<Integer> mIdx = new ArrayList<>();

    int res = 0;
    for (int i = cntMap.size() - 1; i > 1; i--) {
      int num = ts.pollLast();
      mIdx.addAll(cntMap.get(num));
      int sub = num - ts.last();
      res += process(mIdx) * sub;
    }
    return res;
  }

  private int process(List<Integer> mIdx) {
    int cnt = 1 ;
    int[] arr = new int[mIdx.size()];
    for(int i=0;i<arr.length; i++) {
      arr[i] = mIdx.get(i);
    }
    Arrays.sort(arr);
    for(int i=1; i<mIdx.size(); i++) {
      if(arr[i] - arr[i-1] !=1) {
        cnt ++;
      }
    }
    return cnt;
  }
}
