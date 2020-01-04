package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_01_栈和队列;

import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

/*
【题目】
有一个整型数组arr 和一个大小为w 的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
例如，数组为[4,3,5,4,3,3,6,7]，窗口大小为3 时：

[4 3 5] 4 3 3 6 7 窗口中最大值为5
4 [3 5 4] 3 3 6 7 窗口中最大值为5
4 3 [5 4 3] 3 6 7 窗口中最大值为5
4 3 5 [4 3 3] 6 7 窗口中最大值为4
4 3 5 4 [3 3 6] 7 窗口中最大值为6
4 3 5 4 3 [3 6 7] 窗口中最大值为7
如果数组长度为n，窗口大小为w，则一共产生n-w+1 个窗口的最大值。

请实现一个函数。
输入：整型数组arr，窗口大小为w。
输出：一个长度为n-w+1 的数组res，res[i]表示每一种窗口状态下的最大值。以本题为例，结果应该返回{5,5,5,4,6,7}。

【难度】
尉 ★★☆☆
 */
public class P07_生成窗口最大值数组 extends TestCase {

    @Test
    public void testCase() {
        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        int w = 3;
        int[] maxWindowArr = getMaxWindow(arr, w);
        SysOut.printArray(maxWindowArr);
    }

    /**
     * 暴力破解谁都会，时间复杂度为O(N * w)
     * <p>
     * 既然学了栈和队列，那就用双端队列，队列的头保存当前窗口最大值的坐标。【坐标也可以用于校验当前窗口的最大值（当前队列头）是否过期】
     * <p>
     * 使时间复杂度降为O(N)
     *
     * @param arr
     * @param w
     * @return
     */
    private int[] getMaxWindow(int[] arr, int w) {
        if (arr==null || arr.length==0 || w<=0) {
            return new int[0];
        }
        int[] maxWindowArr = new int[arr.length - w + 1];
        LinkedList<Integer> windowQueue = new LinkedList<>();   //用来装当前窗口的范围坐标

        if (w == 1) {
            return arr;
        }

        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!windowQueue.isEmpty() && arr[i] > arr[windowQueue.peekLast()]) {
                //为了保证当前窗口的队列的头，永远是当前窗口的最大值的坐标【arr[i]最大】
                windowQueue.pollLast();
            }
            windowQueue.addLast(i);

            // 校验当前队列头的坐标是否过期
            if (windowQueue.peekFirst() == i - w) {
                windowQueue.pollFirst();
            }

            // 可以开始给maxWindowArr赋值了
            if (i+1-w >= 0) {
                maxWindowArr[index++] = arr[windowQueue.peekFirst()];
            }
        }

        return maxWindowArr;
    }


}
