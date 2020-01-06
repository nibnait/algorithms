package algorithm_practice.nowcoder.b_2nd_Season.bj160928;

import common.datastruct.BinaryTreeNode;

/**
 * 判断一棵二叉树是否为搜索二叉树 
 * 
 * Created by nibnait on 2016/10/9.
 */
public class IsSearchBinaryTree {

    public static boolean isBST(BinaryTreeNode head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        BinaryTreeNode pre = null;
        BinaryTreeNode cur1 = head;
        BinaryTreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if (pre != null && pre.value > cur1.value) {
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }
    
}
