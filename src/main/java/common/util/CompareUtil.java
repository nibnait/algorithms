package common.util;

import common.datastruct.ListNode;

/**
 * Created by nibnait on 2020/11/20
 */
public class CompareUtil {

    public static boolean compareListNode(ListNode left, ListNode right) {
        while (left != null && right != null && left.val == right.val) {
            left = left.next;
            right = right.next;
        }
        return left == right;
    }

}
