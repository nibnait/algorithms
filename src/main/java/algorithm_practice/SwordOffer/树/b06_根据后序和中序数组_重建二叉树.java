package algorithm_practice.SwordOffer.树;

import common.datastruct.BinaryTreeNode;
import common.util.PrintBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;

/*
 根据后序和中序序列（不含有重复的数字），构建一棵二叉树
 Created by nibnait on 2019-07-17
 */
public class b06_根据后序和中序数组_重建二叉树 extends TestCase {

    private BinaryTreeNode constructBinaryTree(int[] postOrderArray, int[] inOrderArray) {

        if (postOrderArray == null || inOrderArray == null || postOrderArray.length != inOrderArray.length || inOrderArray.length <= 0) {
            return null;
        }

        HashMap<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inOrderArray.length; i++) {
            inOrderMap.put(inOrderArray[i], i);
        }
        return constructBinaryTree(postOrderArray, 0, postOrderArray.length - 1, inOrderMap, 0, inOrderArray.length - 1);
    }

    /*
    画二叉树，根据后序数组确定根结点（最后一个数字），
            根据中序数组确定 这个根结点的左右子树的范围

     */
    private BinaryTreeNode constructBinaryTree(int[] postOrderArray, int postStart, int postEnd, HashMap<Integer, Integer> inOrderIndexMap, int inStart, int inEnd) {

        if (postStart > postEnd) {
            return null;
        }

        Integer rootIndex = inOrderIndexMap.get(postOrderArray[postEnd]);
        if (rootIndex == null || rootIndex < inStart || rootIndex > inEnd) {
            throw new IllegalArgumentException("后序和中序数组不匹配，无法重建二叉树");
        }

        BinaryTreeNode head = new BinaryTreeNode(postOrderArray[postEnd]);
        head.left = constructBinaryTree(postOrderArray, postStart, postStart + (rootIndex - inStart) - 1, inOrderIndexMap, inStart, rootIndex - 1);
        head.right = constructBinaryTree(postOrderArray, postStart + (rootIndex - inStart), postEnd - 1, inOrderIndexMap, rootIndex + 1, inEnd);
        return head;
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
        int[] preOrderArray = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrderArray = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] postOrderArray = {7, 4, 2, 5, 8, 6, 3, 1};
        BinaryTreeNode root = constructBinaryTree(postOrderArray, inOrderArray);
        PrintBinaryTree.print(root);
    }

    /*
     所有结点都没有右子结点
                1
               /
              2
             /
            3
           /
          4
         /
        5
     */
    @Test
    public void test2() {
        int[] preOrderArray = {1, 2, 3, 4, 5};
        int[] inOrderArray = {5, 4, 3, 2, 1};
        int[] postOrderArray = {5, 4, 3, 2, 1};
        BinaryTreeNode root = constructBinaryTree(postOrderArray, inOrderArray);
        PrintBinaryTree.print(root);
    }

    /*
     所有结点都没有左子结点
                1
                 \
                  2
                   \
                    3
                     \
                      4
                       \
                        5
     */
    @Test
    public void test3() {
        int[] preOrderArray = {1, 2, 3, 4, 5};
        int[] inOrderArray = {1, 2, 3, 4, 5};
        int[] postOrderArray = {5, 4, 3, 2, 1};
        BinaryTreeNode root = constructBinaryTree(postOrderArray, inOrderArray);
        PrintBinaryTree.print(root);
    }

    /*
     树中只有一个结点
     */
    @Test
    public void test4() {
        int[] preOrderArray = {1};
        int[] inOrderArray = {1};
        BinaryTreeNode root = constructBinaryTree(preOrderArray, inOrderArray);
        PrintBinaryTree.print(root);
    }

    /*
     完全二叉树
                  1
               /     \
              2       3
             / \     / \
            4   5   6   7
     */
    @Test
    public void test5() {
        int[] preOrderArray = {1, 2, 4, 5, 3, 6, 7};
        int[] inOrderArray = {4, 2, 5, 1, 6, 3, 7};
        int[] postOrderArray = {4, 5, 2, 6, 7, 3, 1};
        BinaryTreeNode root = constructBinaryTree(postOrderArray, inOrderArray);
        PrintBinaryTree.print(root);
    }

    /*
     输入空指针
     */
    @Test
    public void test6() {
        constructBinaryTree(null, null);
    }

    /*
     输入的两个序列不匹配
     */
    @Test
    public void test7() {
        int[] preOrderArray = {1, 2, 4, 5, 3, 6, 7};
        int[] inOrderArray = {4, 2, 8, 1, 6, 3, 7};
        BinaryTreeNode root = constructBinaryTree(preOrderArray, inOrderArray);
        PrintBinaryTree.print(root);
    }

    /*
    输入的两个序列不匹配
     */
    @Test
    public void test8() {
        int[] preOrderArray = {2, 1, 4, 7, 3, 5, 6, 8};
        int[] inOrderArray = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] postOrderArray = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = constructBinaryTree(postOrderArray, inOrderArray);
        PrintBinaryTree.print(root);
    }

}
