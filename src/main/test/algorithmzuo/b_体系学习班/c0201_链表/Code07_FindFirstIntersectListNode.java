package algorithmzuo.b_体系学习班.c0201_链表;

import common.CommonConstants;
import common.datastruct.ListNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by nibnait on 2022/10/25
 */
@Slf4j
public class Code07_FindFirstIntersectListNode {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {




    }

    /**
     * 给定两个可能有环也可能无环的单链表，头节点head1和head2
     * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交返回null
     * 要求如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)
     */
    private ListNode getIntersectListNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        return null;
    }

}
