package algorithm_practice.algorithmzuo.b_体系学习班.class03;

import common.CommonConstants;
import common.datastruct.LinkedNode;
import common.util.CompareUtils;
import common.util.ConstructLinkedNode;
import common.util.SysOut;
import common.util.SysRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nibnait on 2022/06/24
 */
public class Code01_1_ReverseListNode {

    @Test
    public void loolTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        LinkedNode head = ConstructLinkedNode.constructSingleLinkedNode(SysRandom.randomArr());
        LinkedNode copyHead = ConstructLinkedNode.copySingleLinkedNode(head);
        SysOut.printLinkedNode("origin: ", head);

        LinkedNode actual = reverseList(head);
        SysOut.printLinkedNode("reverse: ", actual);
        LinkedNode excepted = expectReverseListNode(copyHead);
        SysOut.printLinkedNode("excepted: ", excepted);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));
    }

    /**
     * 反转链表
     */
    private LinkedNode reverseList(LinkedNode head) {
        LinkedNode pre = null;
        LinkedNode next = null;
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
    private LinkedNode expectReverseListNode(LinkedNode head) {
        List<LinkedNode> list = new ArrayList<>();
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
