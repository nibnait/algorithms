package algorithm_practice.LeetCode.code200;

import cn.hutool.core.lang.Assert;
import common.datastruct.ListNode;
import common.util.ConstructLinkedNode;
import org.junit.Test;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 输入：head = [1,2]
 * 输出：false
 *
 * Created by nibnait on 2023/03/01
 */
public class E234_回文链表 {

    @Test
    public void test() {
        int[] nums = new int[]{1,2,2,1};
        boolean result = isPalindrome(ConstructLinkedNode.constructSingleLinkedNode(nums));
        Assert.equals(true, result);

        nums = new int[]{1};
        result = isPalindrome(ConstructLinkedNode.constructSingleLinkedNode(nums));
        Assert.equals(true, result);

        nums = new int[]{1, 2};
        result = isPalindrome(ConstructLinkedNode.constructSingleLinkedNode(nums));
        Assert.equals(false, result);

        nums = new int[]{1, 2, 1};
        result = isPalindrome(ConstructLinkedNode.constructSingleLinkedNode(nums));
        Assert.equals(true, result);

        nums = new int[]{1,1,0,0,1};
        result = isPalindrome(ConstructLinkedNode.constructSingleLinkedNode(nums));
        Assert.equals(false, result);

    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode p1 = head;
        ListNode p2 = head;
        int count = 1;
        while (p2.next != null && p2.next.next != null) {
            ListNode cur = p1.next;
            p2 = p2.next.next;
            count++;
        }

        ListNode reverseStart = p1;
        if (count % 2 == 0) {
            reverseStart = p1.next;
        }

        // 翻转链表
        ListNode dummyHead = new ListNode();
        dummyHead.next = reverseStart;

        ListNode cur = reverseStart.next;
        reverseStart.next = null;
        while (cur != null) {
            ListNode next = cur.next;

            cur.next = dummyHead.next;
            dummyHead.next = cur;

            cur = next;
        }

        // 一一对比
        dummyHead = dummyHead.next;
        while (head != null && dummyHead != null) {
            if (head.val != dummyHead.val) {
                return false;
            }
            head = head.next;
            dummyHead = dummyHead.next;
        }
        return true;
    }

}
