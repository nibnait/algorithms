package cc.tianbin.algorithm_practice.LeetCode.code100;

import cc.tianbin.common.datastruct.ListNode;
import cc.tianbin.common.util.CompareUtils;
import cc.tianbin.common.util.ConstructLinkedNode;
import cc.tianbin.common.util.SysOut;
import org.junit.Assert;
import org.junit.Test;

/**
 对链表进行插入排序。


 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。

  

 插入排序算法：

 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 重复直到所有输入数据插入完为止。
  

 示例 1：

 输入: 4->2->1->3
 输出: 1->2->3->4
 示例 2：

 输入: -1->5->3->4->0
 输出: -1->0->3->4->5


 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/insertion-sort-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/20
 */
public class M147_对链表进行插入排序 {

    @Test
    public void testCase() {
        ListNode head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 3, 5, 2, 4});
        ListNode excepted = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 2, 3, 4, 5});
        ListNode actual = insertionSortList(head);
        SysOut.printLinkedNode(actual);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));

        head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{-1,5,3,4,0});
        excepted = ConstructLinkedNode.constructSingleLinkedNode(new int[]{-1,0,3,4,5});
        actual = insertionSortList(head);
        SysOut.printLinkedNode(actual);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));
    }

    /**
     * 双指针
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        // 已排好序的最后一个节点
        ListNode p1 = head;

        /**
         * p1的下一个节点
         * 1. p1.val <= p2.val: 正常，已排好序，依次往后移动
         * 2. p1.val > p2.val: p2应该从头开始找 第一个比p2大的值，把p2插在它前面
         */
        ListNode p2 = p1.next;

        while (p2 != null) {

            if (p1.val <= p2.val) {
                p1 = p1.next;
            } else {
                // 从头开始找 第一个比p2大的值，把p2插在它前面
                ListNode prev = dummyHead;
                while (prev.next.val < p2.val) {
                    prev = prev.next;
                }

                p1.next = p2.next;
                p2.next = prev.next;
                prev.next = p2;
            }

            p2 = p1.next;
        }

        return dummyHead.next;
    }

}
