package algorithm_practice.LeetCode.code200;

import common.datastruct.LinkedNode;
import common.util.CompareUtils;
import common.util.ConstructLinkedNode;
import common.util.SysOut;
import org.junit.Assert;
import org.junit.Test;

/*
 反转一个单链表。

 示例:

 输入: 1->2->3->4->5->NULL
 输出: 5->4->3->2->1->NULL
 进阶:
 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？



 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/reverse-linked-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/20
 */
public class E206_反转链表 {

    @Test
    public void testCase() {
        LinkedNode head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 2, 3, 4, 5});
        LinkedNode excepted = ConstructLinkedNode.constructSingleLinkedNode(new int[]{5, 4, 3, 2, 1});
        LinkedNode actual = reverseList(head);
        SysOut.printLinkedNode(actual);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));

    }

    /**
     * labuladong 递归
     */
    public LinkedNode reverseList(LinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return last;
    }

    /**
     * 递归
     */
    public LinkedNode reverseList3(LinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedNode prev = reverseList(head.next);
        LinkedNode p = prev;
        while (p.next != null) {
            p = p.next;
        }
        p.next = head;
        head.next = null;

        return prev;
    }

    /**
     * 非递归
     */
    public LinkedNode reverseList2(LinkedNode head) {
        if (head == null) {
            return null;
        }

        LinkedNode dummyHead = new LinkedNode();
        dummyHead.next = head;

        LinkedNode cur = head.next;
        head.next = null;

        while (cur != null) {
            LinkedNode next = cur.next;

            // 把 cur 插在 dummyHead.next
            cur.next = dummyHead.next;
            dummyHead.next = cur;

            cur = next;
        }

        return dummyHead.next;
    }

}
