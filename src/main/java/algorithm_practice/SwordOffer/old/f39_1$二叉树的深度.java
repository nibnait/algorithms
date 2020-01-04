package algorithm_practice.SwordOffer.old;

import common.datastruct.BinaryTreeNode;

/**
 * 题目一：输入一棵二叉树的根结点，求该树的深度。
 * 从根结点到叶子点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 【解】：递归
 *
 *
 * Created by nibnait on 2016/10/1.
 */
public class f39_1$二叉树的深度 {

    public static int TreeDepth(BinaryTreeNode head){
        if (head==null){
            return 0;
        }
        int left = TreeDepth(head.left);
        int right = TreeDepth(head.right);
        return left>right? left+1: right+1;
    }

}
