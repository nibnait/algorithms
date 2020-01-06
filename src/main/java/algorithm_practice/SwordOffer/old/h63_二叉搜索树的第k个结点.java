package algorithm_practice.SwordOffer.old;

import common.datastruct.BinaryTreeNode;
import common.util.SysOut;

/**
 * 题目：给定一棵二叉搜索树，请找出其中的第k大的结点。
 *
 * 【解】：
 *     因为按照中序遍历的顺序遍历一棵二叉搜索树，遍历序列的数值是递增排序的。
 *     所以只需要用中序遍历的方法遍历这棵二叉搜索树，就很容易找出它的第k大结点。
 *
 * Created by nibnait on 2016/10/3.
 */
public class h63_二叉搜索树的第k个结点 {

    public static BinaryTreeNode kthNode(BinaryTreeNode root, int k) {
        if (root == null || k < 1) {
            return null;
        }
        int[] tmp = {k};
        return kthNodeCore(root, tmp);
    }
    private static BinaryTreeNode kthNodeCore(BinaryTreeNode root, int[] k) {
        BinaryTreeNode result = null;
        // 先在左子树中找
        if (root.left != null) {
            result =  kthNodeCore(root.left, k);
        }
        // 如果在左子树中没有找到
        if (result == null) {
            if(k[0] == 1) { // 说明当前的根结点是所要找的结点
                result = root;
            } else {    // 当前的根结点不是要找的结点，但是已经找过了，所以计数器减一
                k[0]--;
            }
        }
        // 根结点以及根结点的左子结点都没有找到，则找其右子树
        if (result == null && root.right != null) {
            result = kthNodeCore(root.right, k);
        }
        return result;
    }
    public static void main(String[] args) {
        BinaryTreeNode root = initBinaryTree();
        SysOut.inOrderPrint(root);
        System.out.println();
        for (int i = 0; i <= 10; i++) {
            System.out.printf(kthNode(root, i) + ", ");
        }
    }

    //            8
    //        6      10
    //       5 7    9  11
    private static BinaryTreeNode initBinaryTree() {
        BinaryTreeNode n1 = new BinaryTreeNode(8);
        BinaryTreeNode n2 = new BinaryTreeNode(6);
        BinaryTreeNode n3 = new BinaryTreeNode(10);
        BinaryTreeNode n4 = new BinaryTreeNode(5);
        BinaryTreeNode n5 = new BinaryTreeNode(7);
        BinaryTreeNode n6 = new BinaryTreeNode(9);
        BinaryTreeNode n7 = new BinaryTreeNode(11);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        return n1;
    }

}
