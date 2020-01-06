package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_01_栈和队列;

import junit.framework.TestCase;
import org.junit.Test;
import common.util.SysOut;

import java.util.LinkedList;

/*
【题目】
给定数组arr 和整数num，共返回有多少个子数组满足如下情况：

max(arr[i..j]) - min(arr[i..j]) <= num

max(arr[i..j])表示子数组arr[i..j]中的最大值，min(arr[i..j])表示子数组arr[i..j]中的最小值。

【要求】
如果数组长度为N，请实现时间复杂度为O(N)的解法。

【难度】
校 ★★★☆

 */
public class P10_最大值减去最小值小于或等于num的子数组数量 extends TestCase {

    @Test
    public void testCase() {
        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        int num = 3;

        SysOut.print("共有%s个子数组", getNum(arr, num));
    }

    /**
     * 参考“生成窗口最大值数组”的双端队列，
     *  此处需要两个双端队列，一个qmax 队列的头是当前窗口的最大值的坐标
     *                     一个qmin 队列的头是当前窗口的最小值的坐标
     *  arr[i...j]  i从1开始
     *                  j++，直到max(arr[i...j]) - min(arr[i...j]) 大于 num
     *                      此时arr[i...j]、arr[i...j-1]、arr[i...j-2]、...、arr[i...i]都满足题议
     *                  res += j-i;
     *               i++
     * @param arr
     * @param num
     * @return
     */
    private int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }

        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int i = 0, j = 0;
        int res = 0;

        while (i < arr.length) {
            while (j < arr.length) {
                if (qmin.isEmpty() || qmin.peekLast() != j) {   //一旦大于，则停止j++
                    if (!qmax.isEmpty() && arr[j] > arr[qmax.peekFirst()]) {
                        qmax.pollFirst();
                    }
                    qmax.addLast(j);
                    if (!qmin.isEmpty() && arr[j] < arr[qmin.peekFirst()]) {
                        qmin.pollFirst();
                    }
                    qmin.addLast(j);
                }

                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                j++;
            }

            res += j - i;

            //判断qmax.peekFirst 和 qmin.peekFirst是否过期
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            i++;
        }

        return res;
    }
}
