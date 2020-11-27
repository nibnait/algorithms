package algorithm_practice;

import common.datastruct.ListNode;
import common.util.ConstructListNode;
import common.util.SysOut;
import org.junit.Test;

/**
 * Created by nibnait on 2020/11/24
 */
public class TempTest {

    @Test
    public void testCase() {
        ListNode head = ConstructListNode.construct(new int[]{1, 2, 3, 4, 5});
        SysOut.printList(head);

        head = reverseK(head, 2);
        SysOut.printList(head);
    }

    private ListNode reverseK(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        while (dummyHead.next != null) {

            dummyHead.next = reverseN(dummyHead.next, k);

            for (int i = 0; i < k; i++) {
                if (dummyHead.next == null) {
                    break;
                }
                dummyHead = dummyHead.next;
            }

        }

        return dummyHead.next;
    }

    private ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            return head;
        }

        ListNode last = reverseN(head.next, n-1);
        head.next.next = head;
        head.next = null;

        return last;
    }

}
