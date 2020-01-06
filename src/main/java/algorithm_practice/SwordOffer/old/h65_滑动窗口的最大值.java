package algorithm_practice.SwordOffer.old;

import common.util.SysOut;

import java.util.LinkedList;

/**
 * 题目：给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
 * 那么一共存在 6 个滑动窗口，它们的最大值分别为{4,4,6,6,6,5}。
 *
 *【解】：用一个双端队列
 *  队列里存的是数组元素的下标
 *  当前队列头永远放的是当前窗口的最大值
 *
 * Created by nibnait on 2016/10/5.
 */
public class h65_滑动窗口的最大值 {

    public static void main(String[] args) {
        // expected {7};
        int[] data1 = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        SysOut.printArray(maxInWindows(data1, 10));
        // expected {3, 3, 5, 5, 6, 7};
        int[] data2 = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        SysOut.printArray(maxInWindows(data2, 3));
        // expected {7, 9, 11, 13, 15};
        int[] data3 = new int[]{1, 3, 5, 7, 9, 11, 13, 15};
        SysOut.printArray(maxInWindows(data3, 4));
        // expected  {16, 14, 12};
        int[] data5 = new int[]{16, 14, 12, 10, 8, 6, 4};
        SysOut.printArray(maxInWindows(data5, 5));
        // expected  {10, 14, 12, 11};
        int[] data6 = new int[]{10, 14, 12, 11};
        SysOut.printArray(maxInWindows(data6, 1));
        // expected  {14};
        int[] data7 = new int[]{10, 14, 12, 11};
        SysOut.printArray(maxInWindows(data7, 4));
    }

    private static int[] maxInWindows(int[] arr, int w) {

        if (arr == null || arr.length<=0 || w < 1) {
            throw new IllegalArgumentException("Invalid args");
        }
        if (arr.length < w){
            int max = arr[0];
            for(int i : arr){
                max = max>i? max : i;
            }
            return new int[]{max};
        }

        LinkedList<Integer> deque = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1];
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && arr[deque.peekFirst()] <= arr[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            if (deque.peekFirst() <= i - w) {
                deque.pollFirst();
            }
            if (i >= w - 1) {
                res[cnt++] = arr[deque.peekFirst()];
            }
        }

        return res;
    }
}
