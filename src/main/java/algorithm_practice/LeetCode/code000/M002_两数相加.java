package algorithm_practice.LeetCode.code000;

import common.datastruct.ListNode;

/*
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个结点只能存储 一位 数字。
如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-two-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class M002_两数相加 {

    public static void main(String[] args) {
        //1,8   0
        //1,    9,9
        ListNode firstListNode = new ListNode(2);
        firstListNode.next = new ListNode(7);
        firstListNode.next.next = new ListNode(1);

        ListNode secondListNode = new ListNode(3);
        secondListNode.next = new ListNode(2);
        secondListNode.next.next = new ListNode(9);
        secondListNode.next.next.next = new ListNode(9);

        addTwoNumbers(firstListNode, secondListNode).print();
        System.out.println();
        addTwoNumbers_v2(firstListNode, secondListNode).print();

    }

    /**
     * 法2：递归
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode addTwoNumbers_v2(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode result = new ListNode(0);
        result.next = sumListNode(l1, l2, carry);
        return result.next;
    }

    static int TEN = 10;

    private static ListNode sumListNode(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            return carry > 0 ? new ListNode(carry) : null;
        }
        int x = l1 == null ? 0 : l1.val;
        int y = l2 == null ? 0 : l2.val;
        int sum = x + y + carry;
        carry = sum / TEN;
        ListNode sumNode = new ListNode(sum % TEN);
        sumNode.next = sumListNode(l1 != null ? l1.next : null, l2 != null ? l2.next : null, carry);
        return sumNode;
    }


    /**
     * 法1：两大数相加
     *      保留一个头
     *      使用一个valNode，依次往后移动，
     *      注意末尾二次进位
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int TEN = 10;
        ListNode result = null;
        int carry = 0;
        ListNode valNode = new ListNode(0);
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / TEN;
            valNode.next = new ListNode(sum % TEN);
            if (result == null) {
                result = valNode.next;
            }
            valNode = valNode.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if (carry > 0) {
            valNode.next = new ListNode(carry);
        }
        return result;
    }

    /**
     * 标准答案，1. 注意命名规范。
     *          dummyHead、currentNode
     *
     *          2. dummyHead赋值的时机。
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode daan(ListNode l1, ListNode l2) {
        int TEN = 10;
        int carry = 0;
        ListNode currentNode = new ListNode(0);
        ListNode dummyHead = currentNode;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / TEN;
            currentNode.next = new ListNode(sum % TEN);
            currentNode = currentNode.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if (carry > 0) {
            currentNode.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}