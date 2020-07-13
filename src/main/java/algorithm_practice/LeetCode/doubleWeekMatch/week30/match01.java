package algorithm_practice.LeetCode.doubleWeekMatch.week30;

import java.util.HashMap;
import java.util.Map;

public class match01 {

  public static void main(String[] args) {
    System.out.println(reformatDate("6th Jun 1933"));
  }

  public static String reformatDate(String date) {
    String[] s = date.split(" ");
    int day = processDay(s[0]);
    Map<String, Integer> monthMap = new HashMap<>();
    monthMap.put("Jan", 1);
    monthMap.put("Feb", 2);
    monthMap.put("Mar", 3);
    monthMap.put("Apr", 4);
    monthMap.put("May", 5);
    monthMap.put("Jun", 6);
    monthMap.put("Jul", 7);
    monthMap.put("Aug", 8);
    monthMap.put("Sep", 9);
    monthMap.put("Oct", 10);
    monthMap.put("Nov", 11);
    monthMap.put("Dec", 12);
    int month = monthMap.get(s[1]);
    String ms = fillZero(month);
    int year = Integer.parseInt(s[2]);
    String ds = fillZero(day);
    return year + "-" + ms + "-" + ds;
  }

  private static String fillZero(int month) {
    if(month<10) {
      return "0" + month;
    }
    return String.valueOf(month);
  }

  private static int processDay(String s) {
    if (s.length() == 3) {
      return Integer.parseInt(s.substring(0, 1));
    } else {
      return Integer.parseInt(s.substring(0, 2));
    }
  }
}
