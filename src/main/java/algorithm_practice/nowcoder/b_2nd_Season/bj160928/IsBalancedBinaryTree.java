package algorithm_practice.nowcoder.b_2nd_Season.bj160928;


import common.datastruct.BinaryTreeNode;

/**
 * 判断一棵二叉树是否为 平衡二叉树
 * Created by nibnait on 2016/10/9.
 */
public class IsBalancedBinaryTree {

    public static boolean isBalance(BinaryTreeNode head) {
        return getHeight(head, 0) != -1;
    }

    public static int getHeight(BinaryTreeNode head, int level) {
        if (head == null) {
            return level;
        }
        int lh = getHeight(head.left, level + 1);
        int rh = getHeight(head.right, level + 1);
        if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1) {
            return -1;
        }
        return Math.max(lh, rh);
    }
    
}
