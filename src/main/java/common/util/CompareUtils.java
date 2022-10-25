package common.util;

import common.datastruct.ListNode;

/**
 * Created by nibnait on 2022/04/11
 */
public class CompareUtils {

    /**
     * 对比两个链表
     */
    public static boolean compareSingleListNode(ListNode left, ListNode right) {
        while (left != null && right != null && left.val == right.val) {
            left = left.next;
            right = right.next;
        }
        return left == right;
    }

    public static boolean compareDoubleListNode(ListNode left, ListNode right) {
        while (left != null && right != null && left.val == right.val) {
            left = left.next;
            right = right.next;
        }
        if (left != right) {
            return false;
        }
        while (left != null && right != null && left.val == right.val) {
            left = left.prev;
            right = right.prev;
        }
        return left == right;
    }

    /**
     * 正序排列
     */
    public static boolean isSortAsc(int[] arr) {
        if (arr == null || arr.length < 2) {
            return true;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        return true;
    }

}
