package cc.tianbin.algorithm_practice.LeetCode.code100;

import cc.tianbin.common.datastruct.TreeNode;
import cc.tianbin.common.util.binaryTree.ConstructBinaryTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明：叶子节点是指没有子节点的节点。

示例 1：
     3
    / \
   9   20
       / \
      15  7

输入：root = [3,9,20,null,null,15,7]
输出：2
示例 2：

输入：root = [2,null,3,null,4,null,5,null,6]
输出：5
 

提示：

树中节点数的范围在 [0, 105] 内
-1000 <= Node.val <= 1000


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class E111_二叉树的最小深度 {

    @Test
    public void testCase() {
        Integer[] BFSArray = {3, 9, 20, null, null, 15, 7};
        TreeNode root = ConstructBinaryTree.constructByBFSArray(BFSArray);
        int minDepth = 2;
        Assert.assertEquals(minDepth, minDepth(root));

        BFSArray = new Integer[]{2, null, 3, null, 4, null, 5, null, 6};
        root = ConstructBinaryTree.constructByBFSArray(BFSArray);
//        PrintBinaryTree.print(root);
        minDepth = 5;
        Assert.assertEquals(minDepth, minDepth(root));

        BFSArray = new Integer[]{1,2,3,4,5};
        root = ConstructBinaryTree.constructByBFSArray(BFSArray);
//        PrintBinaryTree.print(root);
        minDepth = 2;
        Assert.assertEquals(minDepth, minDepth(root));

        BFSArray = new Integer[]{1,2,3,4,null,null,5};
        root = ConstructBinaryTree.constructByBFSArray(BFSArray);
//        PrintBinaryTree.print(root);
        minDepth = 3;
        Assert.assertEquals(minDepth, minDepth(root));

    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        int deep = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            int currentDeep = deep;

            // 将队列中所有节点想四周扩散
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();

                if (isLeaf(cur)) {
                    return Math.min(deep, currentDeep);
                }

                // 将相邻节点加入队列
                if (cur.left != null && cur.left.val != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null && cur.right.val != null) {
                    queue.offer(cur.right);
                }
            }

            // 更新步数
            currentDeep++;
            deep = currentDeep;
        }

        return deep;
    }

    private boolean isLeaf(TreeNode cur) {
        return (null == cur.left || null == cur.left.val)
                && (null == cur.right || null == cur.right.val);
    }

}
