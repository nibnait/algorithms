package algorithm_practice.LeetCode.code100;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

/*
给定一个二叉树，判断它是否是高度平衡的二叉树。
本题中，一棵高度平衡二叉树定义为：
    一个二叉树每个结点 的左右两个子树的高度差的绝对值不超过1。

示例 1:
给定二叉树 [3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7
返回 true 。

示例 2:
给定二叉树 [1,2,2,3,3,null,null,4,4]
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/balanced-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 Created by nibnait on 2019-07-20
 */
public class E110_平衡二叉树 extends TestCase {

    /*
     遍历二叉树，
     先看head的左子树是否平衡
        不平衡直接返回false
     再看head的右子树是否平衡

     若两个子树都平衡，再看两个子树的高度差是否小于等于1
     */


    public boolean isBalanced(TreeNode head) {

        if (head == null) {
            return true;
        }
        Boolean[] isBalanced = new Boolean[1];
        isBalanced[0] = true;
        getHeight(head, 1, isBalanced);
        return isBalanced[0];
    }

    private int getHeight(TreeNode head, int height, Boolean[] isBalanced) {
        if (head == null) {
            return height - 1;
        }
        int leftHeight = getHeight(head.left, height + 1, isBalanced);
        if (!isBalanced[0]) {
            return height;
        }
        int rightHeight = getHeight(head.right, height + 1, isBalanced);
        if (!isBalanced[0]) {
            return height;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalanced[0] = false;
        }
        return Math.max(leftHeight, rightHeight);
    }

    /*
            普通二叉树
                 1
              /     \
             2       3
            /       / \
           4       5   6
            \         /
             7       8
     */
    @Test
    public void testCase() {
        Integer[] preOrderArray = {1, 2, 4, null, 7, null, null, null, 3, 5, null, null, 6, 8, null};
        TreeNode treeNode = ConstructBinaryTree.constructByPreOrderArray(preOrderArray);
        System.out.println(isBalanced(treeNode));
    }
}
