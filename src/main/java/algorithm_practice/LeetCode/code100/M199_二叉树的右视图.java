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

        Integer[] root1 = {1, 2, 3, null, 5, null, 4};
        System.out.println(rightSideView(ConstructBinaryTree.constructByBFSArray(root1)));


    }


    /**
     * 1. 层先法遍历二叉树
     * 2. 然后取第1、2、4、8、16...个结点上的val
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> bfsArray = new ArrayList<>();

        bfsTravel(root, bfsArray);
//        System.out.println(JSON.toJSONString(bfsArray));

        List<Integer> result = new ArrayList<>();
        int row = 0;
        int index = Double.valueOf(Math.pow(2, row)).intValue();
        while (index <= bfsArray.size()) {
            int rightIndex = index - 1;
            Integer node = bfsArray.get(rightIndex);
            if (node != null) {
                result.add(node);
            } else {
                while (bfsArray.get(--rightIndex) != null) {
                    result.add(node);
                }
            }

            index += Double.valueOf(Math.pow(2, ++row)).intValue();
        }

        return bfsArray;
    }

    private void bfsTravel(TreeNode root, List<Integer> bfsArray) {

        bfsArray.add(root.val);

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root.left);
        queue.addLast(root.right);

        while (!queue.isEmpty()) {

            TreeNode treeNode = queue.pollFirst();
            bfsArray.add(treeNode.val);

            if (isNotLeafNode(treeNode)) {
                queue.addLast(treeNode.left);
                queue.addLast(treeNode.right);
            }
        }

    }

    private boolean isNotLeafNode(TreeNode treeNode) {
        return treeNode.left != null && treeNode.right != null;
    }
}
