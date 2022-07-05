package algorithm_practice.SwordOffer.old;

import common.datastruct.LinkedNode;;
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

    private static LinkedNode deleteDuplication(LinkedNode head) {
        if (head == null) {
            return null;
        }

        LinkedNode root = new LinkedNode();
        root.next = head;       // 临时的头结点

        LinkedNode prev = root;   // 当前结点的前驱结点
        LinkedNode node = head;   // 当前处理的结点
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
        LinkedNode n1 = new LinkedNode(1);
        LinkedNode n2 = new LinkedNode(2);
        LinkedNode n3 = new LinkedNode(3);
        LinkedNode n4 = new LinkedNode(3);
        LinkedNode n5 = new LinkedNode(4);
        LinkedNode n6 = new LinkedNode(4);
        LinkedNode n7 = new LinkedNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        LinkedNode result = deleteDuplication(n1);
        SysOut.printLinkedNode(result);
    }
    // 1->2->3->4->5->6->7
    private static void test02() {
        LinkedNode n1 = new LinkedNode(1);
        LinkedNode n2 = new LinkedNode(2);
        LinkedNode n3 = new LinkedNode(3);
        LinkedNode n4 = new LinkedNode(4);
        LinkedNode n5 = new LinkedNode(5);
        LinkedNode n6 = new LinkedNode(6);
        LinkedNode n7 = new LinkedNode(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        LinkedNode result = deleteDuplication(n1);
        SysOut.printLinkedNode(result);
    }
    // 1->1->1->1->1->1->2
    private static void test03() {
        LinkedNode n1 = new LinkedNode(1);
        LinkedNode n2 = new LinkedNode(1);
        LinkedNode n3 = new LinkedNode(1);
        LinkedNode n4 = new LinkedNode(1);
        LinkedNode n5 = new LinkedNode(1);
        LinkedNode n6 = new LinkedNode(1);
        LinkedNode n7 = new LinkedNode(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        LinkedNode result = deleteDuplication(n1);
        SysOut.printLinkedNode(result);
    }
    // 1->1->1->1->1->1->1
    private static void test04() {
        LinkedNode n1 = new LinkedNode(1);
        LinkedNode n2 = new LinkedNode(1);
        LinkedNode n3 = new LinkedNode(1);
        LinkedNode n4 = new LinkedNode(1);
        LinkedNode n5 = new LinkedNode(1);
        LinkedNode n6 = new LinkedNode(1);
        LinkedNode n7 = new LinkedNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        LinkedNode result = deleteDuplication(n1);
        SysOut.printLinkedNode(result);
    }
    // 1->1->2->2->3->3->4->4
    private static void test05() {
        LinkedNode n1 = new LinkedNode(1);
        LinkedNode n2 = new LinkedNode(1);
        LinkedNode n3 = new LinkedNode(2);
        LinkedNode n4 = new LinkedNode(2);
        LinkedNode n5 = new LinkedNode(3);
        LinkedNode n6 = new LinkedNode(3);
        LinkedNode n7 = new LinkedNode(4);
        LinkedNode n8 = new LinkedNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        LinkedNode result = deleteDuplication(n1);
        SysOut.printLinkedNode(result);
    }
    // 1->1->2->3->3->4->5->5
    private static void test06() {
        LinkedNode n1 = new LinkedNode(1);
        LinkedNode n2 = new LinkedNode(1);
        LinkedNode n3 = new LinkedNode(2);
        LinkedNode n4 = new LinkedNode(3);
        LinkedNode n5 = new LinkedNode(3);
        LinkedNode n6 = new LinkedNode(4);
        LinkedNode n7 = new LinkedNode(5);
        LinkedNode n8 = new LinkedNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        LinkedNode result = deleteDuplication(n1);
        SysOut.printLinkedNode(result);
    }
    // 1->1->2->2->3->3->4->5->5
    private static void test07() {
        LinkedNode n1 = new LinkedNode(1);
        LinkedNode n2 = new LinkedNode(1);
        LinkedNode n3 = new LinkedNode(2);
        LinkedNode n4 = new LinkedNode(2);
        LinkedNode n5 = new LinkedNode(3);
        LinkedNode n6 = new LinkedNode(3);
        LinkedNode n7 = new LinkedNode(4);
        LinkedNode n8 = new LinkedNode(5);
        LinkedNode n9 = new LinkedNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        LinkedNode result = deleteDuplication(n1);
        SysOut.printLinkedNode(result);
    }
    // 1->2
    private static void test08() {
        LinkedNode n1 = new LinkedNode(1);
        LinkedNode n2 = new LinkedNode(2);
        n1.next = n2;
        LinkedNode result = deleteDuplication(n1);
        SysOut.printLinkedNode(result);
    }
    // 1
    private static void test09() {
        LinkedNode n1 = new LinkedNode(1);
        LinkedNode result = deleteDuplication(n1);
        SysOut.printLinkedNode(result);
    }
    // null
    private static void test10() {
        LinkedNode result = deleteDuplication(null);
        SysOut.printLinkedNode(result);
    }
}
