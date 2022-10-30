package common.util;

import common.datastruct.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *  构造二叉树
 *  Created by nibnait on 2019-07-17
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

    /**
     * 根据按层遍历的数组，生成一棵二叉树
     */
    public static TreeNode constructByBFSArray(Integer[] bfsArray) {
        if (bfsArray == null || bfsArray.length == 0) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int count = 0;
        Integer val = bfsArray[count++];
        if (val == null) {
            return null;
        }
        TreeNode head = new TreeNode(val);

        queue.add(head);
        while (!queue.isEmpty() && count < bfsArray.length) {
            TreeNode pop = queue.pop();
            if (pop.val == null) {
                continue;
            }
            Integer leftValue = bfsArray[count++];
            pop.left = leftValue == null ? null : new TreeNode(leftValue);
            queue.add(pop.left);

            if (count + 1 <= bfsArray.length) {
                Integer rightValue = bfsArray[count++];
                pop.right = rightValue == null ? null : new TreeNode(rightValue);
                queue.add(pop.right);
            }
        }
        return head;
    }

    /**
     * 生成一个随机树
     * @param maxLevel 指定最大的层数
     * @param maxValue 指定树里节点的最大值
     */
    public static TreeNode generateRandomBinaryTree(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    /**
     * 随机 pick 树中的一个节点
     */
    public static TreeNode pickRandomOne(TreeNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    private static void fillPrelist(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
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
