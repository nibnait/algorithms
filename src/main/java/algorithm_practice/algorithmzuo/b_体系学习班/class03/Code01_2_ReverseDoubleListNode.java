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
public class Code01_2_ReverseDoubleListNode {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        LinkedNode head = ConstructLinkedNode.constructDoubleLinkedNode(SysRandom.randomArr());
        SysOut.printDoubleLinkedNode("origin: ", head);
        LinkedNode copyHead = ConstructLinkedNode.copyDoubleLinkedNode(head);

        LinkedNode actual = reverseList(head);
        SysOut.printDoubleLinkedNode("reverse: ", actual);
        LinkedNode excepted = expectReverseListNode(copyHead);
        SysOut.printDoubleLinkedNode("excepted: ", excepted);
        Assert.assertTrue(CompareUtils.compareDoubleListNode(excepted, actual));
    }

    /**
     * 反转双向链表
     */
    private LinkedNode reverseList(LinkedNode head) {
        LinkedNode pre = null;
        LinkedNode next = null;
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
    private LinkedNode expectReverseListNode(LinkedNode head) {
        List<LinkedNode> list = new ArrayList<>();
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
