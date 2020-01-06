package algorithm_practice.LeetCode.code000;

import common.datastruct.ListNode;
import common.util.ConstructListNode;
import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

示例 1:
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL

示例 2:
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-08
 */
public class M061_旋转链表 extends TestCase {

    @Test
    public void testCase() {
        int[] list = {1, 2, 3, 4, 5};
        int k = 2;
        soutTest(list, k);

        int[] list2 = {0, 1, 2};
        int k2 = 4;
        soutTest(list2, k2);

        int[] list3 = {1, 2};
        int k3 = 2;
        soutTest(list3, k3);

    }

    private void soutTest(int[] list, int k) {
        ListNode before = ConstructListNode.construct(list);
        SysOut.printList(before);
        ListNode after = rotateRight(before, k);
        SysOut.printList(after);

        System.out.println();
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        if (k == 0) {
            return head;
        }
        ListNode newHeadPreNode = findKthToTail(head, k);
        ListNode newHead = newHeadPreNode.next;
        if (newHead == null) {
            return head;
        }
        newHeadPreNode.next = null;
        ListNode lastNode = newHead;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = head;
        return newHead;
    }

    /**
     * 倒数第k个结点（从0开始数）
     *
     * @param head
     * @param k
     * @return
     */
    private ListNode findKthToTail(ListNode head, int k) {
        ListNode p1 = head;
        ListNode p2 = head;
        int newK = 0;
        for (int i = 0; i < k; i++) {
            if (p2.next != null) {
                p2 = p2.next;
            } else {
                //k > 链表中结点的数量。
                newK = k % (i + 1);
                p2 = head;
                break;
            }
        }
        if (newK != 0) {
            for (int i = 0; i < newK; i++) {
                if (p2.next != null) {
                    p2 = p2.next;
                }
            }
        }
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
