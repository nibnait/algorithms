package common.util;

import common.datastruct.ListNode;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * 构造链表
 * Created by nibnait on 2019-08-08
 */
public class ConstructListNode {

    public static ListNode construct(int[] nums) {
        ListNode head = new ListNode();
        ListNode curNode = head;
        for (int i = 0; i < nums.length; i++) {
            curNode.val = nums[i];
            if (i != nums.length - 1) {
                curNode.next = new ListNode();
                curNode = curNode.next;
            }
        }
        return head;
    }
}
