package algorithm_practice.nowcoder.b_2nd_Season.bd160810;

import common.util.SysOut;

import java.util.LinkedList;

/**
 * 双端队列的操作
 * <p>
 * Created by nibnait on 2016/9/13.
 */
public class SlidingWindowMaxValue {

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int w = 3;
        SysOut.printArray(getWindowMaxValue(arr, w));
    }

    private static int[] getWindowMaxValue(int[] arr, int w) {

        if (arr == null || w < 1 || arr.length < w) {
            return null;
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
