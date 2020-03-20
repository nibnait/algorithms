package algorithm_practice.LeetCode.code100;

import com.alibaba.fastjson.JSON;
import common.datastruct.TreeNode;
import common.util.ConstructBinaryTree;
import common.util.PrintBinaryTree;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

/*
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:

输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
Created by nibnait on 2020-03-19
 */
public class M199_二叉树的右视图 extends TestCase {

    @Test
    public void testCase() {
        Integer[] root = {1, 2, 3, null, 5, null, 4};
        System.out.println(rightSideView(ConstructBinaryTree.constructByBFSArray(root)));

        Integer[] root1 = {};
        System.out.println(rightSideView(ConstructBinaryTree.constructByBFSArray(root1)));

        Integer[] root2 = {1, 2};
        System.out.println(rightSideView(ConstructBinaryTree.constructByBFSArray(root2)));

        Integer[] root3 = {1, 2, 3, 4};
        System.out.println(rightSideView(ConstructBinaryTree.constructByBFSArray(root3)));


    }

    /**
     * 1. 层先法遍历二叉树
     * // 2. 然后取第1、2、4、8、16...个结点上的val
     * 2. 遇到每一层的最后一个元素 记录下来。
     */
    public List<Integer> rightSideView(TreeNode root) {
//        PrintBinaryTree.print(root);

        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        // queue是当前层的所有节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 下一层的所有节点
        LinkedList<TreeNode> nextRowQueue = new LinkedList<>();

        int val = root.val;
        queue.add(root);

        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                TreeNode treeNode = queue.poll();

                if (treeNode != null && treeNode.val != null) {
                    val = treeNode.val;
                }

                if (treeNode != null && !isLeafNode(treeNode)) {
                    nextRowQueue.add(treeNode.left);
                    nextRowQueue.add(treeNode.right);
                }
            }
            result.add(val);
            copyToQueue(nextRowQueue, queue);
        }
        return result;
    }

    private void copyToQueue(LinkedList<TreeNode> nextRowQueue, LinkedList<TreeNode> queue) {
        queue.clear();
        while (!nextRowQueue.isEmpty()){
            queue.add(nextRowQueue.poll());
        }
    }

    private boolean isLeafNode(TreeNode treeNode) {
        return treeNode.left == null && treeNode.right == null;
    }

}
