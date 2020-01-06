package algorithm_practice.nowcoder.b_2nd_Season.bh160907;

import common.datastruct.BinaryTreeNode;
import common.util.PrintBinaryTree;

/**
 * 找到二叉树中的最大搜索二叉子树
 *
 * Created by nibnait on 2016/9/16.
 */
public class BiggestSubBSTInTree {

    private static BinaryTreeNode biggestSubBST(BinaryTreeNode head) {

        return null;
    }




    public static void main(String[] args) {

        BinaryTreeNode head = new BinaryTreeNode(6);
        head.left = new BinaryTreeNode(1);
        head.left.left = new BinaryTreeNode(0);
        head.left.right = new BinaryTreeNode(3);
        head.right = new BinaryTreeNode(12);
        head.right.left = new BinaryTreeNode(10);
        head.right.left.left = new BinaryTreeNode(4);
        head.right.left.left.left = new BinaryTreeNode(2);
        head.right.left.left.right = new BinaryTreeNode(5);
        head.right.left.right = new BinaryTreeNode(14);
        head.right.left.right.left = new BinaryTreeNode(11);
        head.right.left.right.right = new BinaryTreeNode(15);
        head.right.right = new BinaryTreeNode(13);
        head.right.right.left = new BinaryTreeNode(20);
        head.right.right.right = new BinaryTreeNode(16);

        PrintBinaryTree.print(head);
        BinaryTreeNode bst = biggestSubBST(head);
        PrintBinaryTree.print(bst);

    }

}
