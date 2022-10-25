package algorithmzuo.b_体系学习班.c0201_链表;

import common.CommonConstants;
import common.datastruct.ListNode;
import common.util.CompareUtils;
import common.util.ConstructLinkedNode;
import common.util.SysRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nibnait on 2022/06/24
 */
public class Code01_2_ReverseDoubleListNode {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        ListNode head = ConstructLinkedNode.constructDoubleLinkedNode(SysRandom.randomArr());
//        SysOut.printDoubleLinkedNode("origin: ", head);
        ListNode copyHead = ConstructLinkedNode.copyDoubleLinkedNode(head);

        ListNode actual = reverseList(head);
//        SysOut.printDoubleLinkedNode("reverse: ", actual);
        ListNode excepted = expectReverseListNode(copyHead);
//        SysOut.printDoubleLinkedNode("excepted: ", excepted);
        Assert.assertTrue(CompareUtils.compareDoubleListNode(excepted, actual));
    }

    /**
     * 反转双向链表
     */
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.prev = next;

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
            if (i != list.size() - 1) {
                list.get(i).prev = list.get(i + 1);
            }
        }
        list.get(0).next = null;
        return list.get(list.size() - 1);
    }

}
