package algorithm_practice;

import common.datastruct.LinkedNode;
import common.util.ConstructLinkedNode;
import common.util.SysOut;
import org.junit.Test;

/**
 * Created by nibnait on 2020/11/24
 */
public class TempTest {

    @Test
    public void testCase() {
        LinkedNode head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 2, 3, 4, 5});
        SysOut.printLinkedNode(head);

        head = reverseK(head, 2);
        SysOut.printLinkedNode(head);
    }

    private LinkedNode reverseK(LinkedNode head, int k) {
        if (head == null) {
            return null;
        }

        LinkedNode dummyHead = new LinkedNode();
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

    private LinkedNode reverseN(LinkedNode head, int n) {
        if (n == 1) {
            return head;
        }

        LinkedNode last = reverseN(head.next, n-1);
        head.next.next = head;
        head.next = null;

        return last;
    }

}
