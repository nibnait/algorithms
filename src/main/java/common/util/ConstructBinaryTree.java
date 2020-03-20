package common.util;

import common.datastruct.TreeNode;
import org.junit.Test;

import java.util.LinkedList;

/*
 构造二叉树
 Created by nibnait on 2019-07-17
 */
public class ConstructBinaryTree {

    /**
     * 根据一个【完全满二叉树】的先序数组
     *
     * @param preOrderArray
     * @return
     */
    public static TreeNode constructByPreOrderArray(Integer[] preOrderArray) {
        if (preOrderArray == null || preOrderArray.length == 0) {
            return null;
        }
        return constructByPreOrderArray(preOrderArray, 0, preOrderArray.length - 1);
    }

    private static TreeNode constructByPreOrderArray(Integer[] preOrderArray, int start, int end) {
        if (start > end || preOrderArray[start] == null) {
            return null;
        }
        TreeNode head = new TreeNode(preOrderArray[start]);
        head.left = constructByPreOrderArray(preOrderArray, start + 1, start + (end - start) / 2);
        head.right = constructByPreOrderArray(preOrderArray, start + (end - start) / 2 + 1, end);
        return head;
    }

    public static TreeNode constructByBFSArray(Integer[] bfsArray) {
        if (bfsArray == null || bfsArray.length == 0) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int count = 0;
        TreeNode head = new TreeNode(bfsArray[count++]);
        queue.add(head);
        while (!queue.isEmpty() && count < bfsArray.length) {
            TreeNode pop = queue.pop();
            Integer leftValue = bfsArray[count++];
            pop.left = new TreeNode(leftValue);
            queue.add(pop.left);

            if (count + 1 <= bfsArray.length) {
                Integer rightValue = bfsArray[count++];
                pop.right = new TreeNode(rightValue);
                queue.add(pop.right);
            }
        }
        return head;
    }

    @Test
    public void testCase() {
        Integer[] preOrderArray1 = {1, null, 2, 3};
        Integer[] preOrderArray = {1, 2, 4, null, 7, null, null, null, 3, 5, null, null, 6, 8, null};
        PrintBinaryTree.print(constructByPreOrderArray(preOrderArray));

        Integer[] BFSArray = {1, 3, 2, 6, 5, null, 4, null, 8, null, null, null, null, 7, null};
        PrintBinaryTree.print(constructByBFSArray(BFSArray));
    }
}
