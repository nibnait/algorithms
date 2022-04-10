package common.util;

import common.datastruct.ListNode;

/**
 * Created by nibnait on 2022/04/11
 */
public class CompareUtils {

    /**
     * 对比两个链表
     */
    public static boolean compareListNode(ListNode left, ListNode right) {
        while (left != null && right != null && left.val == right.val) {
            left = left.next;
            right = right.next;
        }
        return left == right;
    }

}
