package SwordOffer.Chapter4;

import Standard.Node;

import java.util.LinkedList;
import java.util.Queue;

import static Standard.stdOut.inOrderPrint;
import static Standard.stdOut.printDoubleLinkedList;

/**
 * 题目：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 【解】：
 *      中序遍历
 *
 * Created by nibnait on 2016/9/26.
 */
public class a27_二叉搜索树与双向链表 {

    /**
     * 自然解法：
     *      将二叉树结点中序遍历到队列中
     * @param head 二叉树的根结点
     * @return 双向链表的头节点
     */
    private static Node convert1(Node head) {
        if (head == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        inOrderToQueue(head, queue);
        head = queue.poll();
        Node pre = head;
        pre.left = null;
        Node cur = null;
        while (!queue.isEmpty()){
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    private static void inOrderToQueue(Node head, Queue<Node> queue) {
        if (head == null){
            return;
        }
        inOrderToQueue(head.left, queue);
        queue.offer(head);
        inOrderToQueue(head.right, queue);
    }

    /**
     * 书上解法：
     *     利用一个二级指针，保存每次已经处理好的当前链表的尾结点
     * @param head 二叉树的根结点
     * @return 双向链表的头节点
     */
    private static Node convert2(Node head) {
        if (head == null){
            return null;
        }
        Node[] last = new Node[1];
        convertNode(head, last);
        head = last[0];
        while (head!=null && head.left!=null){
            head = head.left;
        }
        return head;
    }

    /**
     * 返回已经处理好的双向链表的尾结点
     * @param head
     * @param last
     */
    private static void convertNode(Node head, Node[] last) {
        if (head == null){
            return;
        }
        if (head.left != null){
            convertNode(head.left, last);
        }
        head.left = last[0];   //设置当前结点的前驱结点

        if (last[0] != null) {
            last[0].right = head;  //设置尾结点的后继
        }
        last[0] = head; //记录当前结点为已处理好的双向链表的尾结点

        if (head.right != null){
            convertNode(head.right, last);
        }
    }

    /**
     * 左神解法：
     *
     * @param head
     * @return
     */
    private static Node convert(Node head) {
        if (head == null){
            return null;
        }

        Node last = process(head);
        head = last.right;
        last.right = null;
        return head;
    }

    /**
     * BSTtoDoubleLinkedList
     * @param head
     * @return  返回已经处理好的双向链表的尾结点
     */
    private static Node process(Node head) {
        if (head == null){
            return null;
        }

        Node leftE = process(head.left);
        Node rightE = process(head.right);
        Node leftS = leftE!=null? leftE.right: null;
        Node rightS = rightE!=null? rightE.right: null;
        if (leftE != null && rightE!=null){     //leftS(头)...leftE<-->head<-->rightS...rightE(尾)
            leftE.right = head;
            head.left = leftE;
            head.right = rightS;
            rightS.left = head;
            rightE.right = leftS;
            return rightE;
        } else if (leftE != null){  //head为右子树为空    leftE<-->head(尾)-->leftS(头)
            leftE.right = head;
            head.left = leftE;
            head.right = leftS;
            return head;
        } else if (rightE != null) {    //head的左子树为空，   head(头)<-->rightS...rightE(尾)
            head.right = rightS;
            rightS.left = head;
            rightE.right = head;
            return rightE;
        } else {        //head为左右子节点都为null的叶子结点
            head.right = head;
            return head;
        }
    }


    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();
        test05();
    }

    //            10
    //         /      \
    //        6        14
    //       /\        /\
    //      4  8     12  16
    private static void test01() {
        Node node10 = new Node();
        node10.value = 10;
        Node node6 = new Node();
        node6.value = 6;
        Node node14 = new Node();
        node14.value = 14;
        Node node4 = new Node();
        node4.value = 4;
        Node node8 = new Node();
        node8.value = 8;
        Node node12 = new Node();
        node12.value = 12;
        Node node16 = new Node();
        node16.value = 16;
        node10.left = node6;
        node10.right = node14;
        node6.left = node4;
        node6.right = node8;
        node14.left = node12;
        node14.right = node16;
        System.out.println("inOrderTraversal: ");
        inOrderPrint(node10);
        Node head = convert(node10);
        printDoubleLinkedList(head);
        System.out.println();
    }

    //               5
    //              /
    //             4
    //            /
    //           3
    //          /
    //         2
    //        /
    //       1
    private static void test02() {
        Node node1 = new Node();
        node1.value = 1;
        Node node2 = new Node();
        node2.value = 2;
        Node node3 = new Node();
        node3.value = 3;
        Node node4 = new Node();
        node4.value = 4;
        Node node5 = new Node();
        node5.value = 5;
        node5.left = node4;
        node4.left = node3;
        node3.left = node2;
        node2.left = node1;
        System.out.println("inOrderTraversal: ");
        inOrderPrint(node5);
        Node head = convert(node5);
        printDoubleLinkedList(head);
        System.out.println();
    }
    // 1
    //  \
    //   2
    //    \
    //     3
    //      \
    //       4
    //        \
    //         5
    private static void test03() {
        Node node1 = new Node();
        node1.value = 1;
        Node node2 = new Node();
        node2.value = 2;
        Node node3 = new Node();
        node3.value = 3;
        Node node4 = new Node();
        node4.value = 4;
        Node node5 = new Node();
        node5.value = 5;
        node1.right = node2;
        node2.right = node3;
        node3.right = node4;
        node4.right = node5;
        System.out.println("inOrderTraversal: ");
        inOrderPrint(node1);
        Node head = convert(node1);
        printDoubleLinkedList(head);
        System.out.println();
    }
    // 只有一个结点
    private static void test04() {
        Node node1 = new Node();
        node1.value = 1;
        System.out.println("inOrderTraversal: ");
        inOrderPrint(node1);
        Node head = convert(node1);
        printDoubleLinkedList(head);
        System.out.println();
    }
    // 没有结点
    private static void test05() {
        System.out.println("inOrderTraversal: ");
        inOrderPrint(null);
        System.out.println("null");
        Node head = convert(null);
        printDoubleLinkedList(head);
        System.out.println();
    }

}
