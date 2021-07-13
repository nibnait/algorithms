package algorithm_practice.LeetCode.code100;

import common.datastruct.ListNode;
import common.util.compare.CompareUtil;
import common.util.ConstructListNode;
import common.util.SysOut;
import org.junit.Assert;
import org.junit.Test;

/**
 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

 进阶：
 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
  

 示例 1：
 输入：head = [4,2,1,3]
 输出：[1,2,3,4]

 示例 2：
 输入：head = [-1,5,3,4,0]
 输出：[-1,0,3,4,5]

 示例 3：
 输入：head = []
 输出：[]


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/sort-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/21
 */
public class M148_排序链表 {

    @Test
    public void testCase() {
        ListNode head = ConstructListNode.construct(new int[]{4,2,1,3});
        ListNode excepted = ConstructListNode.construct(new int[]{1, 2, 3, 4});
        ListNode actual = sortList(head);
        SysOut.printList(actual);
        Assert.assertTrue(CompareUtil.compareListNode(excepted, actual));

        head = ConstructListNode.construct(new int[]{-1,5,3,4,0});
        excepted = ConstructListNode.construct(new int[]{-1,0,3,4,5});
        actual = sortList(head);
        SysOut.printList(actual);
        Assert.assertTrue(CompareUtil.compareListNode(excepted, actual));

    }

    /**
     * 双指针
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        // 指向当前 val最小的节点
        ListNode p1 = head;
        // 指向当前 val最大的节点
        ListNode p2 = head.next;
        if (p1.val > p2.val) {
            // 把 p2 挪到 p1 前面
            p1.next = p2.next;
            p2.next = dummyHead.next;
            dummyHead.next = p2;

            p1 = dummyHead.next;
            p2 = dummyHead.next.next;
        }

        ListNode cur = p2.next;
        while (cur != null) {
            if (cur.val <= p1.val) {
                // 把 cur 挪到 p1 前面
                p2.next = cur.next;

                cur.next = dummyHead.next;
                dummyHead.next = cur;

                p1 = cur;

                cur = p2.next;
            } else if (cur.val >= p2.val) {
                p2 = cur;
                cur = cur.next;
            } else {
                // p1.val < cur.val < p2.val
                // 找个地方插进去
                while (p1.next.val < cur.val) {
                    p1 = p1.next;
                }

                // 把 cur 插到 p1 的后面
                p2.next = cur.next;

                cur.next = p1.next;
                p1.next = cur;

                cur = p2.next;
                p1 = dummyHead.next;
            }
        }

        return dummyHead.next;
    }

}
