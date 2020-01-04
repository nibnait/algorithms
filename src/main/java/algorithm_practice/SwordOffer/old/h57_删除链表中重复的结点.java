package algorithm_practice.SwordOffer.old;

import common.datastruct.ListNode;;
import common.util.SysOut;

/**
 * 题目：在一个排序的链表中，如何删除重复的结点？
 * 例如，1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5 -> null 中重复结点被删除之后，链表变成了：
 *      1 -> 2 -> 5 -> null
 *
 * 【注意陷阱】：
 *      头结点也可能被删除，所以在链表头添加一个临时头结点 root。
 *      然后，最后返回 root.next即可
 *
 * Created by nibnait on 2016/10/3.
 */
public class h57_删除链表中重复的结点 {

    private static ListNode deleteDuplication(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode root = new ListNode();
        root.next = head;       // 临时的头结点

        ListNode prev = root;   // 当前结点的前驱结点
        ListNode node = head;   // 当前处理的结点
        while (node != null && node.next != null) {
            if (node.val == node.next.val) {
                while (node.next != null && node.next.val == node.val) {
                    node = node.next;
                }
                prev.next = node.next;
            } else {
                prev.next = node;
                prev = prev.next;
            }
            node = node.next;
        }
        return root.next;
    }

    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();
        test05();
        test06();
        test07();
        test08();
        test09();
        test10();
    }
    // 1->2->3->3->4->4->5
    private static void test01() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(4);
        ListNode n7 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        ListNode result = deleteDuplication(n1);
        SysOut.printList(result);
    }
    // 1->2->3->4->5->6->7
    private static void test02() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        ListNode result = deleteDuplication(n1);
        SysOut.printList(result);
    }
    // 1->1->1->1->1->1->2
    private static void test03() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(1);
        ListNode n5 = new ListNode(1);
        ListNode n6 = new ListNode(1);
        ListNode n7 = new ListNode(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        ListNode result = deleteDuplication(n1);
        SysOut.printList(result);
    }
    // 1->1->1->1->1->1->1
    private static void test04() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(1);
        ListNode n5 = new ListNode(1);
        ListNode n6 = new ListNode(1);
        ListNode n7 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        ListNode result = deleteDuplication(n1);
        SysOut.printList(result);
    }
    // 1->1->2->2->3->3->4->4
    private static void test05() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(3);
        ListNode n6 = new ListNode(3);
        ListNode n7 = new ListNode(4);
        ListNode n8 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        ListNode result = deleteDuplication(n1);
        SysOut.printList(result);
    }
    // 1->1->2->3->3->4->5->5
    private static void test06() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(3);
        ListNode n6 = new ListNode(4);
        ListNode n7 = new ListNode(5);
        ListNode n8 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        ListNode result = deleteDuplication(n1);
        SysOut.printList(result);
    }
    // 1->1->2->2->3->3->4->5->5
    private static void test07() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(3);
        ListNode n6 = new ListNode(3);
        ListNode n7 = new ListNode(4);
        ListNode n8 = new ListNode(5);
        ListNode n9 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        ListNode result = deleteDuplication(n1);
        SysOut.printList(result);
    }
    // 1->2
    private static void test08() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        ListNode result = deleteDuplication(n1);
        SysOut.printList(result);
    }
    // 1
    private static void test09() {
        ListNode n1 = new ListNode(1);
        ListNode result = deleteDuplication(n1);
        SysOut.printList(result);
    }
    // null
    private static void test10() {
        ListNode result = deleteDuplication(null);
        SysOut.printList(result);
    }
}
