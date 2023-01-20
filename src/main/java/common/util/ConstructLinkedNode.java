package common.util;

import common.datastruct.ListNode;

import java.util.List;

/**
 * 构造链表
 * Created by nibnait on 2019-08-08
 */
public class ConstructLinkedNode {

    public static ListNode constructSingleLinkedNode(List<Integer> nums) {
        ListNode head = new ListNode();
        ListNode curNode = head;
        for (int i = 0; i < nums.size(); i++) {
            curNode.val = nums.get(i);
            if (i != nums.size() - 1) {
                curNode.next = new ListNode();
                curNode = curNode.next;
            }
        }
        return head;
    }

    public static ListNode constructSingleLinkedNode(int[] nums) {
        ListNode head = new ListNode();
        ListNode curNode = head;
        for (int i = 0; i < nums.length; i++) {
            curNode.val = nums[i];
            if (i != nums.length - 1) {
                curNode.next = new ListNode();
                curNode = curNode.next;
            }
        }
        return head;
    }

    public static ListNode copySingleLinkedNode(ListNode head) {
        ListNode newHead = new ListNode();
        ListNode next = new ListNode();
        newHead.next = next;
        while (head != null) {
            next.val = head.val;
            if (head.next != null) {
                next.next = new ListNode();
            }
            next = next.next;
            head = head.next;
        }
        return newHead.next;
    }

    public static ListNode constructDoubleLinkedNode(int[] nums) {
        ListNode head = new ListNode();
        ListNode prevNode = null;

        ListNode next = new ListNode();
        head.next = next;
        for (int i = 0; i < nums.length; i++) {
            next.val = nums[i];
            next.prev = prevNode;
            if (i != nums.length - 1) {
                next.next = new ListNode();
            }

            prevNode = next;
            next = next.next;
        }
        return head.next;
    }

    public static ListNode copyDoubleLinkedNode(ListNode head) {
        ListNode newHead = new ListNode();
        ListNode next = new ListNode();
        newHead.next = next;
        ListNode prevNode = null;
        while (head != null) {
            next.val = head.val;
            if (head.next != null) {
                next.next = new ListNode();
            }
            next.prev = prevNode;

            prevNode = next;
            next = next.next;
            head = head.next;
        }
        return newHead.next;
    }
}
