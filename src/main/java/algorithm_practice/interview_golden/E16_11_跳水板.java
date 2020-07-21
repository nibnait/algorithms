package algorithm_practice.interview_golden;

import java.util.TreeSet;
import sun.security.jgss.GSSHeader;

public class E16_11_跳水板 {

  /*
  你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。

返回的长度需要从小到大排列。

示例：

输入：
shorter = 1
longer = 2
k = 3
输出： {3,4,5,6}
提示：

0 < shorter <= longer
0 <= k <= 100000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/diving-board-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  public static void main(String[] args) {
    int[] toBe = new int[]{3, 4, 5, 6};
    int[] res = divingBoard(1, 2, 3);
    System.out.println((toBe.length == res.length));
    for (int i = 0; i < toBe.length; i++) {
      System.out.println((toBe[i] == res[i]));
    }
    int[] toBe2 = new int[]{};
    int[] res2 = divingBoard(1, 1, 0);
    System.out.println(toBe2.length == res2.length);
  }

  /*
  	1. 利用TreeSet，遍历所有的0*x + k*y到 k*x + 0 * y的组合，TreeSet自己具备有序性，考虑特殊场景，如果k == 0 直接return 空数组
   */
  public static int[] divingBoard1(int shorter, int longer, int k) {
    if (k == 0) {
      return new int[]{};
    }
    TreeSet<Integer> ts = new TreeSet<>();
    for (int i = 0; i <= k; i++) {
      ts.add(shorter * i + longer * (k - i));
    }
    int[] res = new int[ts.size()];
    final int[] index = {0};
    ts.forEach(val -> {
      res[index[0]] = val;
      index[0]++;
    });
    return res;
  }

  /*
	  2. 数学方法，如果k==0，return空数组，如果x == y，那么只有一种k*x的结果，否则是等差数列，从小的那个数x开始，每次增加(y - x)直到增加了k个y-x

   */
  public static int[] divingBoard(int shorter, int longer, int k) {
    if (k == 0) {
      return new int[]{};
    }
    if (shorter == longer) {
      return new int[]{shorter * k};
    }
    int[] res = new int[k + 1];
    int adder = longer - shorter;
    for (int i = 0; i <= k; i++) {
      res[i] =  shorter * k + i * adder;
    }
    return res;
  }
}
