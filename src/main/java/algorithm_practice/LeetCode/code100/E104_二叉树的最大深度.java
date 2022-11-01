package algorithm_practice.LeetCode.code100;

import common.datastruct.TreeNode;
import common.util.binaryTree.ConstructBinaryTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。



来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class E104_二叉树的最大深度 {

    @Test
    public void testCase() {
        Integer[] BFSArray = {3,9,20,null,null,15,7};
        TreeNode root = ConstructBinaryTree.constructByBFSArray(BFSArray);
        int maxDepth = 3;
        Assert.assertEquals(maxDepth, maxDepth(root));

        BFSArray = new Integer[]{2, null, 3, null, 4, null, 5, null, 6};
        root = ConstructBinaryTree.constructByBFSArray(BFSArray);
        maxDepth = 5;
        Assert.assertEquals(maxDepth, maxDepth(root));

        BFSArray = new Integer[]{1,2,3,4,null,null,5};
        root = ConstructBinaryTree.constructByBFSArray(BFSArray);
        maxDepth = 3;
        Assert.assertEquals(maxDepth, maxDepth(root));

    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        int deep = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();

                if (isLeaf(cur)) {
                    continue;
                }

                if (cur.left != null && cur.left.val != null) {
                    queue.offer(cur.left);
                }

                if (cur.right != null && cur.right.val != null) {
                    queue.offer(cur.right);
                }
            }

            if (queue.isEmpty()) {
                return deep;
            }
            deep++;
        }

        return deep;
    }

    private boolean isLeaf(TreeNode cur) {
        return (cur.left == null || cur.left.val == null)
                && (cur.right == null || cur.right.val == null);
    }
}
