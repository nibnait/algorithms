package SwordOffer;

import Standard.ListNode;

import java.util.Stack;

/**
 * 不改变原数据结构
 * --> 用 栈
 * -->改写成 递归√
 * Created by nibnait on 2016/9/20.
 */
public class b05_从尾到头打印链表 {

    /**
     * 递归
     * @param head
     */
    private static void printListInverselyUsingRecursion(ListNode head) {
        if (head == null){
            return;
        }
        printListInverselyUsingRecursion(head.next);
        System.out.print(head.value+ " ");
    }

    /**
     * 栈
     * @param head
     */
    private static void printListInverselyUsingStack(ListNode head) {
        if (head == null){
            return;
        }
        Stack<Integer> stack = new Stack<>();
        while (head != null){
            stack.push(head.value);
            head = head.next;
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+ " ");
        }
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        printListInverselyUsingStack(head);
        System.out.println();
        printListInverselyUsingRecursion(head);
    }

}
