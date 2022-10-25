package algorithm_practice.LeetCode.code000;

import common.datastruct.ListNode;
import common.util.CompareUtils;
import common.util.ConstructLinkedNode;
import common.util.SysOut;
import org.junit.Assert;
import org.junit.Test;

/**
 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

 说明:
 1 ≤ m ≤ n ≤ 链表长度。

 示例:

 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 输出: 1->4->3->2->5->NULL


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/20
 */
public class M092_反转链表2 {

    @Test
    public void testCase() {
        ListNode head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 2, 3, 4, 5});
        int m = 2, n = 4;
        ListNode excepted = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 4, 3, 2, 5});
        ListNode actual = reverseBetween(head, m, n);
        SysOut.printLinkedNode(actual);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));

        head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{3, 5});
        m = 1; n = 2;
        excepted = ConstructLinkedNode.constructSingleLinkedNode(new int[]{5, 3});
        actual = reverseBetween(head, m, n);
        SysOut.printLinkedNode(actual);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));

    }

    /**
     * 递归
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }

        head.next = reverseBetween(head.next, m-1, n-1);

        return head;
    }

    ListNode lastNext = null;
    /**
     * reverse 前n个节点
     */
    private ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            lastNext = head.next;
            return head;
        }

        ListNode last = reverseN(head.next, n-1);

        head.next.next = head;
        head.next = lastNext;

        return last;
    }

    /**
     * 非递归
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {

        if (head == null) {
            return null;
        }

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        int index = 1;
        while (index < m) {
            dummyHead = dummyHead.next;
            index++;
        }

        if (dummyHead.next == null || m == n) {
            return head;
        }

        // indexNode的前驱节点
        ListNode prev = dummyHead.next;
        ListNode indexNode = prev.next;
        while (indexNode != null && index+1 <= n) {
            // 把 indexNode 插在dummyHead 的后面。
            ListNode cur = new ListNode(indexNode.val);

            prev.next = indexNode.next;

            cur.next = dummyHead.next;
            dummyHead.next = cur;

            indexNode = prev.next;
            index++;
        }

        return m == 1 ? dummyHead.next : head;
    }

}
