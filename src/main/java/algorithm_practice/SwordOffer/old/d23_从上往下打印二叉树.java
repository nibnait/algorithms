package algorithm_practice.SwordOffer.old;

import common.datastruct.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：从上往下打印出二叉树的每个结点，同一层的结点按照从左向右的顺序打印。
 *
 * 【解】：
 *      用队列，
 * Created by nibnait on 2016/9/26.
 */
public class d23_从上往下打印二叉树 {

    private static void printFromToBottom(BinaryTreeNode head) {
        if (head == null){
            return;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(head);
        BinaryTreeNode curNode = null;
        while (!queue.isEmpty()){
            curNode = queue.remove();
            System.out.print(curNode.value + " ");
            if (curNode.left != null){
                queue.add(curNode.left);
            }
            if (curNode.right != null){
                queue.add(curNode.right);
            }
        }
    }



    public static void main(String[] args) {
        //       8
        //    /    \
        //   6     10
        //  / \   / \
        // 5   7 9  11
        BinaryTreeNode head = new BinaryTreeNode();
        head.value = 8;
        head.left = new BinaryTreeNode();
        head.left.value = 6;
        head.left.left = new BinaryTreeNode();
        head.left.left.value = 5;
        head.left.right = new BinaryTreeNode();
        head.left.right.value = 7;
        head.right = new BinaryTreeNode();
        head.right.value = 10;
        head.right.left = new BinaryTreeNode();
        head.right.left.value = 9;
        head.right.right = new BinaryTreeNode();
        head.right.right.value = 11;
        printFromToBottom(head);
        //         1
        //        /
        //       3
        //      /
        //     5
        //    /
        //   7
        //  /
        // 9
        BinaryTreeNode head2 = new BinaryTreeNode();
        head2.value = 1;
        head2.left = new BinaryTreeNode();
        head2.left.value = 3;
        head2.left.left = new BinaryTreeNode();
        head2.left.left.value = 5;
        head2.left.left.left = new BinaryTreeNode();
        head2.left.left.left.value = 7;
        head2.left.left.left.left = new BinaryTreeNode();
        head2.left.left.left.left.value = 9;
        System.out.println("\n");
        printFromToBottom(head2);
        // 0
        //  \
        //   2
        //    \
        //     4
        //      \
        //       6
        //        \
        //         8
        BinaryTreeNode head3 = new BinaryTreeNode();
        head3.value = 0;
        head3.right = new BinaryTreeNode();
        head3.right.value = 2;
        head3.right.right = new BinaryTreeNode();
        head3.right.right.value = 4;
        head3.right.right.right = new BinaryTreeNode();
        head3.right.right.right.value = 6;
        head3.right.right.right.right = new BinaryTreeNode();
        head3.right.right.right.right.value = 8;
        System.out.println("\n");
        printFromToBottom(head3);
        // 1
        BinaryTreeNode head4 = new BinaryTreeNode();
        head4.value = 1;
        System.out.println("\n");
        printFromToBottom(head4);
        // null
        System.out.println("\n");
        printFromToBottom(null);

    }

}
