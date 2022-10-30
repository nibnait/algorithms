package common.util;

import common.datastruct.ListNode;
import common.datastruct.TreeNode;

/**
 * Created by nibnait on 2022/04/11
 */
public class CompareUtils {

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
     * 对比两棵二叉树
     */
    public static boolean isSameValueStructure(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.val != head2.val) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    /**
     * 三目最大值
     */
    public static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    /**
     * 三目最小值
     */
    public static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
