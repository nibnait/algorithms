package algorithm_practice.SwordOffer.old;

import java.util.HashMap;

/**
 * 题目：请实现函数 ComplexListNode clone(ComplexListNode head)，复制一个复杂链表。
 * 在复杂链表中，每个结点除了有一个 next 域指向下一个结点外，还有一个 sibling 指向链表中的任意结点或者 null。
 *  【解】：
 *  法一：
 *      用HashMap保存(1,1.val)、(2,2.val)、...之间的对应关系
 *
 *  法二：
 *      先将链表化成 1->1'->2->2'->...
 *
 * Created by nibnait on 2016/9/26.
 */
public class d26_复杂链表的复制 {

    static class ComplexListNode {
        int value;
        ComplexListNode next;
        ComplexListNode sibling;

        public ComplexListNode() {
        }

        public ComplexListNode(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        //          -----------------
        //         \|/              |
        //  1-------2-------3-------4-------5
        //  |       |      /|\             /|\
        //  --------+--------               |
        //          -------------------------
        ComplexListNode head = new ComplexListNode();
        head.value = 1;
        head.next = new ComplexListNode();
        head.next.value = 2;
        head.next.next = new ComplexListNode();
        head.next.next.value = 3;
        head.next.next.next = new ComplexListNode();
        head.next.next.next.value = 4;
        head.next.next.next.next = new ComplexListNode();
        head.next.next.next.next.value = 5;
        head.sibling = head.next.next;
        head.next.sibling = head.next.next.next.next;
        head.next.next.next.sibling = head.next;
        printList(head);
        ComplexListNode newHead = clone2(head);
        printList(newHead);

        // 有指向自身的情况
        //          -----------------
        //         \|/              |
        //  1-------2-------3-------4-------5
        //         |       | /|\           /|\
        //         |       | --             |
        //         |------------------------|
        ComplexListNode head2 = new ComplexListNode();
        head2.value = 1;
        head2.next = new ComplexListNode();
        head2.next.value = 2;
        head2.next.next = new ComplexListNode();
        head2.next.next.value = 3;
        head2.next.next.next = new ComplexListNode();
        head2.next.next.next.value = 4;
        head2.next.next.next.next = new ComplexListNode();
        head2.next.next.next.next.value = 5;
        head2.next.sibling = head2.next.next.next.next;
        head2.next.next.next.sibling = head2.next.sibling;
        head2.next.next.sibling = head2.next.next;
        System.out.println("\n");
        printList(head2);
        ComplexListNode newHead2 = clone2(head2);
        printList(newHead2);

        ComplexListNode head3 = new ComplexListNode();
        head3.value = 1;
        System.out.println("\n");
        printList(head3);
        ComplexListNode newHead3 = clone2(head3);
        printList(newHead3);

        System.out.println("\n");
        ComplexListNode head4 = clone2(null);
        printList(head4);
    }

    private static void printList(ComplexListNode head) {

        ComplexListNode cur = head;
        System.out.println("order: ");
        while (cur != null) {
            System.out.print(cur.value + "-> ");
            cur = cur.next;
        }
        System.out.println("null");
        cur = head;
        System.out.println("sibling:  ");
        while (cur != null) {
            System.out.println(cur.value + ".sibling -> " + (cur.sibling == null ? "null" : cur.sibling.value));
            cur = cur.next;
        }

    }

    /**
     * 法1：用HashMap
     *
     * @param head
     * @return
     */
    private static ComplexListNode clone(ComplexListNode head) {

        HashMap<ComplexListNode, ComplexListNode> map = new HashMap<>();
        ComplexListNode cur = head;
        while (cur != null) {
            map.put(cur, new ComplexListNode(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).sibling = map.get(cur.sibling);
            cur = cur.next;
        }

        return map.get(head);
    }

    /**
     * 法2： 1->1'->2->2'->...
     *
     * @param head
     * @return
     */
    private static ComplexListNode clone2(ComplexListNode head) {
        if (head == null) {
            return null;
        }

        ComplexListNode cur = head;
        ComplexListNode curCopy = null;
        while (cur != null) {
            curCopy = new ComplexListNode(cur.value);
            curCopy.sibling = cur.sibling;
            curCopy.next = cur.next;
            cur.next = curCopy;
            cur = curCopy.next;
        }

        cur = head;
        ComplexListNode clone = null;
        ComplexListNode res = cur.next;
        while (cur != null) {   //将clone链表从原链表中剥离出来，并恢复原链表
            clone = cur.next;
            clone.next = cur.next.next;
            cur.next = clone.next;
            cur = cur.next;
        }
        return res;
    }
}