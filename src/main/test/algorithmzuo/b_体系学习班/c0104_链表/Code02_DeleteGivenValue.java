package algorithmzuo.b_体系学习班.c0104_链表;

import common.CommonConstants;
import common.datastruct.ListNode;
import common.util.ConstructLinkedNode;
import common.util.SysRandom;
import org.junit.Test;

/**
 * Created by nibnait on 2022/07/06
 */
public class Code02_DeleteGivenValue {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        ListNode head = ConstructLinkedNode.constructSingleLinkedNode(SysRandom.randomArr(20));
        ListNode copyHead = ConstructLinkedNode.copySingleLinkedNode(head);
//        SysOut.printLinkedNode("before: ", head);

        ListNode ans = deleteGivenValue(head, 11);
//        SysOut.printLinkedNode("after: ", ans);
    }

    @Test
    public void test() {
        ListNode head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{11,11,12,3,4,5});
//        head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{3,4,5,11});
//        SysOut.printLinkedNode("before: ", head);

        ListNode ans = deleteGivenValue(head, 11);
//        SysOut.printLinkedNode("after: ", ans);
    }

    /**
     * 在链表中删除指定值的所有节点
     */
    private ListNode deleteGivenValue(ListNode head, int num) {
        if (head == null) {
            return head;
        }

        while (head.val == num) {
            head = head.next;
        }

        ListNode cursor = head;
        while (cursor != null && cursor.next != null) {
            if (cursor.next.val == num) {
                cursor.next = cursor.next.next;
            }
            cursor = cursor.next;
        }

        return head;
    }

}
