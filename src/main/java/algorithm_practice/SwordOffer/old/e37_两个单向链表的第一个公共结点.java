package algorithm_practice.SwordOffer.old;

import common.datastruct.LinkedNode;;

/**
 * 题目：输入两个链表，找出它们的第一个公共结点。
 *
 *  ../../b_2nd_Season/bf160824/README.md：题目五
 *
     1. 两个链表：一个有环，一个无环
     则两个链表不可能相交
     return null;

     2. 两个链表：都无环
     - 不相交 （"||"型）
     - 相交   （"Y " 型）（不可能是"X "型的，因为一个结点只能有一个next元素）

         法一：
         HashMap 保存第一个链表结点之间的关系
         然后比较第二个链表，

         法二：
         先统计两条链表的长度
         然后让长链表先把多出来的步数走掉
         然后两条链表一起走，相遇则“Y”，否则为“||”

     3. 两个链表：都有环
     - 先相交，再共同入环（两个链表的第一个入环结点相等）
     - 共享环，（在环外没遇上）：让环1的入环结点，往下走，如果遇到了环2的入环结点，√
     - 不相交： 两环的入环的第一个结点没遇上
 *
 * （书中没有考虑链表有环的情况）
 * Created by nibnait on 2016/10/1.
 */
public class e37_两个单向链表的第一个公共结点 {
    
    public static LinkedNode getIntersectListNode(LinkedNode head1, LinkedNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        LinkedNode loop1 = getLoopListNode(head1);
        LinkedNode loop2 = getLoopListNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    /**
     * 求链表的第一个入环结点
     *
     * @param head
     * @return
     */
    public static LinkedNode getLoopListNode(LinkedNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        LinkedNode n1 = head.next; // n1 -> slow
        LinkedNode n2 = head.next.next; // n2 -> fast	一次走两步
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head; // n2 -> walk again from head	n2 复用，这次一次走一步
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    /**
     * 两个链表都无环时，求两个链表的相交结点
     *
     * @param head1
     * @param head2
     * @return
     */
    public static LinkedNode noLoop(LinkedNode head1, LinkedNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        LinkedNode cur1 = head1;
        LinkedNode cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;            //cur1永远指向长的那个链表的head
        cur2 = cur1 == head1 ? head2 : head1;    //cur2指向短的那个链表head
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;        //长链表先把多出来的步数走掉
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 都有环时，
     *
     * @param head1
     * @param loop1 链表1的 第一个入环结点
     * @param head2
     * @param loop2 链表2的 第一个入环结点
     * @return
     */
    public static LinkedNode bothLoop(LinkedNode head1, LinkedNode loop1, LinkedNode head2, LinkedNode loop2) {
        LinkedNode cur1 = null;
        LinkedNode cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        LinkedNode head1 = new LinkedNode(1);
        head1.next = new LinkedNode(2);
        head1.next.next = new LinkedNode(3);
        head1.next.next.next = new LinkedNode(4);
        head1.next.next.next.next = new LinkedNode(5);
        head1.next.next.next.next.next = new LinkedNode(6);
        head1.next.next.next.next.next.next = new LinkedNode(7);

        // 0->9->8->6->7->null
        LinkedNode head2 = new LinkedNode(0);
        head2.next = new LinkedNode(9);
        head2.next.next = new LinkedNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectListNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new LinkedNode(1);
        head1.next = new LinkedNode(2);
        head1.next.next = new LinkedNode(3);
        head1.next.next.next = new LinkedNode(4);
        head1.next.next.next.next = new LinkedNode(5);
        head1.next.next.next.next.next = new LinkedNode(6);
        head1.next.next.next.next.next.next = new LinkedNode(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new LinkedNode(0);
        head2.next = new LinkedNode(9);
        head2.next.next = new LinkedNode(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectListNode(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new LinkedNode(0);
        head2.next = new LinkedNode(9);
        head2.next.next = new LinkedNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectListNode(head1, head2).val);

    }


}
