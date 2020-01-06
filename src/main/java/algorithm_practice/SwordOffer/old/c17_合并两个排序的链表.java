package algorithm_practice.SwordOffer.old;

import common.datastruct.ListNode;
import common.util.SysOut;

/**
 * 题目：输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的
 *
 * 【解】：
 *      仅用4个辅助变量，实现时间复杂度:O(M+N)，空间复杂度:O(1)
 *
 * Created by nibnait on 2016/9/22.
 */
public class c17_合并两个排序的链表 {

    /**
     * 空间复杂度：O(1)
     *      使用4个辅助变量， head2往head1上合并
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode mergeSortedLists(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }
        ListNode head = new ListNode();
        ListNode head1tmp = new ListNode(); //永远指向head1.next
        ListNode head1ttmp = new ListNode(); //永远指向head1.next.next
        ListNode head2tmp = new ListNode(); //永远指向head2.next
        //不管怎样，都让head2往head1上合并！
        if (head2.val < head1.val) {
            head = head1;
            head1 = head2;
            head2 = head;
        }
        //这样 head1.value就永远都<= head2.value了
        head = head1;   //即以head1为主链，开始 head2往head1上合并
        while (head1ttmp != null && head2 != null) {
            head1tmp = head1.next;
            head1ttmp = head1.next.next;
            head2tmp = head2.next;
            if (head2.val <= head1tmp.val) {
                //head2插在head1tmp之前
                head1.next = head2;
                head2.next = head1tmp;
                head1 = head1.next; //head1向后移动一位
                head2 = head2tmp;   //head2向后移动一位
            } else if (head2.val <= head1ttmp.val) {
                //插在head1ttmp之前
                head1tmp.next = head2;
                head2.next = head1ttmp;
                head1 = head1.next; //head1向后移动一位
                head2 = head2tmp;   //head2向后移动一位
            } else {
                //head2插在head1ttmp之后
                head1 = head1tmp;
                head1tmp = head1.next;
            }
        }


        if(head2!=null){ //head1为空了，则head2直接接到head1tmp后面
            head1tmp.next = head2;
        }
        //如果是head2为空了，那正好！啥也不用干了
        return head;
    }

    /**
     * 空间复杂度：O(M+N)
     *      返回一条(M+N)长度的新链
     * @param head1
     * @param head2
     * @return
     */
    private static ListNode mergeSortedLists2(ListNode head1, ListNode head2){
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }
        ListNode head = new ListNode();
        ListNode tail = head;   //永远指向root链的末尾
        while (head1!=null && head2!=null){
            if (head1.val <= head2.val){
                tail.next = head1;
                head1 = head1.next;
            }else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        if (head1 != null){ //将head1直接放到tail.next
            tail.next = head1;
        }
        if (head2 != null){
            tail.next = head2;
        }

        return head.next;
    }


    /**
     * 书上的解法：递归，
     * @param head1
     * @param head2
     * @return
     */
    private static ListNode mergeSortedLists3(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }

        ListNode tmp = new ListNode();
        if (head1.val <= head2.val){
            tmp = head1;    //head1.next = head2;
            tmp.next = mergeSortedLists3(head1.next, head2);
        }else {
            tmp = head2;    //head2.next = head1;
            tmp.next = mergeSortedLists3(head1, head2.next);
        }
        return tmp;
    }



    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        SysOut.printList(head);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(5);
        head2.next.next.next = new ListNode(6);
        head2.next.next.next.next = new ListNode(7);
        SysOut.printList(head2);
        head = mergeSortedLists2(head, head2);
        SysOut.printList(head);
        System.out.println();

        //1. head2插在head1和head1tmp中间
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(6);
        head3.next.next = new ListNode(7);
        SysOut.printList(head3);

        ListNode head4 = new ListNode(2);
        head4.next = new ListNode(3);
        head4.next.next = new ListNode(4);
        SysOut.printList(head4);

        head3 = mergeSortedLists(head3, head4);
        SysOut.printList(head3);
    }

}
