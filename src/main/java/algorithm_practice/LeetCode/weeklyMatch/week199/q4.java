package algorithm_practice.LeetCode.weeklyMatch.week199;

import java.util.ArrayList;

public class q4 {

  public static void main(String[] args) {
    System.out.println(new q4().getLengthOfOptimalCompression("aaabcccd", 2));
    System.out.println(new q4().getLengthOfOptimalCompression("aabbaa", 2));
  }

  public int getLengthOfOptimalCompression(String s, int k) {
    ArrayList<Character> c = new ArrayList<>();
    ArrayList<Integer> n = new ArrayList<>();

    char current = s.charAt(0);
    int cnt=1;
    for(int i=1; i<s.length(); i++) {
      if(current == s.charAt(i)) {
        cnt++;
      } else {
        c.add(current);
        n.add(cnt);
        current = s.charAt(i);
        cnt =1;
      }
    }
    c.add(current);
    n.add(cnt);
    int min =1;
    while(k!=0) {
      for(int i=0; i<n.size(); i++) {
        if(min == n.get(i) && k!=0) {
          if(k >= min) {
            n.remove(i);
            c.remove(i);
            k = k -min;
          } else {
            n.set(i, n.get(i) - k);
            k = 0;
          }
        }
      }
      min ++;
    }

    StringBuilder sb = new StringBuilder();
    for(int i=0; i<c.size(); i++) {
      sb.append(c.get(i));
      if(n.get(i)!=1) {
        sb.append(n.get(i));
      }
    }
    System.out.println(sb.toString());
    return sb.toString().length();
  }
}
