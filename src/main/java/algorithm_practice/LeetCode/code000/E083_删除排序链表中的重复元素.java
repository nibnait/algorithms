package algorithm_practice.LeetCode.code000;

import common.datastruct.ListNode;
import common.util.ConstructListNode;
import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:
输入: 1->1->2
输出: 1->2

示例 2:
输入: 1->1->2->3->3
输出: 1->2->3


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-08
 */
public class E083_删除排序链表中的重复元素 extends TestCase {

    @Test
    public void testCase() {
        soutTest(new int[]{1, 1, 2});
        soutTest(new int[]{1, 1, 2, 3, 3});
        soutTest(new int[]{1, 1, 1});
        soutTest(new int[]{});
        soutTest(new int[]{1, 2, 2, 2});
        soutTest(new int[]{1, 1, 1, 2});
        soutTest(new int[]{1, 2, 2, 2, 3});

    }

    private void soutTest(int[] list) {
        ListNode before = ConstructListNode.construct(list);
        SysOut.printList(before);
        ListNode after = deleteDuplicates(before);
        SysOut.printList(after);
        System.out.println();
    }

    /**
     * 好多陷阱
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode currentNode = head;
        while (currentNode != null && currentNode.next != null) {
            if (currentNode.val == currentNode.next.val) {
                currentNode.next = currentNode.next.next;
                continue;
            }
            if (currentNode != null && currentNode.next != null && currentNode.val == currentNode.next.val && currentNode.next.next == null) {
                currentNode.next = null;
            }
            currentNode = currentNode.next;
        }
        if (head != null && head.next != null && head.val == head.next.val) {
            head = head.next;
        }
        return head;
    }
}
