package algorithm_practice.SwordOffer.old;

import common.datastruct.ListNode;

import static common.util.SysOut.printList;

/**
 * 给定单向链表的头指针和一个结点指针，定义一个函数在0(1)时间删除该结点,
 *
 * 【抖机灵】
 *      正常删除链表结点都得给个头指针和要删除的结点，然后从头开始遍历寻找
 *      但是要求时间复杂度是1，下面抖个机灵：
 *      我们可以直接复制这个结点的下一个结点，然后再将这个结点的下一个结点删除。over
 * 【注意】
 * - 要删除的结点是尾结点
 *     - 没办法，NULL为系统中的特定的那块区域！！并无法复制，所以只能从头遍历，得到该结点的前序结点，删除。
 *     - 如果链表只有一个结点，即这个要删除的结点也是头结点，则，将nodeToBeDeleted置为空之后，还需把头结点置为空；
 * - 由于Java子函数，只能是值传递，（所以不像C++，）必须返回新链表头结点，不然子函数就白调用了。
 *
 * Created by nibnait on 2016/9/21.
 */
public class c13_删除链表结点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        ListNode middle = head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode last = head.next.next.next.next.next = new ListNode(6);

        head = deleteNodeInList(head, head); // 删除头结点
        printList(head);
        head = deleteNodeInList(head, last); // 删除尾结点
        printList(head);
        head = deleteNodeInList(head, middle); // 删除中间结点
        printList(head);
    }

    private static ListNode deleteNodeInList(ListNode head, ListNode nodeToBeDeleted) {
        if (head==null || nodeToBeDeleted==null){
            return head;
        }

        if (nodeToBeDeleted.next != null){  //只要删除的不是尾结点
            ListNode tmp = nodeToBeDeleted.next;
            nodeToBeDeleted.val = tmp.val;
            nodeToBeDeleted.next = tmp.next;
            tmp = null;
        } else if (head == nodeToBeDeleted){    //链表中就这么一个结点
            nodeToBeDeleted = null;
        } else {    //多个结点的情况下，删除尾结点
            ListNode tmp = head;
            while (tmp.next != nodeToBeDeleted){
                tmp = tmp.next;
            }
            nodeToBeDeleted = null;
            tmp.next = null;
        }
        return head;
    }


}
