package algorithm_practice.SwordOffer.old;

import common.datastruct.LinkedNode;
import common.util.SysOut;

/**
 * 题目：定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
 *
 * 【思路】
 *  在单链表的表头临时接入一个结点，然后进行尾插法操作。反转单链表。
 *
 * Created by nibnait on 2016/9/22.
 */
public class c16_反转链表 {


    private static LinkedNode reverseList(LinkedNode head) {
        if (head == null) {
            return null;
        }
        LinkedNode root = new LinkedNode(); //逻辑头结点
        root.next = null;
        LinkedNode next;
        while (head != null){
            next = head.next;
            head.next = root.next;
            root.next = head;
            head = next;
        }
        return root.next;
    }

    /**
     * 书上的方法
     * @param head
     * @return
     */
    private static LinkedNode reverseList2(LinkedNode head) {
        if (head == null) {
            return null;
        }
        LinkedNode reverseHead = null;
        LinkedNode cur = head;    //当前结点
        LinkedNode pre = null;    //当前结点的前一个结点
            // pre = null, 因为反转后，head.next = null;
        LinkedNode next = null;
        while (cur != null) {
            next = cur.next;
            if (next == null) {
                reverseHead = cur;
            }
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return reverseHead;
    }

    public static void main(String[] args) {
        LinkedNode head = new LinkedNode(1);
        head.next = new LinkedNode(2);
        head.next.next = new LinkedNode(3);
        head.next.next.next = new LinkedNode(4);
        head.next.next.next.next = new LinkedNode(5);
        head.next.next.next.next.next = new LinkedNode(6);

        SysOut.printLinkedNode(head);
        head = reverseList(head);
        SysOut.printLinkedNode(head);
        head = reverseList2(head);
        SysOut.printLinkedNode(head);

        //当只有一个结点时
        LinkedNode head2 = new LinkedNode(1);
        SysOut.printLinkedNode(head2);
        head = reverseList(head2);
        SysOut.printLinkedNode(head2);
        head = reverseList2(head2);
        SysOut.printLinkedNode(head2);

    }
}
