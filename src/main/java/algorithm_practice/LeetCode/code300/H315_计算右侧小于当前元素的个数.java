package algorithm_practice.LeetCode.code300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class H315_计算右侧小于当前元素的个数 {

  /*
  给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

示例:

输入: [5,2,6,1]
输出: [2,1,1,0]
解释:
5 的右侧有 2 个更小的元素 (2 和 1).
2 的右侧仅有 1 个更小的元素 (1).
6 的右侧有 1 个更小的元素 (1).
1 的右侧有 0 个更小的元素.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   */
  private int[] c;
  private int[] a;

  public static void main(String[] args) {
    int[] result = new int[]{2, 1, 1, 0};
    List<Integer> cal = new H315_计算右侧小于当前元素的个数().countSmaller(new int[]{5, 2, 6, 1});
    for (int i = 0; i < result.length; i++) {
      System.out.println(result[i] == cal.get(i));
    }
  }

  /*
  解法：看hints就知道这道题肯定有多种解决方法，而树状数组和线段树代表了需要新的数据结构，所以这道题被我归类到新的数据结构类问题的范畴。
朴素的思维来看就是从右向左遍历数组，遍历到每个元素的同事记录一下已经遍历的每个元素值的个数，然后在res的该位置设置为已经遍历过的元素中小于当前值的元素的个数和。可以看到这时候就需要一个高效的数据结构提供以下功能：
	1. 利用较少的空间按顺序记录每个已经遍历过的元素以及他们出现的次数
	2. 利用最快的时间找到小于某个元素的那些值在数组中的个数
这时候就需要使用到树状数组，同时这个数组可能是很稀疏的，有很多的0，我们需要对其进行离散化操作以节约空间。

「树状数组」是一种可以动态维护序列前缀和的数据结构，它的功能是：

单点更新 update(i, v)： 把序列 ii 位置的数加上一个值 vv，在该题中 v = 1v=1
区间查询 query(i)： 查询序列 [1 \cdots i][1⋯i] 区间的区间和，即 ii 位置的前缀和
修改和查询的时间代价都是 O(\log n)O(logn)，其中 nn 为需要维护前缀和的序列的长度。

   */
  public List<Integer> countSmaller(int[] nums) {
    List<Integer> resultList = new ArrayList<>();
    discretization(nums);
    init(nums.length + 5);
    for (int i = nums.length - 1; i >= 0; --i) {
      int id = getId(nums[i]);
      resultList.add(query(id - 1));
      update(id);
    }
    Collections.reverse(resultList);
    return resultList;
  }

  private void init(int length) {
    c = new int[length];
    Arrays.fill(c, 0);
  }

  private int lowBit(int x) {
    return x & (-x);
  }

  private void update(int pos) {
    while (pos < c.length) {
      c[pos] += 1;
      pos += lowBit(pos);
    }
  }

  private int query(int pos) {
    int ret = 0;
    while (pos > 0) {
      ret += c[pos];
      pos -= lowBit(pos);
    }

    return ret;
  }

  private void discretization(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }
    int size = set.size();
    a = new int[size];
    int index = 0;
    for (int num : set) {
      a[index++] = num;
    }
    Arrays.sort(a);
  }

  private int getId(int x) {
    return Arrays.binarySearch(a, x) + 1;
  }
}
