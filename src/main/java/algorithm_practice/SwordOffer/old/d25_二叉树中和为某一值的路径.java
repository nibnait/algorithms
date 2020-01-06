package algorithm_practice.SwordOffer.old;

import common.datastruct.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：输入一棵二叉树和一个整数， 打印出二叉树中结点值的和为输入整数的所有路径。
 * 【从树的根结点开始往下一直到叶结点所经过的结点形成一条路径】
 *
 * Created by nibnait on 2016/9/26.
 */
public class d25_二叉树中和为某一值的路径 {

    private static void findPath(BinaryTreeNode head, int expectedSum) {
        if (head == null){
            return;
        }
        
        List<Integer> path = new ArrayList<>();
        int currentSum = 0;
        findPath(head, expectedSum, path, currentSum);
    }

    private static void findPath(BinaryTreeNode head, int expectedSum, List<Integer> path, int currentSum) {
        currentSum += head.value;
        path.add(head.value);
        if (head.left==null && head.right==null && currentSum == expectedSum){
            System.out.println(path);
        }
        if (head.left != null){
            findPath(head.left, expectedSum, path, currentSum);
        }
        if (head.right != null){
            findPath(head.right, expectedSum, path, currentSum);
        }
        path.remove(path.size()-1);
    }


    public static void main(String[] args) {
        //            10
        //         /      \
        //        5        12
        //       /\
        //      4  7
        BinaryTreeNode head = new BinaryTreeNode();
        head.value = 10;
        head.left = new BinaryTreeNode();
        head.left.value = 5;
        head.left.left = new BinaryTreeNode();
        head.left.left.value = 4;
        head.left.right = new BinaryTreeNode();
        head.left.right.value = 7;
        head.right = new BinaryTreeNode();
        head.right.value = 12;
        // 有两条路径上的结点和为22
        System.out.println("findPath(head, 22);");
        findPath(head, 22);
        // 没有路径上的结点和为15
        System.out.println("findPath(head, 15);");
        findPath(head, 15);
        // 有一条路径上的结点和为19
        System.out.println("findPath(head, 19);");
        findPath(head, 19);
        //               5
        //              /
        //             4
        //            /
        //           3
        //          /
        //         2
        //        /
        //       1
        BinaryTreeNode head2 = new BinaryTreeNode();
        head2.value = 5;
        head2.left = new BinaryTreeNode();
        head2.left.value = 4;
        head2.left.left = new BinaryTreeNode();
        head2.left.left.value = 3;
        head2.left.left.left = new BinaryTreeNode();
        head2.left.left.left.value = 2;
        head2.left.left.left.left = new BinaryTreeNode();
        head2.left.left.left.left.value = 1;
        // 有一条路径上面的结点和为15
        System.out.println("findPath(head2, 15);");
        findPath(head2, 15);
        // 没有路径上面的结点和为16
        System.out.println("findPath(head2, 16);");
        findPath(head2, 16);
        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        //        \
        //         5
        BinaryTreeNode head3 = new BinaryTreeNode();
        head3.value = 1;
        head3.right = new BinaryTreeNode();
        head3.right.value = 2;
        head3.right.right = new BinaryTreeNode();
        head3.right.right.value = 3;
        head3.right.right.right = new BinaryTreeNode();
        head3.right.right.right.value = 4;
        head3.right.right.right.right = new BinaryTreeNode();
        head3.right.right.right.right.value = 5;
        // 有一条路径上面的结点和为15
        System.out.println("findPath(head3, 15);");
        findPath(head3, 15);
        // 没有路径上面的结点和为16
        System.out.println("findPath(head3, 16);");
        findPath(head3, 16);
        // 树中只有1个结点
        BinaryTreeNode head4 = new BinaryTreeNode();
        head4.value = 1;
        // 有一条路径上面的结点和为1
        System.out.println("findPath(head4, 1);");
        findPath(head4, 1);
        // 没有路径上面的结点和为2
        System.out.println("findPath(head4, 2);");
        findPath(head4, 2);
        // 树中没有结点
        System.out.println("findPath(null, 0);");
        findPath(null, 0);

    }

}
