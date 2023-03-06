package cc.tianbin.common.util;

import cc.tianbin.common.datastruct.ListNode;
import cc.tianbin.common.datastruct.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * 比较两个数组
     */
    public static boolean compareArray(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareArrayIgnoreOrder(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareListList(List<List<Integer>> expect, List<List<Integer>> actual) {
        if (expect.size() != actual.size()) {
            return false;
        }

        expect = expect.stream().sorted(comparator).collect(Collectors.toList());
        actual = actual.stream().sorted(comparator).collect(Collectors.toList());

        for (int i = 0; i < expect.size(); i++) {
            List<Integer> expectList = expect.get(i).stream().sorted().collect(Collectors.toList());
            if (!compareList(expectList, actual.get(i))) {
                return false;
            }
        }

        return true;
    }

    private static Comparator<List<Integer>> comparator = new Comparator<List<Integer>>() {
        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            return o1.hashCode() - o2.hashCode();
        }
    };

    public static boolean compareList(List<Integer> expectList, List<Integer> actualList) {
        if (expectList.size() != actualList.size()) {
            return false;
        }

        expectList = expectList.stream().sorted().collect(Collectors.toList());
        actualList = actualList.stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < expectList.size(); i++) {
            if (!Objects.equals(expectList.get(i), actualList.get(i))) {
                return false;
            }
        }
        return true;
    }
}
