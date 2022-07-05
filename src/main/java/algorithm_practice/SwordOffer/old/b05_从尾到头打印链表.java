package algorithm_practice.SwordOffer.old;

import common.datastruct.LinkedNode;

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
    private static void printListInverselyUsingRecursion(LinkedNode head) {
        if (head == null){
            return;
        }
        printListInverselyUsingRecursion(head.next);
        System.out.print(head.val+ " ");
    }

    /**
     * 栈
     * @param head
     */
    private static void printListInverselyUsingStack(LinkedNode head) {
        if (head == null){
            return;
        }
        Stack<Integer> stack = new Stack<>();
        while (head != null){
            stack.push(head.val);
            head = head.next;
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+ " ");
        }
    }

    /**
     * 利用头插法插入链表
     * @param head
     */
    public static void reverseNode(LinkedNode head) {
        if (head == null) {
            return ;
        }

        //最终翻转之后的 Node
        LinkedNode node ;

        LinkedNode pre = head;
        LinkedNode cur = head.next;
        LinkedNode next                                                                     ;
        while(cur != null){
            next = cur.next;

            //链表的头插法
            cur.next = pre;
            pre = cur;

            cur = next;
        }
        head.next = null;
        node = pre;


        //遍历新链表
        while (node != null){
            System.out.print(node.val + " ");
            node = node.next ;
        }

    }


    public static void main(String[] args) {
        LinkedNode head = new LinkedNode(1);
        head.next = new LinkedNode(2);
        head.next.next = new LinkedNode(3);
        head.next.next.next = new LinkedNode(4);
        head.next.next.next.next = new LinkedNode(5);
        printListInverselyUsingStack(head);
        System.out.println();
        printListInverselyUsingRecursion(head);
        System.out.println();
        reverseNode(head);
    }

}
