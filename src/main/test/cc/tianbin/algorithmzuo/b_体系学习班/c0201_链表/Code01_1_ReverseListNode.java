package cc.tianbin.algorithmzuo.b_体系学习班.c0201_链表;

import cc.tianbin.common.CommonConstants;
import cc.tianbin.common.datastruct.ListNode;
import cc.tianbin.common.util.CompareUtils;
import cc.tianbin.common.util.ConstructLinkedNode;
import cc.tianbin.common.util.SysRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nibnait on 2022/06/24
 */
public class Code01_1_ReverseListNode {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        ListNode head = ConstructLinkedNode.constructSingleLinkedNode(SysRandom.generateArr());
        ListNode copyHead = ConstructLinkedNode.copySingleLinkedNode(head);
//        SysOut.printLinkedNode("origin: ", head);

        ListNode actual = reverseList(head);
//        SysOut.printLinkedNode("reverse: ", actual);
        ListNode excepted = expectReverseListNode(copyHead);
//        SysOut.printLinkedNode("excepted: ", excepted);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));
    }

    /**
     * 反转链表
     */
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;

            pre = head;
            head = next;
        }
        return pre;
    }


    /**
     * 用数组对数器，验证反转链表
     */
    private ListNode expectReverseListNode(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        for (int i = list.size() - 1; i >= 1; i--) {
            list.get(i).next = list.get(i - 1);
        }
        list.get(0).next = null;
        return list.get(list.size() - 1);
    }
}
