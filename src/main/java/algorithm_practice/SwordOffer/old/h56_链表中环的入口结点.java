package algorithm_practice.SwordOffer.old;

import common.datastruct.ListNode;

/**
 * 题目：一个链表中包含环，如何找出环的入口结点？
 *
 * /src/Algorithm.nowcoder/b_2nd_Season/bf160824/README.md：题目五：两个单链表相交的一系列问题的第一个小环节
 * 【解】：双指针：
        F：一次走两步、S：一次走一步
     - 如果有环，则F与S一定会在环中相遇。
         相遇时，再来一个指针A：一次走一步，（S：也一次走一步）
         则A与S 一定会在入环的第一个结点相遇【一定】，可返回入环的第一个结点。
     - 如果无环，
         return null;

 * Created by nibnait on 2016/10/3.
 */
public class h56_链表中环的入口结点 {
    private static ListNode meetingNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        // 链表中没有环
        if (fast == null || fast.next == null) {
            return null;
        }
        // fast重新指向第一个结点
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }
    // 1->2->3->4->5->6
    private static void test01() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        System.out.println(meetingNode(n1));
    }
    // 1->2->3->4->5->6
    //       ^        |
    //       |        |
    //       +--------+
    private static void test02() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n3;
        System.out.println(meetingNode(n1));
    }
    // 1->2->3->4->5->6 <-+
    //                |   |
    //                +---+
    private static void test03() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n6;
        System.out.println(meetingNode(n1));
    }
}
