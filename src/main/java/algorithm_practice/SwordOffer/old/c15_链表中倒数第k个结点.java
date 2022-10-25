package algorithm_practice.SwordOffer.old;

import common.datastruct.ListNode;

/**
 * 题目：输入一个链表，输出该链表中倒数第k 个结点．
 * 为了符合大多数人的习惯，本题从 1 开始计数，即链表的尾结点是倒数第 1 个结点．
 * 例如一个链表有 6 个结点，从头结点开始它们的值依次是 1 、2、3、4、5 、6。这个个链表的倒数第 3 个结点是值为 4 的结点。
 *
 * 【思路】
 *   两个指针:
 *      p1指向头结点
 *      p2指向第k-1个结点
 *      然后双指针一起往后跑，知道p2到达末尾
 *      则p1指向的就是倒数第k个结点
 *
 * 【注意】：特殊值的处理
 *  - 当输入的head为null时，直接return null;
 *  - 当输入k = 0时，由于我们是从1开始计数的，所以查找倒数第0个结点无意义，直接return null;
 *  - 当链表的结点数小于k时，则p2指不到第k-1个结点（NullPointerException），所以还要在for循环中加个判断！增加程序的鲁棒性
 *
 *  【相关题目】
 * - 求链表的中间结点
 * - 判断单向链表是否形成了环形结构？
 *  解：
 *      快慢指针，快指针一次走两步，慢指针一次走一步。
 *      若快指针走到头了，慢指针指向的就是中间结点，
 *      若快指针追上了慢指针，则是环形链表。
 * Created by nibnait on 2016/9/22.
 */
public class c15_链表中倒数第k个结点 {


    public static ListNode findKthToTail(ListNode head, int k) {
        if (head==null || k<=0){
           return null;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 1; i < k; i++) {
            if (p2.next != null){
                p2 = p2.next;
            } else {
                throw new RuntimeException("Invalid args: k > 链表的结点数");
            }
        }

        while (p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        System.out.println(findKthToTail(head, 1).val);   //倒数第1个
        System.out.println(findKthToTail(head, 3).val);   //中间的一个
        System.out.println(findKthToTail(head, 6).val);   //倒数最后一个就是顺数第一个
//        System.out.println(findKthToTail(head, 10).val);
    }
}
