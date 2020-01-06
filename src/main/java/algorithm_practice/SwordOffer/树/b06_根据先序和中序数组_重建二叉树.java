package algorithm_practice.SwordOffer.树;

import common.datastruct.BinaryTreeNode;
import common.util.PrintBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;

/*
 根据前序和中序序列（不含有重复的数字），构建一棵二叉树
 Created by nibnait on 2019-07-17
 */
public class b06_根据先序和中序数组_重建二叉树 extends TestCase {

    private BinaryTreeNode constructBinaryTree(int[] preOrderArray, int[] inOrderArray) {

        if (preOrderArray == null || inOrderArray == null || preOrderArray.length != inOrderArray.length || inOrderArray.length <= 0) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrderArray.length; i++) {
            map.put(inOrderArray[i], i);
        }
        return constructBinaryTree(preOrderArray, 0, preOrderArray.length - 1, inOrderArray, 0, inOrderArray.length - 1, map);
    }

    /*
    画二叉树，根据先序数组确定根结点（第一个数字），
            根据中序数组确定 这个根结点的左右子树的范围

     */
    private BinaryTreeNode constructBinaryTree(int[] preOrderArray, int preStart, int preEnd, int[] inOrderArray, int inStart, int inEnd, HashMap<Integer, Integer> inOrderIndexMap) {

        if (preStart > preEnd) {
            return null;
        }

        Integer rootIndex = inOrderIndexMap.get(preOrderArray[preStart]);
        if (rootIndex == null || rootIndex < inStart || rootIndex > inEnd) {
            throw new IllegalArgumentException("前序和中序数组不匹配，无法重建二叉树");
        }

        BinaryTreeNode head = new BinaryTreeNode(preOrderArray[preStart]);
        head.left = constructBinaryTree(preOrderArray, preStart + 1, preStart + (rootIndex - inStart), inOrderArray, inStart, rootIndex - 1, inOrderIndexMap);
        head.right = constructBinaryTree(preOrderArray, preStart + (rootIndex - inStart) + 1, preEnd, inOrderArray, rootIndex + 1, inEnd, inOrderIndexMap);
        return head;
    }

    /*
    输入的两个序列不匹配
     */
    @Test
    public void test9() {
        int[] preOrderArray = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrderArray = {4, 7, 1, 2, 5, 3, 8, 6};
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
        BinaryTreeNode root = constructBinaryTree(preOrderArray, inOrderArray);
        PrintBinaryTree.print(root);
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
    public void test1() {
        int[] preOrderArray = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrderArray = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = constructBinaryTree(preOrderArray, inOrderArray);
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
        BinaryTreeNode root = constructBinaryTree(preOrderArray, inOrderArray);
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
        BinaryTreeNode root = constructBinaryTree(preOrderArray, inOrderArray);
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
        BinaryTreeNode root = constructBinaryTree(preOrderArray, inOrderArray);
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

}
