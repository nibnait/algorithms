package algorithm_practice.LeetCode.code000;

import common.datastruct.ListNode;
import common.util.ConstructListNode;
import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例:
给定 1->2->3->4, 你应该返回 2->1->4->3.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-08
 */
public class M024_两两交换链表中的节点 extends TestCase {

    @Test
    public void testCase() {
        int[] list = {1, 2, 3, 4};
        soutTest(list);
    }

    private void soutTest(int[] list) {
        ListNode before = ConstructListNode.construct(list);
        SysOut.printList(before);
        ListNode after_recursion = swapPairs_recursion(before);
        SysOut.printList(after_recursion);
        ListNode after = swapPairs(ConstructListNode.construct(list));
        SysOut.printList(after);

        System.out.println();
    }

    /**
     * 非递归
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode nextNext = next.next;
        next.next = head;
        head.next = nextNext;
        ListNode pre = head;
        while (nextNext != null && nextNext.next != null) {
            ListNode next1 = nextNext.next;
            ListNode nextNext1 = next1.next;
            next1.next = nextNext;
            nextNext.next = nextNext1;

            pre.next = next1;
            pre = nextNext;

            nextNext = nextNext1;
        }
        return next;
    }

    public ListNode swapPairs_recursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs_recursion(next.next);
        next.next = head;
        return next;
    }

}
