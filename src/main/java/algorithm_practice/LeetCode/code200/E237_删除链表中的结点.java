package algorithm_practice.LeetCode.code200;

import common.datastruct.ListNode;
import common.util.ConstructListNode;
import common.util.SysOut;
import junit.framework.TestCase;
import org.junit.Test;

/*
请编写一个函数，使其可以删除某个链表中给定的（非末尾）结点，你将只被给定要求被删除的结点。
现有一个链表 -- head = [4,5,1,9]，它可以表示为: 4->5->1->9

示例 1:
输入: head = [4,5,1,9], node = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个结点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.

示例 2:
输入: head = [4,5,1,9], node = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个结点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.

说明:
链表至少包含两个节点。
链表中所有节点的值都是唯一的。
给定的节点为非末尾节点并且一定是链表中的一个有效节点。
不要从你的函数中返回任何结果。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 * Created by nibnait on 2019-08-08
 */
public class E237_删除链表中的结点 extends TestCase {

    @Test
    public void testCase() {
        int[] list1 = {4,5,1,9};
        ListNode listNode1 = ConstructListNode.construct(list1);
        SysOut.printList(listNode1);
        deleteNode(listNode1.next);
        SysOut.printList(listNode1);

    }

    public void deleteNode(ListNode node) {
        ListNode nextNode = node.next;
        while (nextNode != null) {
            node.val = nextNode.val;
            nextNode = nextNode.next;
            if (nextNode != null) {
                node = node.next;
            } else {
                node.next = null;
            }
        }
    }
}
