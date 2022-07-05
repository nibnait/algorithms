package algorithm_practice.LeetCode.code300;

import common.datastruct.LinkedNode;
import common.util.CompareUtils;
import common.util.ConstructLinkedNode;
import common.util.SysOut;
import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 * <p>
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by nibnait on 2020/11/19
 */
public class M328_奇偶链表 {

    @Test
    public void testCase() {
        LinkedNode head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 2, 3, 4, 5});
        LinkedNode excepted = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 3, 5, 2, 4});
        LinkedNode actual = oddEvenList(head);
//        SysOut.printList(actual);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));

        head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{2, 1, 3, 5, 6, 4, 7});
        excepted = ConstructLinkedNode.constructSingleLinkedNode(new int[]{2, 3, 6, 7, 1, 5, 4});
        actual = oddEvenList(head);
//        SysOut.printList(actual);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));

        head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{2, 1, 3, 5, 6, 4, 7});
        excepted = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 3, 5, 7, 2, 6, 4});
        actual = oddEvenValList(head);
        SysOut.printLinkedNode(actual);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));

        head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 2, 3, 4, 5});
        excepted = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 3, 5, 2, 4});
        actual = oddEvenValList(head);
        SysOut.printLinkedNode(actual);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));

        head = ConstructLinkedNode.constructSingleLinkedNode(new int[]{2, 1});
        excepted = ConstructLinkedNode.constructSingleLinkedNode(new int[]{1, 2});
        actual = oddEvenValList(head);
        SysOut.printLinkedNode(actual);
        Assert.assertTrue(CompareUtils.compareSingleListNode(excepted, actual));

    }

    /**
     * 换一道题：把奇数值的节点放到前面，把偶数值的节点放到后面
     * 时间复杂度O(nodes)，空间复杂度O(1)
     *
     * 双指针  屡试不爽
     */
    public LinkedNode oddEvenValList(LinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        LinkedNode dummyHead = new LinkedNode();
        dummyHead.next = head;

        // p1 指向奇数val 的节点
        LinkedNode p1 = dummyHead;
        while (p1.next != null && p1.next.val % 2 == 0) {
            p1 = p1.next;
        }
        // p1.next 是 第一个奇数节点，把他挪到 dummyHead.next
        LinkedNode cur = p1.next;
        p1.next = cur.next;

        cur.next = dummyHead.next;
        dummyHead.next = cur;

        p1 = cur;

        // p2 指向偶数val 的节点
        LinkedNode p2 = dummyHead.next;
        while (p2.next != null && p2.next.val % 2 == 1) {
            p2 = p2.next;
        }
        // p2.next 是 第一个偶数节点，把他挪到 dummyHead.next.next
        cur = p2.next;
        p2.next = cur.next;

        cur.next = dummyHead.next.next;
        dummyHead.next.next = cur;

        p2 = cur;

        cur = cur.next;
        while (cur != null) {
            if (cur.val % 2 == 1) {
                LinkedNode next = cur.next;

                p2.next = cur.next;

                cur.next = p1.next;
                p1.next = cur;

                p1 = p1.next;
                cur = next;
            } else {
                p2 = cur;
                cur = cur.next;
            }
        }

        return dummyHead.next;
    }


    /**
     * 双指针
     *
     * 哎，，大意了，，想多了。。。
     * 此题，只想让我把第1、3、5...个节点放到前面
     *             把第2、4、6...个节点放到后面
     * 跟节点值的奇偶性无关。。。
     */
    public LinkedNode oddEvenList(LinkedNode head) {

        if (head == null) {
            return null;
        }

        // 奇数编号 Node
        LinkedNode p1 = head;

        // 偶数编号 Node
        LinkedNode evenHead = head.next;
        LinkedNode p2 = head.next;

        while (p2 != null && p2.next != null) {
            p1.next = p2.next;
            p1 = p1.next;

            p2.next = p1.next;
            p2 = p2.next;
        }
        p1.next = evenHead;

        return head;
    }

}
