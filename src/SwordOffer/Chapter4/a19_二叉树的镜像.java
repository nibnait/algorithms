package SwordOffer.Chapter4;

import Standard.Node;
import Standard.stdOut;

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
public class a19_二叉树的镜像 {

    private static void MirrorRecursivelly(Node head) {
        if (head == null){
            return;
        }
        if (head.left==null && head.right==null){
            return;
        }
        Node tmp = head.left;
        head.left = head.right;
        head.right = tmp;
        MirrorRecursivelly(head.left);
        MirrorRecursivelly(head.right);
    }

    public static void main(String[] args) {
        //       8
        //    /    \
        //   6     10
        //  / \   / \
        // 5   7 9  11
        Node head = new Node();
        head.value = 8;
        head.left = new Node();
        head.left.value = 6;
        head.left.left = new Node();
        head.left.left.value = 5;
        head.left.right = new Node();
        head.left.right.value = 7;
        head.right = new Node();
        head.right.value = 10;
        head.right.left = new Node();
        head.right.left.value = 9;
        head.right.right = new Node();
        head.right.right.value = 11;
        stdOut.printBinaryTree(head);
        System.out.println();
        MirrorRecursivelly(head);
        stdOut.printBinaryTree(head);
        //         1
        //        /
        //       3
        //      /
        //     5
        //    /
        //   7
        //  /
        // 9
        Node head2 = new Node();
        head2.value = 1;
        head2.left = new Node();
        head2.left.value = 3;
        head2.left.left = new Node();
        head2.left.left.value = 5;
        head2.left.left.left = new Node();
        head2.left.left.left.value = 7;
        head2.left.left.left.left = new Node();
        head2.left.left.left.left.value = 9;
        System.out.println("\n");
        stdOut.printBinaryTree(head2);
        System.out.println();
        MirrorRecursivelly(head2);
        stdOut.printBinaryTree(head2);
        // 0
        //  \
        //   2
        //    \
        //     4
        //      \
        //       6
        //        \
        //         8
        Node head3 = new Node();
        head3.value = 0;
        head3.right = new Node();
        head3.right.value = 2;
        head3.right.right = new Node();
        head3.right.right.value = 4;
        head3.right.right.right = new Node();
        head3.right.right.right.value = 6;
        head3.right.right.right.right = new Node();
        head3.right.right.right.right.value = 8;
        System.out.println("\n");
        stdOut.printBinaryTree(head3);
        System.out.println();
        MirrorRecursivelly(head3);
        stdOut.printBinaryTree(head3);
    }

}
