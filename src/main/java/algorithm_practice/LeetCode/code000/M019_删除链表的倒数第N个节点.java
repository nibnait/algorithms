package algorithm_practice.LeetCode.code000;

import common.datastruct.LinkedNode;
import common.util.ConstructLinkedNode;
import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：
给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.

说明：
给定的 n 保证是有效的。

进阶：
你能尝试使用一趟扫描实现吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-07
 */
public class M019_删除链表的倒数第N个节点 extends TestCase {

    @Test
    public void testCase() {
        int[] list1 = {1,2,3,4,5};
        int n = 2;
        LinkedNode before = ConstructLinkedNode.constructSingleLinkedNode(list1);
        before.printListNode();
        System.out.println();
        LinkedNode after = removeNthFromEnd(before, n);
        after.printListNode();

        System.out.println();

        int[] list2 = {1};
        int n2 = 1;
        LinkedNode linkedNode2 = ConstructLinkedNode.constructSingleLinkedNode(list2);
        SysOut.printLinkedNode(linkedNode2);
        SysOut.printLinkedNode(removeNthFromEnd(linkedNode2, n2));
    }

    /**
     * 双指针
     * @param head
     * @param n
     * @return
     */
    public LinkedNode removeNthFromEnd(LinkedNode head, int n) {
        //指向最后一个结点
        LinkedNode lastNode = head;
        for (int i = 0; i < n-1; i++) {
            lastNode = lastNode.next;
        }
        LinkedNode preNode = head;
        LinkedNode curNode = head.next;
        if (lastNode == null || lastNode.next == null) {
            return curNode;
        }
        lastNode = lastNode.next;
        while (lastNode.next != null) {
            preNode = preNode.next;
            curNode = curNode.next;
            lastNode = lastNode.next;
        }
        preNode.next = curNode.next;
        return head;
    }
}
