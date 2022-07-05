package common.util;

import common.datastruct.LinkedNode;

/**
 * 构造链表
 * Created by nibnait on 2019-08-08
 */
public class ConstructLinkedNode {

    public static LinkedNode constructSingleLinkedNode(int[] nums) {
        LinkedNode head = new LinkedNode();
        LinkedNode curNode = head;
        for (int i = 0; i < nums.length; i++) {
            curNode.val = nums[i];
            if (i != nums.length - 1) {
                curNode.next = new LinkedNode();
                curNode = curNode.next;
            }
        }
        return head;
    }

    public static LinkedNode copySingleLinkedNode(LinkedNode head) {
        LinkedNode newHead = new LinkedNode();
        LinkedNode next = new LinkedNode();
        newHead.next = next;
        while (head != null) {
            next.val = head.val;
            if (head.next != null) {
                next.next = new LinkedNode();
            }
            next = next.next;
            head = head.next;
        }
        return newHead.next;
    }

    public static LinkedNode constructDoubleLinkedNode(int[] nums) {
        LinkedNode head = new LinkedNode();
        LinkedNode prevNode = null;

        LinkedNode next = new LinkedNode();
        head.next = next;
        for (int i = 0; i < nums.length; i++) {
            next.val = nums[i];
            next.prev = prevNode;
            if (i != nums.length - 1) {
                next.next = new LinkedNode();
            }

            prevNode = next;
            next = next.next;
        }
        return head.next;
    }

    public static LinkedNode copyDoubleLinkedNode(LinkedNode head) {
        LinkedNode newHead = new LinkedNode();
        LinkedNode next = new LinkedNode();
        newHead.next = next;
        LinkedNode prevNode = null;
        while (head != null) {
            next.val = head.val;
            if (head.next != null) {
                next.next = new LinkedNode();
            }
            next.prev = prevNode;

            prevNode = next;
            next = next.next;
            head = head.next;
        }
        return newHead.next;
    }
}
