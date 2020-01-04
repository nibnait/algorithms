package algorithm_practice.SwordOffer.树;

import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import common.util.PrintBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 二叉树的神级遍历 —— 莫里斯遍历（将二叉树平铺成一条线）

 空间复杂度：O(1)【只用两个辅助变量】
 时间复杂度：O(h)
 缺点：破坏了二叉树的结构

 Created by nibnait on 2019-07-19
 */
public class MorrisTraversal extends TestCase {

    /*
        普通二叉树
                 1
              /     \
             2       3
            /       / \
           4       5   6
            \         /
             7       8
        Integer[] preOrderArray = {1, 2, 4, null, 7, null, null, null, 3, 5, null, null, 6, 8, null};
        int[] preOrderArray = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrderArray = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] postOrderArray = {7, 4, 2, 5, 8, 6, 3, 1};

     */
    @Test
    public void testCase() {
        Integer[] preOrderArray = {1, 2, 4, null, 7, null, null, null, 3, 5, null, null, 6, 8, null};
        TreeNode treeNode = ConstructBinaryTree.constructByPreOrderArray(preOrderArray);
        TreeNode treeNode2 = ConstructBinaryTree.constructByPreOrderArray(preOrderArray);
        TreeNode treeNode3 = ConstructBinaryTree.constructByPreOrderArray(preOrderArray);
        PrintBinaryTree.print(treeNode);

        System.out.println(MorrisInorderTraversal(treeNode));
        System.out.println(MorrisPreorderTraversal(treeNode2));
        System.out.println(MorrisPostorderTraversal(treeNode3));
    }

    /**
     * 莫里斯中序遍历
     * 将curHead放到curHead.left 的最右结点处
     */
    private List<Integer> MorrisInorderTraversal(TreeNode head) {
        List<Integer> result = new ArrayList<>();
        if (head == null) {
            return result;
        }

        TreeNode curHead = head;
        TreeNode curLeft = null;
        while (curHead != null) {
            curLeft = curHead.left;
            if (curLeft != null) {
                // 将curHead放到curLeft的最右结点处
                while (curLeft.right != null) {
                    curLeft = curLeft.right;
                }
                curLeft.right = curHead;
                curLeft = curHead.left;

                //将curHead.left置空，防止第二次遍历到curLeft，造成死循环
                curHead.left = null;
                curHead = curLeft;
            } else {
                //说明curHead已经是当前最左结点，可以打印了
                result.add(curHead.val);
                curHead = curHead.right;
            }
        }

        return result;
    }

    /**
     * 莫里斯先序遍历
     * 中左右
     * 将curHead.right 放到curHead.left的最右结点处
     */
    private List<Integer> MorrisPreorderTraversal(TreeNode head) {
        List<Integer> result = new ArrayList<>();
        TreeNode curHead = head;
        TreeNode curLeft = null;
        while (curHead != null) {
            curLeft = curHead.left;
            if (curLeft != null) {
                //将curHead.right 放到curLeft的最右结点处
                while (curLeft.right != null) {
                    curLeft = curLeft.right;
                }
                curLeft.right = curHead.right;

                //curHead.right = curHead.left ; curHead = curHead.right
                curLeft = curHead.left;
                curHead.right = curLeft;
                result.add(curHead.val);
                curHead = curHead.right;
            } else {
                //说明curHead没有左结点了，则打印curHead，然后curHead = curHead.right
                result.add(curHead.val);
                curHead = curHead.right;
            }
        }

        return result;
    }

    /**
     * 莫里斯后序遍历
     * 左右中 -> （中右左的逆序）
     * 将curHead.left 放到curHead.right的最左结点处，然后逆序输出
     */
    private List<Integer> MorrisPostorderTraversal(TreeNode head) {
        LinkedList<Integer> result = new LinkedList<>();
        TreeNode curHead = head;
        TreeNode curRight = null;
        while (curHead != null) {
            curRight = curHead.right;
            if (curRight != null) {
                //将curHead.left 放到curRight的最左结点
                while (curRight.left != null) {
                    curRight = curRight.left;
                }
                curRight.left = curHead.left;

                // curHead = curHead.left;
                curRight = curHead.right;
                curHead.left = curRight;
                result.addFirst(curHead.val);
                curHead = curHead.left;
            } else {
                result.addFirst(curHead.val);
                curHead = curHead.left;
            }
        }

        return result;
    }
}
