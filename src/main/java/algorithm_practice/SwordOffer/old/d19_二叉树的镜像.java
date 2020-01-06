package algorithm_practice.SwordOffer.old;

import common.datastruct.BinaryTreeNode;
import common.util.SysOut;

/**
 * 题目：请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 【思路】
 *  先序遍历这棵树的每个结点，
 *  如果遍历到的结点有子结点，就交换它的两个子结点。
 *  当交换完所有非叶子结点的左右子结点之后，就得到了树的镜像。
 *
 * Created by nibnait on 2016/9/23.
 */
public class d19_二叉树的镜像 {

    private static void mirrorRecursivelly(BinaryTreeNode head) {
        if (head == null){
            return;
        }
        if (head.left==null && head.right==null){
            return;
        }
        BinaryTreeNode tmp = head.left;
        head.left = head.right;
        head.right = tmp;
        mirrorRecursivelly(head.left);
        mirrorRecursivelly(head.right);
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
        SysOut.printBinaryTree(head);
        System.out.println();
        mirrorRecursivelly(head);
        SysOut.printBinaryTree(head);
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
        SysOut.printBinaryTree(head2);
        System.out.println();
        mirrorRecursivelly(head2);
        SysOut.printBinaryTree(head2);
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
        SysOut.printBinaryTree(head3);
        System.out.println();
        mirrorRecursivelly(head3);
        SysOut.printBinaryTree(head3);
    }

}
